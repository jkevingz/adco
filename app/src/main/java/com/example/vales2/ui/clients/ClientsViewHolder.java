package com.example.vales2.ui.clients;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vales2.R;
import com.example.vales2.data.Client;

public class ClientsViewHolder extends RecyclerView.ViewHolder {
  private TextView textViewClientName;

  public ClientsViewHolder(@NonNull View itemView) {
    super(itemView);

    textViewClientName = itemView.findViewById(R.id.text_client_name);
  }

  public void bindData(@NonNull Client client) {
    textViewClientName.setText(client.getName());
  }
}
