package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentTopVolume extends Fragment {

    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL = "https://www.cryptocompare.com";
    private RecyclerView recyclerView;
    private CurrencyAdapter adapter;
    private List<Currency> currencyList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_new, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(container.getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        String url = BASE_URL + "/data/top/totalvolfull?limit=20&tsym=USD";
        prepareCurrencies(url,container);

        return v;

    }
    private void prepareCurrencies(String url,ViewGroup container) {
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i).getJSONObject("DISPLAY").getJSONObject("USD");
                        String image = IMAGE_URL + display.getString("IMAGEURL");
                        JSONObject coinInfo = js.getJSONObject(i).getJSONObject("CoinInfo");

                        Currency c = new Currency(coinInfo.getString("FullName"), coinInfo.getString("Name"), display.getString("PRICE"),
                                display.getString("LOWDAY"), display.getString("HIGHDAY"),
                                display.getString("OPENDAY"), image, coinInfo.getString("Algorithm"));
                        currencyList.add(c);
                        adapter = new CurrencyAdapter(getActivity(), currencyList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("******", "Error");
            }
        });
        queue.add(jsObjRequest);
    }
}
