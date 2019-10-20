package com.ravinada.cryptocompare.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.ravinada.cryptocompare.modelclasses.News;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.adapters.NewsListAdapter;
import com.ravinada.cryptocompare.databinding.FragmentNewsListBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment {
    private final String TAG = NewsListFragment.class.getSimpleName();
  FragmentNewsListBinding binding;
    String BASE_URL = "https://min-api.cryptocompare.com";
    private List<News> newsList = new ArrayList<>();

    private NewsListAdapter newsListAdapter;
    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);

        binding.swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        prepareNews();
                    }
                }
        );

        prepareNews();
    }

    private void prepareNews() {
        newsList.clear();
        String url = BASE_URL + "/data/v2/news/?lang=EN";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i);
                        News n = new News(display.getString("id"),
                                display.getString("title"),
                                display.getJSONObject("source_info").getString("name"),
                                display.getString("imageurl"),
                                display.getString("categories"),
                                display.getJSONObject("source_info").getString("img"),
                                display.getString("body"),
                                display.getLong("published_on"),
                                display.getString("url"));
                        newsList.add(n);
                    }

                    newsListAdapter = new NewsListAdapter(getActivity(), newsList);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.recyclerView.setAdapter(newsListAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(jsObjRequest);

    }

}
