package com.ravinada.cryptocompare;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ravinada.cryptocompare.adapters.PortfolioSelectorAdapter;
import com.ravinada.cryptocompare.databinding.PortfolioSelectionFragmentBinding;
import com.ravinada.cryptocompare.dialogues.AddPortfolioDialogue;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import java.util.ArrayList;
import java.util.List;

public class PortfolioSelectorFragment extends Fragment implements PortfolioSelectorAdapter.SetPortfolio {
    private PortfolioSelectionFragmentBinding binding;
    private PortfolioViewModel portfolioViewModel;
    PortfolioSelectorAdapter portfolioSelectorAdapter;


    public static PortfolioSelectorFragment newInstance() {
        return new PortfolioSelectorFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.portfolio_selection_fragment,container,false);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        portfolioViewModel.getPortfolios().observe(this,portfolios -> {
            portfolioSelectorAdapter = new PortfolioSelectorAdapter(this, getActivity());
            portfolioSelectorAdapter.setPortfolios(portfolios);
            binding.rvPortfolioSelected.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.rvPortfolioSelected.setAdapter(portfolioSelectorAdapter);
            binding.btnScrollAddPortfolio.setOnClickListener(view -> {
                showAddPortfolioDialog();

            });
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        binding.transparentViewCoinSelector.setOnClickListener(view -> {
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().remove(PortfolioSelectorFragment.this).commit();
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPortfolioClick(Portfolio portfolio) {
        String selected = portfolio.getName();
        String previouslySelected = portfolioViewModel.getSelectedPortfolio();
        if(!selected.equalsIgnoreCase(previouslySelected)){
            portfolioViewModel.setSelected(selected);
            portfolioViewModel.setUnselected(previouslySelected);
        }
    }
    private void showAddPortfolioDialog(){
        Log.e("ara", "showAddPortfolioDialog: " );
        FragmentManager manager = getFragmentManager();
        AddPortfolioDialogue addPortfolioDialogue = new AddPortfolioDialogue();
        assert manager != null;
        addPortfolioDialogue.show(manager,"PORTFOLIO_DIALOG");
    }
}
