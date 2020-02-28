package com.example.vales2.ui.clients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.vales2.R;
import com.example.vales2.data.Client;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikelau.views.shimmer.ShimmerRecyclerViewX;

public class ClientsFragment extends Fragment implements
  ClientsDialog.ClientsDialogListener, ClientsViewHolder.ClientsViewHolderListener {

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
   * The view model owned by this class.
   */
  private ClientsViewModel clientsViewModel;

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

    clientsAdapter = new ClientsAdapter(this);
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
    clientsViewModel = new ViewModelProvider(this).get(ClientsViewModel.class);
    clientsViewModel.getClientList();

    clientsViewModel.clientList.observe(getViewLifecycleOwner(), clients -> {
      clientsAdapter.submitList(clients);
      recyclerViewStudents.hideShimmerAdapter();
      final int visibility = clients.isEmpty() ? View.VISIBLE : View.GONE;
      textViewNoResultsFound.setVisibility(visibility);
    });
  }

  /**
   * Initialize the search view that is used to filter the clients out by their
   * name.
   *
   * @param root The root to find the view elements.
   */
  private void initSearchView(View root) {
    SearchView searchView = root.findViewById(R.id.fragment_clients_search);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        clientsViewModel.search(query);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        clientsViewModel.search(newText);
        return false;
      }
    });

    searchView.setOnCloseListener(() -> {
      clientsViewModel.search("");
      return false;
    });
  }

  /**
   * Initialize the floating action button that is going to create a new client.
   *
   * @param root The root to find the view elements.
   */
  private void initFab(View root) {
    FloatingActionButton fab = root.findViewById(R.id.fragment_clients_fab);
    fab.setOnClickListener(l -> {
      ClientsDialog dialog = new ClientsDialog(this, null);
      dialog.setTargetFragment(this, 1);
      dialog.show(getParentFragmentManager(), "client add dialog");
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
    initSearchView(root);
    initFab(root);

    return root;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onCreate(String name, String address, String phoneNumber) {
    clientsViewModel.addClient(name, address, phoneNumber);
    Toast.makeText(getContext(), getString(R.string.client_add_success), Toast.LENGTH_SHORT).show();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onEdit(Client client) {
    clientsViewModel.editClient(client);
    Toast.makeText(getContext(), R.string.client_edit_success, Toast.LENGTH_SHORT).show();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onBlockClicked(Client client) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle(getString(R.string.block))
      .setMessage(getString(R.string.block_client_confirmation_message))
      .setNegativeButton(getString(R.string.no), null)
      .setPositiveButton(getString(R.string.yes), (dialog, which) -> clientsViewModel.blockClient(client));

    builder.show();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onUnblockClicked(Client client) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle(getString(R.string.unblock))
      .setMessage(getString(R.string.unblock_client_confirmation_message))
      .setNegativeButton(getString(R.string.no), null)
      .setPositiveButton(getString(R.string.yes), (dialog, which) -> clientsViewModel.unblockClient(client));

    builder.show();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onEditClicked(Client client) {
    ClientsDialog dialog = new ClientsDialog(this, client);
    dialog.setTargetFragment(this, 1);
    dialog.show(getParentFragmentManager(), "client add dialog");
  }
}
