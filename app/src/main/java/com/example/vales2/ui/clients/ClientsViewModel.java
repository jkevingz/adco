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
}
