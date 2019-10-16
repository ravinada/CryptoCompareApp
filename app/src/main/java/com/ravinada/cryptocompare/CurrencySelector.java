package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class CurrencySelector extends BottomSheetDialogFragment {
    private RecyclerView currencyList;
    private CurrencyChoiceAdapter currencyChoiceAdapter;

    private ArrayList<CurrencyType> currencyType = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_currency_selector,container,false);
        currencyList = view.findViewById(R.id.rvCurrency);
        try {
            JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONFromAsset()));
            JSONArray m_jArry = obj.getJSONArray("currency_types");

            for (int i = 0; i < m_jArry.length(); i++) {
                CurrencyType currency = new CurrencyType();
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String symbol = jo_inside.getString("symbol");
                String abr = jo_inside.getString("abr");
                String name = jo_inside.getString("name");
                currency.setSymbol(symbol);
                currency.setAbr(abr);
                currency.setName(name);
                currencyType.add(currency);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        currencyChoiceAdapter = new CurrencyChoiceAdapter(CurrencySelector.super.getContext(),currencyType);
        currencyList.setLayoutManager(new LinearLayoutManager(getContext()));
        currencyList.setAdapter(currencyChoiceAdapter);
        return view;
    }
    private String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = Objects.requireNonNull(getContext()).getAssets().open("currencies.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
