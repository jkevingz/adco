package com.example.vales2.ui.clients;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import com.example.vales2.data.Client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

class ClientsRepository {

  /**
   * Firebase database reference.
   */
  private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clients");

  /**
   * The mutable data with the users information.
   */
  private MutableLiveData<ArrayList<Client>> userListMutableLiveData = new MutableLiveData<>();

  /**
   * The user list that is set from firebase.
   */
  private ArrayList<Client> userList = new ArrayList<>();

  /**
   * Runtime query string the data will always be filtered out before displaying
   * it to the final user. By default is an empty string, but this will be
   * change by the search method.
   */
  private String queryString = "";

  /**
   * The child event listener to search for clients in firebase.
   */
  private ChildEventListener childEventListener = null;

  /**
   * Search for clients in firebase.
   */
  private void initFirebaseCall() {
    // Initialize the child listener if it's null only to avoid having multiple
    // listeners.
    if (childEventListener == null) {
      childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
          Client addedClient = dataSnapshot.getValue(Client.class);
          if (addedClient != null) {
            addedClient.setId(dataSnapshot.getKey());
            userList.add(addedClient);
            search(queryString);
          }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
          Client changedClient = dataSnapshot.getValue(Client.class);
          if (changedClient != null) {
            changedClient.setId(dataSnapshot.getKey());

            for (int index = 0; index < userList.size(); index++) {
              if (userList.get(index).getId().equals(changedClient.getId())) {
                userList.set(index, changedClient);
                search(queryString);
              }
            }
          }
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
          for (int index = 0; index < userList.size(); index++) {
            if (userList.get(index).getId().equals(dataSnapshot.getKey())) {
              userList.removeIf(client -> client.getId().equals(dataSnapshot.getKey()));
              search(queryString);
            }
          }
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
          // No idea when this happens.
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
          // No idea when this happens.
        }
      };
    }

    databaseReference.orderByChild("userId").equalTo(FirebaseAuth.getInstance().getUid())
      .addChildEventListener(childEventListener);
  }

  /**
   * Call firebase and get the available clients linked to the current logged in
   * user.
   *
   * @return List of clients.
   */
  MutableLiveData<ArrayList<Client>> getClients() {
    userList.clear();

    initFirebaseCall();

    return userListMutableLiveData;
  }

  /**
   * Filter the clients out by their name.
   *
   * @param text The string query to search for.
   */
  void search(String text) {
    // Save the last string to filter the data out on firebase changes.
    queryString = text;

    ArrayList<Client> filteredList = new ArrayList<>(userList);
    filteredList.removeIf(client -> !client.getName().toLowerCase().contains(text.toLowerCase()));
    userListMutableLiveData.setValue(filteredList);
  }

  /**
   * Store a new client record.
   *
   * @param name The client's name.
   * @param address The client's address.
   * @param phoneNumber The client's phone number.
   */
  void addClient(String name, String address, String phoneNumber) {
    Client client = new Client(name, true, address, phoneNumber, FirebaseAuth.getInstance().getUid());
    databaseReference.push().setValue(client);
  }

}
