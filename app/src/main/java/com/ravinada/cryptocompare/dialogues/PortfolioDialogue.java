package com.ravinada.cryptocompare.dialogues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.CurrencyTypePurchaseAdapter;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.PortfolioDialogBinding;
import com.ravinada.cryptocompare.modelclasses.CurrencyType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class PortfolioDialogue extends DialogFragment implements CurrencyTypePurchaseAdapter.CurrencyPurchaseType {
    private CurrencyTypePurchaseAdapter currencyChoiceAdapter;
    PortfolioDialogBinding binding;
    private ArrayList<CurrencyType> currencyType = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.portfolio_dialog,container,false);
        setCancelable(false);
        setCurrencySelector();
        return binding.getRoot();
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = Objects.requireNonNull(getActivity().getAssets().open("currencies.json"));
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
    private void setCurrencySelector(){
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
                currency.setChecked(false);
                currencyType.add(currency);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        currencyChoiceAdapter = new CurrencyTypePurchaseAdapter(this);
        currencyChoiceAdapter.setCurrencies(currencyType);
        binding.selectCurrencyPurchaseCoin.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.selectCurrencyPurchaseCoin.setAdapter(currencyChoiceAdapter);
    }
    @Override
    public void onCurrencyClick(CurrencyType type) {

    }
}
