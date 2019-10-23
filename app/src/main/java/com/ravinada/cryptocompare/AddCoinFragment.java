package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ravinada.cryptocompare.adapters.PortfolioCoinAdapter;
import com.ravinada.cryptocompare.databinding.FragmentCoinBinding;
import com.ravinada.cryptocompare.dialogues.AddCoinDialogue;
import com.ravinada.cryptocompare.modelclasses.UserPortfolioCoins;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioCoin;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddCoinFragment extends Fragment {
    private FragmentCoinBinding binding;
    String name;
    List<UserPortfolioCoins> userPortfolioCoins = new ArrayList<>();
    PortfolioViewModel portfolioViewModel;
    int portfolioId;
    PortfolioCoinAdapter portfolioCoinAdapter;
    public static AddCoinFragment newInstance(){
        return new AddCoinFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_coin,container,false);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        portfolioId = portfolioViewModel.getIdForSelectedName();
        portfolioViewModel.getPortfolioCoinsForId(portfolioId).observe(this,portfolioCoins ->{
            if(portfolioCoins.isEmpty()){
                binding.tvNoCoinAdded.setVisibility(View.VISIBLE);
                binding.btnAddCoin.setVisibility(View.INVISIBLE);
                binding.btnAddFirstCoin.setVisibility(View.VISIBLE);
            }else {
                generateCoinList();
                binding.tvNoCoinAdded.setVisibility(View.INVISIBLE);
                binding.btnAddFirstCoin.setVisibility(View.INVISIBLE);
                binding.btnAddCoin.setVisibility(View.VISIBLE);
            }
        } );

        binding.btnAddFirstCoin.setOnClickListener(view1 -> {
           showAddCoinDialog();
        });
        binding.btnAddCoin.setOnClickListener(view12 -> {
            showAddCoinDialog();
        });

        return binding.getRoot();
    }
    private void generateCoinList() {
        userPortfolioCoins.clear();
        portfolioViewModel.getCurrencyForSelectedPortfolio().observe(this,portfolioCurrency->{
            portfolioViewModel.getPortfolioCoinsForId(portfolioId).observe(this,portfolioCoins -> {
                for(int iterator=0;iterator<portfolioCoins.size();iterator++){
                    PortfolioCoin portfolioCoin = portfolioCoins.get(iterator);
                    String coinPurchaseCurrency=portfolioCoins.get(iterator).getCurrencyTag();
                    String coinTag=portfolioCoins.get(iterator).getBuyTag();
                    String url ="https://min-api.cryptocompare.com/data/price?fsym="+coinTag+"&tsyms="+portfolioCurrency+","+portfolioCoins;
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                double valueInPortfolioCurrency = response.getLong(portfolioCurrency);
                                double valueInPurchasedCurrency = response.getLong(coinPurchaseCurrency);
                                float valueOfHoldingInPortfolioCurrency = (float) valueInPortfolioCurrency*portfolioCoin.getAmount();
                                float valueOfHoldingInPurchaseCurrency = (float) valueInPurchasedCurrency*portfolioCoin.getAmount();
                                float plPortfolioCurrency = valueOfHoldingInPortfolioCurrency -(float) ((valueInPurchasedCurrency/valueInPortfolioCurrency)*portfolioCoin.getBuyPrice());
                                float plPurchaseCurrency = valueOfHoldingInPurchaseCurrency -portfolioCoin.getBuyPrice();
                                int pl;
                                if(valueOfHoldingInPurchaseCurrency>plPurchaseCurrency){
                                    pl=1;
                                }else {
                                    pl=0;
                                }
                                UserPortfolioCoins userPortfolioCoin = new UserPortfolioCoins(coinTag,portfolioCoin.getImageUrl()
                                        ,portfolioCoin.getFullName(),valueOfHoldingInPortfolioCurrency,
                                        valueOfHoldingInPurchaseCurrency,plPortfolioCurrency,plPurchaseCurrency,portfolioCoin.getCoinId(),pl,(float)valueInPortfolioCurrency,false);
                                userPortfolioCoins.add(userPortfolioCoin);
                                float j=0,k=0;
                                int l;
                                for(int i=0;i<userPortfolioCoins.size();i++){
                                    j= j+userPortfolioCoins.get(i).getValueHoldingsPortfolioCurrency();
                                    l=userPortfolioCoins.get(i).getPl();
                                    if(l==1){
                                        k=k+userPortfolioCoins.get(i).getPlPortfolioCurrency();}
                                    else{
                                        k=k-userPortfolioCoins.get(i).getPlPortfolioCurrency();
                                    }
                                }
                                binding.txtTotalValue1.setText(Float.toString(j));
                                binding.txtProfitLoss1.setText(Float.toString(k));
                                if(j>k){
                                    binding.txtProfitLoss1.setTextColor(getResources().getColor(R.color.colorRed));
                                }
                                else{
                                    binding.txtProfitLoss1.setTextColor(getActivity().getColor(R.color.colorGreen));
                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, error -> Log.i("******", "Error"));
                    queue.add(jsObjRequest);   }
                portfolioCoinAdapter = new PortfolioCoinAdapter(userPortfolioCoins);
                binding.rvCoinData.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvCoinData.setAdapter(portfolioCoinAdapter);
            });
        });
    }

    private void showAddCoinDialog(){
        FragmentManager manager = getFragmentManager();
        AddCoinDialogue addCoinDialogue = AddCoinDialogue.newInstance(portfolioId);
        assert manager != null;
        addCoinDialogue.show(manager,"ADD_COIN_DIALOG");
    }
}
