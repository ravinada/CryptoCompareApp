package com.ravinada.cryptocompare.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.ravinada.cryptocompare.AddCoinFragment;
import com.ravinada.cryptocompare.AddPortfolioFragment;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.InitialPortfolioBinding;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioCoin;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import java.util.ArrayList;
import java.util.List;


public class PortfolioFragment extends Fragment {
    private final String TAG = PortfolioFragment.class.getSimpleName();
    private InitialPortfolioBinding binding;
    List<PortfolioCoin> coins= new ArrayList<>();
    private List<Portfolio> portfolios = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static PortfolioFragment newInstance() {
        return new PortfolioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.initial_portfolio,container,false);

        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PortfolioViewModel portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        portfolios = portfolioViewModel.getPortfolios();
        fragmentManager = getActivity().getSupportFragmentManager();
        if (portfolios.isEmpty()){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.emptyPortfolio, AddPortfolioFragment.newInstance()).commit();
        }
        else {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.emptyPortfolio, AddCoinFragment.newInstance()).commit();
        }
    }
}
