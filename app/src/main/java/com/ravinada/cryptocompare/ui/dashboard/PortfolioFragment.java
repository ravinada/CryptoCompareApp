package com.ravinada.cryptocompare.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.ravinada.cryptocompare.AddCoinFragment;
import com.ravinada.cryptocompare.AddPortfolioFragment;
import com.ravinada.cryptocompare.PortfolioSelectorFragment;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.InitialPortfolioBinding;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioCoin;
import com.ravinada.cryptocompare.viewmodels.PortfolioViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PortfolioFragment extends Fragment {
    private InitialPortfolioBinding binding;
    String portfolioSelectedName;
    List<PortfolioCoin> coins= new ArrayList<>();
    private List<Portfolio> portfolios = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    int flag =0;
    TextView portfolioName;

    public static PortfolioFragment newInstance() {
        return new PortfolioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.initial_portfolio,container,false);
        View view = Objects.requireNonNull(getActivity()).findViewById(R.id.txtPortfolioName);
        if (view instanceof TextView) {
            portfolioName = (TextView) view;
        }
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PortfolioViewModel portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel.class);
        portfolios = portfolioViewModel.getPortfolios();
        fragmentManager = getActivity().getSupportFragmentManager();
        if (portfolios.isEmpty()){
            portfolioName.setText("portfolios");
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.emptyPortfolio, AddPortfolioFragment.newInstance()).commit();
        }
        else {
            portfolioSelectedName = portfolioViewModel.getSelectedPortfolio();
            if (portfolioSelectedName.equalsIgnoreCase("")){
                portfolioName.setText("portfolios");
            }
            else {
            portfolioName.setText(portfolioSelectedName);}
            portfolioName.setTextColor(getActivity().getColor(R.color.colorBlack));
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.emptyPortfolio, AddCoinFragment.newInstance()).commit();
            portfolioName.setOnClickListener(view -> {
                if (flag==0){
                    flag =1;
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.addCoinFragment, PortfolioSelectorFragment.newInstance(),"remove-me").commit();}
                else if(flag==1){
                    flag=0;
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = fragmentManager.findFragmentByTag("remove-me");
                    if (fragment!=null){
                    fragmentTransaction.remove(fragment).commit();}
                }
            });
        }
    }
}
