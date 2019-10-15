package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class FragmentFollowing extends Fragment {
    public static final String MY_PREF = "MyPref";
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private CurrencyAdapter currencyAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_new, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        currencyAdapter = new CurrencyAdapter(getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(currencyAdapter);
        return v;
    }
}
