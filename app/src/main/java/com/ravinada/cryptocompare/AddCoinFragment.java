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

import com.ravinada.cryptocompare.databinding.FragmentCoinBinding;
import com.ravinada.cryptocompare.dialogues.AddCoinDialogue;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioCoin;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import java.util.List;
import java.util.Objects;

public class AddCoinFragment extends Fragment {
    private FragmentCoinBinding binding;
    String name;
    PortfolioViewModel portfolioViewModel;
    int portfolioId;
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
    private void showAddCoinDialog(){
        FragmentManager manager = getFragmentManager();
        AddCoinDialogue addCoinDialogue = AddCoinDialogue.newInstance(portfolioId);
        assert manager != null;
        addCoinDialogue.show(manager,"ADD_COIN_DIALOG");
    }
}
