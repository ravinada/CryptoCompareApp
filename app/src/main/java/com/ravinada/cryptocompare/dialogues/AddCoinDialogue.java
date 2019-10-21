package com.ravinada.cryptocompare.dialogues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ravinada.cryptocompare.CurrencyTypePurchaseAdapter;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.CoinPortfolioDialogBinding;
import com.ravinada.cryptocompare.modelclasses.CurrencyType;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class AddCoinDialogue extends DialogFragment implements CurrencyTypePurchaseAdapter.CurrencyPurchaseType {
    CoinPortfolioDialogBinding binding;
    String currencySelection = "";
    PortfolioViewModel portfolioViewModel;
    private ArrayList<CurrencyType> currencyType = new ArrayList<>();
    private CurrencyTypePurchaseAdapter currencyChoiceAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_portfolio_dialog,container,false);
        setCancelable(false);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        getCurrencyList();
        currencyChoiceAdapter = new CurrencyTypePurchaseAdapter(this,getActivity());
        currencyChoiceAdapter.setCurrencies(currencyType);
        binding.selectCurrencyPurchaseCoin.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.selectCurrencyPurchaseCoin.setAdapter(currencyChoiceAdapter);
    return binding.getRoot();
    }
    private void getCurrencyList(){
        currencyType.clear();
        try {
            JSONObject currencies = new JSONObject(Objects.requireNonNull(loadJSONFromAsset()));
            JSONArray currencyArray = currencies.getJSONArray("currency_types");

            for (int i = 0; i < currencyArray.length(); i++) {
                CurrencyType currency = new CurrencyType();
                JSONObject currencyJson = currencyArray.getJSONObject(i);
                String abr = currencyJson.getString("abr");
                currency.setAbr(abr);
                currency.setChecked(false);
                currencyType.add(currency);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onCurrencyClick(CurrencyType type) {
        currencySelection = type.getAbr();
    }
}
