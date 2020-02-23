package com.example.vales2.ui.clients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.vales2.R;
import com.example.vales2.adapters.ClientsAdapter;
import com.example.vales2.models.Client;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikelau.views.shimmer.ShimmerRecyclerViewX;
import java.util.ArrayList;

public class ClientsFragment extends Fragment {

//    private ClientsViewModel clientsViewModel;
    private ShimmerRecyclerViewX recyclerViewStudents;
    DatabaseReference myRef;


    ChildEventListener childEventListener;

    private ClientsAdapter mAdapter;

    private ArrayList<Client> clientList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        clientsViewModel = ViewModelProviders.of(this).get(ClientsViewModel.class);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        clientsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        View root = inflater.inflate(R.layout.fragment_clients, container, false);

        recyclerViewStudents = root.findViewById(R.id.recyclerViewStudents);
        myRef = FirebaseDatabase.getInstance().getReference("Clientes");

        // solo sabemos que es necesario.
        recyclerViewStudents.setHasFixedSize(true);


        mAdapter = new ClientsAdapter(getContext(), clientList);


        recyclerViewStudents.setAdapter(mAdapter);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        getClients();


        return root;
    }



    public void getClients(){
        clientList.clear();
        finishAllListener();

        // Hace efecto de skeleton.
        recyclerViewStudents.showShimmerAdapter();


        childEventListener = myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Client upload = dataSnapshot.getValue(Client.class);
                clientList.add(clientList.size(), upload);

                mAdapter.notifyItemInserted(clientList.size()-1);


                recyclerViewStudents.hideShimmerAdapter();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    ModeloStudent cambiado = dataSnapshot.getValue(ModeloStudent.class);
//
//                    for(int i = 0; i < mUploads.size(); i ++){
//                        if(mUploads.get(i).getUid().equals(cambiado.getUid())){
//                            mUploads.remove(i);
////                            mAdapter.notifyItemRemoved(i);
//                            mUploads.add(i,cambiado);
//                            mAdapter.notifyItemChanged(i);
//                        }
//
//                    }
//
//
//                    if(mUploads.size() == 0){
//                        showEmpty();
//                        txtTitle.setText("No hay estudiantes");
//                    }else{
//                        hideEmpty();
//                        if(mUploads.size() == 1){
//                            txtTitle.setText("Estudiantes ' " + mUploads.size() + " resultado '");
//                        }
//                        else  {
//                            txtTitle.setText("Estudiantes ' " + mUploads.size() + " resultados '");
//                        }
//                    }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                    ModeloStudent eliminado = dataSnapshot.getValue(ModeloStudent.class);
//
//                    for(int i = 0; i < mUploads.size(); i ++){
//                        if(mUploads.get(i).getUid().equals(eliminado.getUid())){
//                            mUploads.remove(i);
//                            mAdapter.notifyItemRemoved(i);
//                        }
//
//                    }
//
//
//                    if(mUploads.size() == 0){
//                        showEmpty();
//                        txtTitle.setText("No hay estudiantes");
//                    }else{
//                        hideEmpty();
//                        if(mUploads.size() == 1){
//                            txtTitle.setText("Estudiantes ' " + mUploads.size() + " resultado '");
//                        }
//                        else  {
//                            txtTitle.setText("Estudiantes ' " + mUploads.size() + " resultados '");
//                        }
//                    }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        finishAllListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finishAllListener();
    }

    private void finishAllListener() {
        if(childEventListener != null) {
            myRef.removeEventListener(childEventListener);
        }
    }

}