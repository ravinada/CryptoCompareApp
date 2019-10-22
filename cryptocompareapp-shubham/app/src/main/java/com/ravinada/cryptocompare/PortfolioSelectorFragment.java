package com.ravinada.cryptocompare;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ravinada.cryptocompare.adapters.PortfolioSelectorAdapter;
import com.ravinada.cryptocompare.databinding.PortfolioSelectionFragmentBinding;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import java.util.ArrayList;
import java.util.List;

public class PortfolioSelectorFragment extends Fragment implements PortfolioSelectorAdapter.SetPortfolio {
    private PortfolioSelectionFragmentBinding binding;
    PortfolioViewModel portfolioViewModel;
    List<Portfolio> portfolios = new ArrayList<>();
    PortfolioSelectorAdapter portfolioSelectorAdapter;
    String selected ;
    String previouslySelected;

    public static PortfolioSelectorFragment newInstance() {
        return new PortfolioSelectorFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.portfolio_selection_fragment,container,false);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        portfolios = portfolioViewModel.getPortfolios();
        portfolioSelectorAdapter = new PortfolioSelectorAdapter(this,getActivity());
        portfolioSelectorAdapter.setPortfolios(portfolios);
        binding.rvPortfolioSelected.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvPortfolioSelected.setAdapter(portfolioSelectorAdapter);
        binding.transparentViewCoinSelector.setOnClickListener(view -> {
            getFragmentManager().beginTransaction().remove(PortfolioSelectorFragment.this).commit();
        });
    }

    @Override
    public void onPortfolioClick(Portfolio portfolio) {
        selected = portfolio.getName();
        previouslySelected = portfolioViewModel.getSelectedPortfolio();
        if(selected.equalsIgnoreCase(previouslySelected)){

        }
        else{
            portfolioViewModel.setSelected(selected);
            portfolioViewModel.setUnselected(previouslySelected);
        }
    }
}
