package com.ravinada.cryptocompare.ui.dashboard;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ravinada.cryptocompare.modelclasses.Currency;
import com.ravinada.cryptocompare.adapters.CurrencyAdapter;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.FragmentTopvolumeListBinding;
import com.ravinada.cryptocompare.modelclasses.DailyHistoricalData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TopVolumeFragment extends Fragment {

    public String type;
    private final String TAG = TopVolumeFragment.class.getSimpleName();
    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL = "https://www.cryptocompare.com";
    FragmentTopvolumeListBinding binding;
    static List<DailyHistoricalData> dailyHistoricalDataList = new ArrayList<>();
    private CurrencyAdapter adapter;
    private List<Currency> currencyList = new ArrayList<>();

    public static TopVolumeFragment newInstance() {
        return new TopVolumeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_topvolume_list, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        View view = Objects.requireNonNull(getActivity()).findViewById(R.id.currencyTag);
        if (view instanceof TextView) {
            TextView currencyType = (TextView) view;
            type = currencyType.getText().toString();
        }
        super.onActivityCreated(savedInstanceState);
        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        String url = BASE_URL + "/data/top/totalvolfull?limit=20&tsym=" + type;
        prepareCurrencies(url);
        binding.swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        String url = BASE_URL + "/data/top/totalvolfull?limit=20&tsym=" + type;
                        prepareCurrencies(url);
                        binding.swipeRefresh.setRefreshing(false);
                    }
                }
        );

    }

    @Override
    public void onResume() {
        super.onResume();
        // binding.shimmerViewContainer.stopShimmerAnimation();
        View view = getActivity().findViewById(R.id.currencyTag);
        if (view instanceof TextView) {
            TextView currencyType = (TextView) view;
            type = currencyType.getText().toString();
        }
        String url = BASE_URL + "/data/top/totalvolfull?limit=20&tsym=" + type;
        prepareCurrencies(url);

    }

    @Override
    public void onPause() {
        super.onPause();
    }
    private void prepareCurrencies(String url) {
        currencyList.clear();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i).getJSONObject("DISPLAY").getJSONObject(type);
                        String image = IMAGE_URL + display.getString("IMAGEURL");
                        JSONObject coinInfo = js.getJSONObject(i).getJSONObject("CoinInfo");

                        Currency c = new Currency(coinInfo.getString("FullName"),
                                coinInfo.getString("Name"),
                                display.getString("TOTALVOLUME24HTO"),
                                display.getString("PRICE"), image);
                        currencyList.add(c);
                    }
                    Log.d("MainListFrag", "log");
                    adapter = new CurrencyAdapter(getActivity(), currencyList);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.setCurrencyAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, error -> Log.i("******", "Error"));

        queue.add(jsObjRequest);

    }


}
