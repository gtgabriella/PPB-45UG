package com.example.belajaruts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Makanan extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Makanan() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
//    public static Makanan newInstance(String param1, String param2) {
//        Makanan fragment = new Makanan();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        getDataFromInternet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_makanan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //variabel
    private ArrayList<String> foto2Menu = new ArrayList<>();
    private ArrayList<String> nama2Menu = new ArrayList<>();
    private ArrayList<String> harga2Menu = new ArrayList<>();

    //proses recycler view adapter
    private void prosesRecyclerViewAdapter(){
        RecyclerView recyclerView =  getView().findViewById(R.id.recyclerView); //error
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(foto2Menu, nama2Menu, harga2Menu, getActivity()); //error (getActivity())? -> this

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //this
//        https://stackoverflow.com/questions/54003985/linearlayoutmanager-cannot-use-in-fragment

    }

    //proses pengambilan data (database, internet, dari yang ditulis)
    private void getDataFromInternet(){
        nama2Menu.add("Mie Aceh");
        harga2Menu.add("Rp20.000,00");
        foto2Menu.add("https://monastours.com/wp-content/uploads/2019/08/mie-aceh-150x145.jpg");

        nama2Menu.add("Karee Kameng");
        harga2Menu.add("Rp25.000,00");
        foto2Menu.add("https://monastours.com/wp-content/uploads/2019/08/Karee-kameng-150x150.jpeg");

        nama2Menu.add("Gulaei Keumamah");
        harga2Menu.add("Rp30.000,00");
        foto2Menu.add("https://monastours.com/wp-content/uploads/2019/08/Gulaie-Keumamah-150x150.jpeg");

        prosesRecyclerViewAdapter();
    }


}