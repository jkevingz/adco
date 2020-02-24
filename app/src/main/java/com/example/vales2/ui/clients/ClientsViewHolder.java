package com.example.vales2.ui.clients;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vales2.R;
import com.example.vales2.data.Client;

class ClientsViewHolder extends RecyclerView.ViewHolder {

  /**
   * The text view that is showing the client's name.
   */
  private TextView textViewClientName;

  /**
   * ClientsViewHolder's constructor.
   *
   * @param itemView The item card view.
   */
  ClientsViewHolder(@NonNull View itemView) {
    super(itemView);

    textViewClientName = itemView.findViewById(R.id.text_client_name);
  }

  /**
   * Bind client's data to card view.
   *
   * @param client The client data that is render in the view.
   */
  void bindData(@NonNull Client client) {
    textViewClientName.setText(client.getName());

    if (client.isBlocked()) {
      int dangerColor = textViewClientName.getContext().getResources().getColor(R.color.danger, null);
      textViewClientName.setTextColor(dangerColor);
      textViewClientName.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_warning_black_20dp, 0);
    }
  }
}
