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

import com.ravinada.cryptocompare.databinding.FragmentPotfolioBinding;
import com.ravinada.cryptocompare.dialogues.AddPortfolioDialogue;

public class AddPortfolioFragment extends Fragment {
    FragmentPotfolioBinding binding;
    public static AddPortfolioFragment newInstance() {
        return new AddPortfolioFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_potfolio,container,false);
        binding.btnAddPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddPortfolioDialog();
            }
        });
        return binding.getRoot();
    }
    private void showAddPortfolioDialog(){
        FragmentManager manager = getFragmentManager();
        AddPortfolioDialogue addPortfolioDialogue = new AddPortfolioDialogue();
        assert manager != null;
        addPortfolioDialogue.show(manager,"PORTFOLIO_DIALOG");
    }
}
