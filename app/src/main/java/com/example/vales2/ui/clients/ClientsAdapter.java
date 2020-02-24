package com.example.vales2.ui.clients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vales2.R;
import com.example.vales2.data.Client;
import java.util.ArrayList;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsViewHolder> {

  /**
   * The client list that is passed to the view holder.
   */
  private ArrayList<Client> clientList = new ArrayList<>();

  /**
   * Set the client list and notify the change.
   *
   * @param clientList The client list that is passed to the view holder.
   */
  void setClients(ArrayList<Client> clientList) {
    this.clientList = clientList;
    notifyDataSetChanged();
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

    return new ClientsViewHolder(view);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
    holder.bindData(clientList.get(position));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getItemCount() {
    return clientList.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getItemViewType(final int position) {
    return R.layout.card_view_client;
  }

}
