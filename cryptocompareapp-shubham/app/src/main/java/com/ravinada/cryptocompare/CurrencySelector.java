package com.ravinada.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.adapters.CurrencyChoiceAdapter;
import com.ravinada.cryptocompare.modelclasses.CurrencyType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class CurrencySelector extends AppCompatActivity implements CurrencyChoiceAdapter.SetCurrencyType{
    private RecyclerView currencyList;
    private CurrencyChoiceAdapter currencyChoiceAdapter;
    private ArrayList<CurrencyType> currencyType = new ArrayList<>();
    ImageView cancel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selector);
        currencyList =findViewById(R.id.rvCurrency);
        cancel = findViewById(R.id.cancel);
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
        currencyChoiceAdapter = new CurrencyChoiceAdapter(this);
        currencyChoiceAdapter.setCurrencies(currencyType);
        currencyList.setLayoutManager(new LinearLayoutManager(this));
        currencyList.setAdapter(currencyChoiceAdapter);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = Objects.requireNonNull(getAssets().open("currencies.json"));
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
    @Override
    public void  onCurrencyClick(CurrencyType type){
        Intent intent = new Intent();
        intent.putExtra("CURRENCY_TYPE",type.getAbr());
        setResult(RESULT_OK,intent);
        finish();
    }
}
