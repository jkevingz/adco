package com.example.vales2.ui.clients;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.vales2.data.Client;
import java.util.ArrayList;

public class ClientsViewModel extends ViewModel {

  /**
   * Repository used to communicate with firebase.
   */
  private ClientsRepository clientsRepository = new ClientsRepository();

  /**
   * Live data with a list of clients.
   */
  LiveData<ArrayList<Client>> clientList;

  /**
   * Call the repository to grab the available clients.
   */
  void getClientList() {
    clientList = clientsRepository.getClients();
  }

  /**
   * Call the repository to filter the available clients.
   *
   * @param text The string query to search for.
   */
  void search(String text) {
    clientsRepository.search(text);
  }

  /**
   * Call the repository to add a new client.
   *
   * @param name The client's name.
   * @param address The client's address.
   * @param phoneNumber The clients's phone number.
   */
  void addClient(String name, String address, String phoneNumber) {
    clientsRepository.addClient(name, address, phoneNumber);
  }

}
