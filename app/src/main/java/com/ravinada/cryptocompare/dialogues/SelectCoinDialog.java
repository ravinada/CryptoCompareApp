package com.ravinada.cryptocompare.dialogues;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.adapters.CoinSelectorAdapter;
import com.ravinada.cryptocompare.adapters.CurrencyAdapter;
import com.ravinada.cryptocompare.databinding.SelectCoinDialogBinding;
import com.ravinada.cryptocompare.modelclasses.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectCoinDialog extends DialogFragment implements CoinSelectorAdapter.SetCoin {
    SelectCoinDialogBinding binding;
    String coinSelected ="";
    String selectedCoinImg="";
    CoinSelectorAdapter coinSelectorAdapter;
    List<Currency> currencyList = new ArrayList<>();
    String IMAGE_URL = "https://www.cryptocompare.com";
    String URL = "https://min-api.cryptocompare.com/data/top/totalvolfull?limit=20&tsym=USD";

    public static SelectCoinDialog newInstance(){
        SelectCoinDialog fragSelectCoin = new SelectCoinDialog();
       return fragSelectCoin;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.select_coin_dialog,container,false);
        prepareCurrencies(URL);
        coinSelectorAdapter = new CoinSelectorAdapter(this);
        setCancelable(false);
        binding.cancel.setOnClickListener(view -> {
            dismiss();
        });
        return binding.getRoot();
    }

    private void prepareCurrencies(String url) {
        currencyList.clear();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i).getJSONObject("DISPLAY").getJSONObject("USD");
                        String image = IMAGE_URL + display.getString("IMAGEURL");
                        JSONObject coinInfo = js.getJSONObject(i).getJSONObject("CoinInfo");

                        Currency c = new Currency(coinInfo.getString("FullName"),
                                coinInfo.getString("Name"),
                                display.getString("TOTALVOLUME24HTO"),
                                display.getString("PRICE"), image);
                        currencyList.add(c);
                    }
                    coinSelectorAdapter.setCurrencyList(currencyList);
                    binding.selectCoinPurchaseType.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.selectCoinPurchaseType.setAdapter(coinSelectorAdapter);
                    Log.d("MainListFrag", "log");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Log.i("******", "Error"));
        queue.add(jsObjRequest);
    }

    @Override
    public void onCoinClick(Currency currency, int flag) {
        if(flag==1){
            coinSelected = currency.getCode();
            selectedCoinImg = currency.getImageURL();
            Intent intent = new Intent();
            intent.putExtra("name",coinSelected);
            intent.putExtra("url",currency.getImageURL());
            intent.putExtra("full_name",currency.getFullName());
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        }
    }
}
