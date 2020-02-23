package com.example.vales2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vales2.R;
import com.example.vales2.models.Client;
import java.util.ArrayList;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ImageViewHolder>  {

  private Context ctx;
  private ArrayList<Client> clientList;
  View v;

  public ClientsAdapter(Context context, ArrayList<Client> clientList){
    this.ctx = context;
    this.clientList = clientList;
  }

  @Override
  public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    v = LayoutInflater.from(ctx).inflate(R.layout.cardview_client, parent, false);

    return new ImageViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final ImageViewHolder holder, final int position) {
    holder.card_view_client.setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.fade_scale_animation));

    Client uploadCurrent = clientList.get(position);

    holder.text_client_name.setText(uploadCurrent.getNombre());

//    if (uploadCurrent.isEstatus()) {
//      holder.text_client_name.setCompoundDrawablesWithIntrinsicBounds(0,0, 0, 0);
//    holder.text_client_name.setTextColor(ctx.getResources().getColor(R.color.black));
//    } else {
//      // maximo 20dp
//      holder.text_client_name.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.warning, 0);
//    holder.text_client_name.setTextColor(ctx.getResources().getColor(R.color.red));
//    }


  }


  @Override
  public int getItemCount() {
    return clientList.size();
  }

  public class ImageViewHolder extends RecyclerView.ViewHolder {
    TextView text_client_name;
    CardView card_view_client;

    public ImageViewHolder(View itemView) {
      super(itemView);
      text_client_name = itemView.findViewById(R.id.text_client_name);
      card_view_client = itemView.findViewById(R.id.card_view_client);
    }
  }

}
