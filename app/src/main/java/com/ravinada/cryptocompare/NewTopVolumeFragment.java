package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewTopVolumeFragment extends Fragment {
    private static final String CURRENCY = "CURRENCY";
    public String coinName, coinPrice, coinTag, openDay, image;
    public de.hdodenhof.circleimageview.CircleImageView coinImage;
    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL = "https://www.cryptocompare.com";
    private RecyclerView recyclerView;
    private CurrencyAdapter adapter;
    private List<Currency> currencyList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_volume, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        currencyList = new ArrayList<>();
        adapter = new CurrencyAdapter(this, currencyList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(container.getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        String url = BASE_URL + "/data/top/totalvolfull?limit=20&tsym=USD";
        ((TextView) v.findViewById(R.id.coin_name)).setText(coinName);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        Currency currency = getArguments().getParcelable(CURRENCY);
        assert currency != null;
        coinName = currency.getFullName();
        coinPrice = currency.getCurrentRate();
        coinTag = currency.getCode();
        openDay = currency.getOpenDay();
        image = currency.getImageURL();

    }
}
