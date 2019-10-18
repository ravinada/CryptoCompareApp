package com.ravinada.cryptocompare.ui.dashboard;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ravinada.cryptocompare.News;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.adapters.NewsListAdapter;
import com.ravinada.cryptocompare.databinding.FragmentPotfolioBinding;



import java.util.ArrayList;
import java.util.List;

public class PortfolioFragment extends Fragment {
    private final String TAG = PortfolioFragment.class.getSimpleName();

    FragmentPotfolioBinding binding;
    String BASE_URL = "https://min-api.cryptocompare.com";
    private List<News> newsList = new ArrayList<>();
    private NewsListAdapter newsListAdapter;

    public static PortfolioFragment newInstance() {
        return new PortfolioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_potfolio, container, false);
        //  binding = DataBindingUtil.inflate(inflater, R.layout.portfolio_dialog, container, false);
        final View dialogView = inflater.inflate(R.layout.portfolio_dialog, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat_Light_NoActionBar));

        binding.btnAddPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.setView(dialogView);
                dialogBuilder.show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        binding.swipeRefresh.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
//
//                        // This method performs the actual data-refresh operation.
//                        // The method calls setRefreshing(false) when it's finished.
//                        prepareNews();
//                    }
//                }
//        );
//
//        prepareNews();
//    }


    }

}
