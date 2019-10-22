package com.ravinada.cryptocompare.dialogues;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioCoin;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class  AddCoinDialogue extends DialogFragment implements CurrencyTypePurchaseAdapter.CurrencyPurchaseType {
    CoinPortfolioDialogBinding binding;
    private PortfolioViewModel portfolioViewModel;
    private ArrayList<CurrencyType> currencyType = new ArrayList<>();
    private int portfolioId;
    private String coinSelected="",coinUrl,currencySelection="";

    public static AddCoinDialogue newInstance(int id){
        AddCoinDialogue fragAddCoin = new AddCoinDialogue();
        Bundle args = new Bundle();
        args.putInt("portfolio_id",id);
        fragAddCoin.setArguments(args);
        return fragAddCoin;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        portfolioId = getArguments().getInt("portfolio_id");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_portfolio_dialog,container,false);
        setCancelable(false);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        getCurrencyList();
        CurrencyTypePurchaseAdapter currencyChoiceAdapter = new CurrencyTypePurchaseAdapter(this, getActivity());
        currencyChoiceAdapter.setCurrencies(currencyType);
        binding.selectCurrencyPurchaseCoin.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.selectCurrencyPurchaseCoin.setAdapter(currencyChoiceAdapter);
        textValidation();
        binding.coinDialCancel.setOnClickListener(view -> {
           dismiss();
        });
        binding.btnSubmit.setOnClickListener(view -> {
            if (validation()){
                PortfolioCoin portfolioCoin = new PortfolioCoin(currencySelection,
                        Float.parseFloat(binding.editAmount.getText().toString()),
                Float.parseFloat(binding.editBuyInsertAmount.getText().toString()),coinSelected,
                        binding.editDate.getText().toString(),binding.editDescription.getText().toString(),portfolioId);
                portfolioViewModel.insertPortfolioCoin(portfolioCoin);
                dismiss();
            }
        });
        binding.selectCoin.setOnClickListener(view -> {
            showSelectCoinDialog();
        });
    return binding.getRoot();
    }
    private boolean validation(){
        if(!binding.editAmount.getText().toString().equalsIgnoreCase("")&&
                !binding.editBuyInsertAmount.getText().toString().equalsIgnoreCase("")
                && !coinSelected.equalsIgnoreCase("")
                && !currencySelection.equalsIgnoreCase("")){
                    return true;
        }
      return false;
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
    private void textValidation(){
        binding.editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equalsIgnoreCase("")){
                    binding.lblAmount.setText("Amount is required");
                    binding.lblAmount.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorRed));
                    binding.btnSubmit.setTextColor(getActivity().getColor(R.color.colorBlack));
                    binding.btnSubmit.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_grey));
                }
                else {
                    binding.lblAmount.setText("Amount");
                    binding.lblAmount.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBlack));
                    if(!binding.editBuyInsertAmount.getText().toString().equalsIgnoreCase("")
                            && !binding.selectCoin.getText().toString().equalsIgnoreCase("")
                    && !currencySelection.equalsIgnoreCase("")){
                        binding.btnSubmit.setTextColor(getActivity().getColor(R.color.white));
                        binding.btnSubmit.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_green));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.editBuyInsertAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equalsIgnoreCase("")){
                    binding.buyPrice.setText("Buy Price is required");
                    binding.buyPrice.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorRed));
                    binding.btnSubmit.setTextColor(getActivity().getColor(R.color.colorBlack));
                    binding.btnSubmit.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_grey));
                }
                else {
                    binding.buyPrice.setText("Buy Price");
                    binding.buyPrice.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBlack));
                    if(!binding.editAmount.getText().toString().equalsIgnoreCase("")
                            && !binding.selectCoin.getText().toString().equalsIgnoreCase("")
                            && !currencySelection.equalsIgnoreCase("")){
                        binding.btnSubmit.setTextColor(getActivity().getColor(R.color.white));
                        binding.btnSubmit.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_green));
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Override
    public void onCurrencyClick(CurrencyType type) {
        currencySelection = type.getAbr();
        if(!binding.editAmount.getText().toString().equalsIgnoreCase("")&&
                !binding.editBuyInsertAmount.getText().toString().equalsIgnoreCase("")
                && !binding.selectCoin.getText().toString().equalsIgnoreCase("")
                && !currencySelection.equalsIgnoreCase("")){
            binding.btnSubmit.setTextColor(getActivity().getColor(R.color.white));
            binding.btnSubmit.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_green));
        }
    }
    private void showSelectCoinDialog(){
       SelectCoinDialog selectCoinDialog = new SelectCoinDialog();
       selectCoinDialog.setTargetFragment(this,0);
       selectCoinDialog.show(getFragmentManager().beginTransaction(),"SELECT_COIN_DIALOG");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
        case 0:
        {
            if (resultCode== Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                coinSelected=bundle.getString("name");
                binding.selectCoin.setText(coinSelected);
                if(!binding.editAmount.getText().toString().equalsIgnoreCase("")&&
                        !binding.editBuyInsertAmount.getText().toString().equalsIgnoreCase("")
                        && !coinSelected.equalsIgnoreCase("")
                        && !currencySelection.equalsIgnoreCase("")){
                    binding.btnSubmit.setTextColor(getActivity().getColor(R.color.white));
                    binding.btnSubmit.setBackground(getActivity().getDrawable(R.drawable.rounded_rect_green));
                }
            }
            break;
        }
    }
    }
}
