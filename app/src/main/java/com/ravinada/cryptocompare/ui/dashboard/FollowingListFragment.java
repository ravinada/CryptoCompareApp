package com.ravinada.cryptocompare.ui.dashboard;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ravinada.cryptocompare.CoinViewModel;
import com.ravinada.cryptocompare.Currency;
import com.ravinada.cryptocompare.CurrencyAdapter;
import com.ravinada.cryptocompare.FavouriteCurrencyAdapter;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.FragmentFollowingListBinding;
import com.ravinada.cryptocompare.room.FavouriteCoin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FollowingListFragment extends Fragment {
    private final String TAG = FollowingListFragment.class.getSimpleName();

    private FragmentFollowingListBinding binding;
    private String type;
    private FavouriteCurrencyAdapter adapter;
    private CoinViewModel coinViewModel;
    private List<FavouriteCoin> favouriteCoins;

    public static FollowingListFragment newInstance() {
        return new FollowingListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        coinViewModel = ViewModelProviders.of(this).get(CoinViewModel.class);
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
        coinViewModel = ViewModelProviders.of(this).get(CoinViewModel.class);
        favouriteCoins= coinViewModel.getFavouriteCoinLiveData();
        adapter = new FavouriteCurrencyAdapter(getActivity(),favouriteCoins);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setFavouriteCurrencyAdapter(adapter);
    }
}
