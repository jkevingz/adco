package com.example.vales2.ui.clients;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vales2.R;
import com.example.vales2.data.Client;

class ClientsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  /**
   * The text view that is showing the client's name.
   */
  private TextView textViewClientName;

  /**
   * The button to block a client.
   */
  private ImageButton blockButton;

  /**
   * The button to unblock a client.
   */
  private ImageButton unblockButton;

  /**
   * The button to edit a client.
   */
  private ImageButton editButton;

  /**
   * The interface listener for the communication to the parent.
   */
  private ClientsViewHolderListener listener;

  /**
   * The client this view is representing to.
   */
  private Client client;

  /**
   * ClientsViewHolder's constructor.
   *
   * @param itemView The item card view.
   */
  ClientsViewHolder(@NonNull View itemView, ClientsViewHolderListener listener) {
    super(itemView);
    this.listener = listener;

    textViewClientName = itemView.findViewById(R.id.text_client_name);
    blockButton = itemView.findViewById(R.id.card_view_client_block_button);
    blockButton.setOnClickListener(this);
    unblockButton = itemView.findViewById(R.id.card_view_client_unblock_button);
    unblockButton.setOnClickListener(this);
    editButton = itemView.findViewById(R.id.card_view_client_edit_button);
    editButton.setOnClickListener(this);
  }

  /**
   * Bind client's data to card view.
   *
   * @param client The client data that is render in the view.
   */
  void bindData(@NonNull Client client) {
    // Store the client to pass it back when an operation takes place.
    this.client = client;

    textViewClientName.setText(client.getName());

    // Add red warning, hide button to block, and show button to unblock when
    // the client is blocked. Otherwise, do the opposite to the buttons and the
    // warning.
    if (client.isBlocked()) {
      int dangerColor = textViewClientName.getContext().getResources().getColor(R.color.danger, null);
      textViewClientName.setTextColor(dangerColor);
      textViewClientName.setCompoundDrawablesWithIntrinsicBounds(
        0, 0, R.drawable.ic_warning_black_20dp, 0
      );

      blockButton.setVisibility(View.GONE);
      unblockButton.setVisibility(View.VISIBLE);
    }
    else {
      int blackColor = textViewClientName.getContext().getResources().getColor(R.color.black, null);
      textViewClientName.setTextColor(blackColor);
      textViewClientName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

      blockButton.setVisibility(View.VISIBLE);
      unblockButton.setVisibility(View.GONE);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.card_view_client_block_button:
        listener.onBlockClicked(client);
        break;

      case R.id.card_view_client_unblock_button:
        listener.onUnblockClicked(client);
        break;

      case R.id.card_view_client_edit_button:
        listener.onEditClicked(client);
    }
  }

  /**
   * The interface that needs to be implemented by classes in order to listen to
   * events on the buttons.
   */
  public interface ClientsViewHolderListener {

    /**
     * Listener callback for the block button.
     *
     * @param client The client the action is on.
     */
    void onBlockClicked(Client client);

    /**
     * Listener callback for the unblock button.
     *
     * @param client The client the action is on.
     */
    void onUnblockClicked(Client client);

    /**
     * Listener callback for the edit button.
     *
     * @param client The client the action is on.
     */
    void onEditClicked(Client client);

  }
}
