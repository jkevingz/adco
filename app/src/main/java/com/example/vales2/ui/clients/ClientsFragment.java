package com.example.vales2.ui.clients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.vales2.R;
import com.mikelau.views.shimmer.ShimmerRecyclerViewX;

public class ClientsFragment extends Fragment {

  /**
   * A text view to display a no results found message.
   */
  private TextView textViewNoResultsFound;

  /**
   * The recycler view that is going to render the list of clients.
   */
  private ShimmerRecyclerViewX recyclerViewStudents;

  /**
   * The clients adapter in charge of passing the data to the recycler.
   */
  private ClientsAdapter clientsAdapter;

  /**
   * Initialize the recycler and its adapter. This will also start the skeleton
   * effect provided by the recycler.
   *
   * @param root The root to find the view elements.
   */
  private void initRecycler(View root) {
    recyclerViewStudents = root.findViewById(R.id.recycler_view_clients);
    // Improve performance of the recycler view by letting it know the size is
    // not affected by the contents.
    recyclerViewStudents.setHasFixedSize(true);

    clientsAdapter = new ClientsAdapter();
    recyclerViewStudents.setAdapter(clientsAdapter);
    recyclerViewStudents.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerViewStudents.showShimmerAdapter();
  }

  /**
   * Initialize the no results text view. This will be set as Gone initially.
   *
   * @param root The root to find the view elements.
   */
  private void initNoResultsFound(View root) {
    textViewNoResultsFound = root.findViewById(R.id.fragment_clients_no_results);
    textViewNoResultsFound.setVisibility(View.GONE);
  }

  /**
   * Initialize the view model. This will observe the client list and will send
   * it to the adapter. Also, this will change the visibility to gone or visible
   * depending on the clients list size that is being observed.
   */
  private void initViewModel() {
    ClientsViewModel clientsViewModel = new ViewModelProvider(this).get(ClientsViewModel.class);
    clientsViewModel.getClientList();

    clientsViewModel.clientList.observe(getViewLifecycleOwner(), clients -> {
      clientsAdapter.setClients(clients);
      recyclerViewStudents.hideShimmerAdapter();
      if (clients.isEmpty()) {
        textViewNoResultsFound.setVisibility(View.VISIBLE);
      }
      else {
        textViewNoResultsFound.setVisibility(View.GONE);
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_clients, container, false);

    initRecycler(root);
    initNoResultsFound(root);
    initViewModel();

    return root;
  }
}
