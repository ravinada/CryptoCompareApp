package com.ravinada.cryptocompare.dialogues;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ravinada.cryptocompare.CurrencyTypePurchaseAdapter;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.PortfolioDialogBinding;
import com.ravinada.cryptocompare.modelclasses.CurrencyType;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class AddPortfolioDialogue extends DialogFragment implements CurrencyTypePurchaseAdapter.CurrencyPurchaseType {
    private CurrencyTypePurchaseAdapter currencyChoiceAdapter;
    PortfolioDialogBinding binding;
    String currencySelection = "";
    PortfolioViewModel portfolioViewModel;
    int access =1;
    private ArrayList<CurrencyType> currencyType = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.portfolio_dialog,container,false);
        setCancelable(false);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        getCurrencyList();
        currencyChoiceAdapter = new CurrencyTypePurchaseAdapter(this,getActivity());
        currencyChoiceAdapter.setCurrencies(currencyType);
        binding.selectCurrencyPurchaseCoin.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.selectCurrencyPurchaseCoin.setAdapter(currencyChoiceAdapter);
        binding.btnSubmit.setOnClickListener(view -> {
            if(validator()){
                Portfolio portfolio= new Portfolio(binding.etPortfolioName.getText().toString(),
                        currencySelection,binding.etDescription.getText().toString(),access);
                portfolioViewModel.insertPortfolio(portfolio);
                dismiss();
            }
        });
       binding.cancel.setOnClickListener(view -> {
            dismiss();
       });
       binding.txtPrivate.setOnClickListener(view -> {
           access =1;
           binding.txtPrivate.setTextColor(getActivity().getColor(R.color.white));
           binding.txtPrivate.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_green));
           binding.txtPublic.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_grey));
           binding.txtPublic.setTextColor(getActivity().getColor(R.color.colorBlack));
       });
       binding.txtPublic.setOnClickListener(view -> {
           access =2;
           binding.txtPrivate.setTextColor(getActivity().getColor(R.color.colorBlack));
           binding.txtPrivate.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_grey));
           binding.txtPublic.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_green));
           binding.txtPublic.setTextColor(getActivity().getColor(R.color.white));
       });
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
    private boolean validator(){
        if(currencySelection.equalsIgnoreCase("") &&
                binding.etPortfolioName.getText().toString().equalsIgnoreCase("")){
            binding.etPortfolioName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.toString().equalsIgnoreCase("")){
                        binding.lblName.setText("Name is required");
                        binding.lblName.setTextColor(getActivity().getColor(R.color.colorRed));
                    }else {
                        binding.lblName.setText("Name");
                        binding.lblName.setTextColor(getActivity().getColor(R.color.colorBlack));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            binding.lblName.setText("Name is required");
            binding.lblName.setTextColor(getActivity().getColor(R.color.colorRed));
            binding.lblCurrency.setText("Currency is required");
            binding.lblCurrency.setTextColor(getActivity().getColor(R.color.colorRed));
            return false;
        }
        else if (!currencySelection.equalsIgnoreCase("" )&&
                binding.etPortfolioName.getText().toString().equalsIgnoreCase("")){
            binding.lblName.setText("Name is required");
            binding.lblName.setTextColor(getActivity().getColor(R.color.colorRed));
            binding.etPortfolioName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.toString().equalsIgnoreCase("")){
                        binding.lblName.setText("Name is required");
                        binding.lblName.setTextColor(getActivity().getColor(R.color.colorRed));
                    }else {
                        binding.lblName.setText("Name");
                        binding.lblName.setTextColor(getActivity().getColor(R.color.colorBlack));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            return false;
        }
        return true;
    }

    @Override
    public void onCurrencyClick(CurrencyType type) {
        currencySelection = type.getAbr();
        binding.lblCurrency.setText("Currency");
        binding.lblCurrency.setTextColor(getActivity().getColor(R.color.colorBlack));
    }
}
