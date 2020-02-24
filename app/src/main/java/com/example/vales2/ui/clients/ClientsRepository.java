package com.example.vales2.ui.clients;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import com.example.vales2.data.Client;
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
   * Call firebase and get the available clients linked to the current logged in
   * user.
   *
   * @return List of clients.
   */
  MutableLiveData<ArrayList<Client>> getClients() {
    userList.clear();

    databaseReference.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Client addedClient = dataSnapshot.getValue(Client.class);
        if (addedClient != null) {
          addedClient.setId(dataSnapshot.getKey());
          userList.add(addedClient);
          userListMutableLiveData.setValue(userList);
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
              userListMutableLiveData.setValue(userList);
            }
          }
        }
      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        for (int index = 0; index < userList.size(); index++) {
          if (userList.get(index).getId().equals(dataSnapshot.getKey())) {
            userList.removeIf(client -> client.getId().equals(dataSnapshot.getKey()));
            userListMutableLiveData.setValue(userList);
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
    });

    return userListMutableLiveData;
  }
}
