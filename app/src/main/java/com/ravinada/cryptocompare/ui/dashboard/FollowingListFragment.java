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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ravinada.cryptocompare.viewmodels.CoinDetailViewModel;
import com.ravinada.cryptocompare.FavouriteCurrencyAdapter;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.FragmentFollowingListBinding;
import com.ravinada.cryptocompare.room.FavouriteCoin;

import java.util.List;

public class FollowingListFragment extends Fragment {
    private final String TAG = FollowingListFragment.class.getSimpleName();

    private FragmentFollowingListBinding binding;
    private String type;
    private FavouriteCurrencyAdapter adapter;
    private CoinDetailViewModel coinDetailViewModel;
    private List<FavouriteCoin> favouriteCoins;

    public static FollowingListFragment newInstance() {
        return new FollowingListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        coinDetailViewModel = ViewModelProviders.of(this).get(CoinDetailViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_following_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        View view = getActivity().findViewById(R.id.currencyTag);
        if (view instanceof TextView) {
            TextView currencyType = (TextView) view;
            type = currencyType.getText().toString();
        }
        super.onActivityCreated(savedInstanceState);
        coinDetailViewModel = ViewModelProviders.of(this).get(CoinDetailViewModel.class);
        favouriteCoins= coinDetailViewModel.getFavouriteCoinLiveData();
        adapter = new FavouriteCurrencyAdapter(getActivity(),favouriteCoins);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setFavouriteCurrencyAdapter(adapter);
    }
}
