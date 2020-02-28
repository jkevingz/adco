package com.example.vales2.ui.clients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.vales2.R;
import com.example.vales2.data.Client;

public class ClientsAdapter extends ListAdapter<Client, ClientsViewHolder> {

  /**
   * The interface listener for the communication to the parent.
   */
  private ClientsViewHolder.ClientsViewHolderListener listener;

  /**
   * Compare the items and determine if it's the same.
   */
  private static final DiffUtil.ItemCallback<Client> DIFF_CALLBACK = new DiffUtil.ItemCallback<Client>() {
    @Override
    public boolean areItemsTheSame(@NonNull Client oldItem, @NonNull Client newItem) {
      return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Client oldItem, @NonNull Client newItem) {
      return oldItem.getName().equals(newItem.getName()) && oldItem.isActive() == newItem.isActive();
    }
  };

  /**
   * ClientsAdapter's constructor.
   */
  protected ClientsAdapter(ClientsViewHolder.ClientsViewHolderListener listener) {
    super(DIFF_CALLBACK);

    this.listener = listener;
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

    return new ClientsViewHolder(view, listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
    holder.bindData(getItem(position));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getItemViewType(final int position) {
    return R.layout.card_view_client;
  }

}
