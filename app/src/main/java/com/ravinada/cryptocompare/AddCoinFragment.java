package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ravinada.cryptocompare.databinding.FragmentCoinBinding;
import com.ravinada.cryptocompare.dialogues.AddCoinDialogue;

public class AddCoinFragment extends Fragment {
    private FragmentCoinBinding binding;
    public static AddCoinFragment newInstance(){
        return new AddCoinFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_coin,container,false);
        binding.btnAddFirstCoin.setOnClickListener(view -> {
            showAddPortfolioDialog();
        });
        return binding.getRoot();
    }
    private void showAddPortfolioDialog(){
        FragmentManager manager = getFragmentManager();
        AddCoinDialogue addPortfolioDialogue = new AddCoinDialogue();
        assert manager != null;
        addPortfolioDialogue.show(manager,"ADD_COIN_DIALOG");
    }
}
