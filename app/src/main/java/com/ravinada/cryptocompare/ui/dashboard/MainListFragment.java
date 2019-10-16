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
import com.ravinada.cryptocompare.Currency;
import com.ravinada.cryptocompare.CurrencyAdapter;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.MainListFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainListFragment extends Fragment {
    private final String TAG = MainListFragment.class.getSimpleName();
    String BASE_URL = "https://min-api.cryptocompare.com";
    String IMAGE_URL = "https://www.cryptocompare.com";
    MainListFragmentBinding binding;
    String type;
    private CurrencyAdapter adapter;
    private List<Currency> currencyList = new ArrayList<>();
    private MainListViewModel mViewModel;

    public static MainListFragment newInstance() {
        return new MainListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_list_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        View view = getActivity().findViewById(R.id.currencyTag);
        if( view instanceof TextView ) {
            TextView currencyType = (TextView) view;
            type = currencyType.getText().toString();
        }
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainListViewModel.class);
        // TODO: Use the ViewModel
        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
//        ArrayList<String> roomList = new ArrayList<>();
//        roomList.addAll(mViewModel.getLikeList());
        String url = BASE_URL + "/data/top/totalvolfull?limit=20&tsym="+type;
        prepareCurrencies(url);
//        RoomListAdapter adapter = new RoomListAdapter(getActivity(), roomList, new ListItemClickListener() {
//            @Override
//            public void onItemClick() {
//                Log.i(TAG, "onItemClick: ");
//
////                getActivity().getSupportFragmentManager().beginTransaction()
////                        .replace(R.id.main_frame_layout, PlayVideoAudioFragment.newInstance(), MainListFragment.class.getSimpleName())
////                        .commit();
//
//            }
//        });

        binding.swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        String url = BASE_URL + "/data/top/totalvolfull?limit=20&tsym="+type;
                        prepareCurrencies(url);
                    }
                }
        );

        // getPost();

    }
//    private void getPost(){
//        Observable<PostRequestResponse> postObservable = WebServiceCaller.getClient().getPost("H2345DSFDSSDFSS$$#","19,21,22.23,24,25,26","hindi,english","","video","",1);
//        postObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(result -> result.getData())
//                .subscribe(this::handlePostResults, this::handlePostError);
//    }

    //    private void handlePostResults(List<PostRequestResponse.DataItem> postList) {
//        Log.i(TAG, "handleResults: POST LIST SIZE - "+postList.size());
//
//        //CategoryHorizontalListAdapter categoryAdapter = new CategoryHorizontalListAdapter(getActivity(),postList , this);
//        //binding.setCategoryAdapter(categoryAdapter);
//    }
//
//    private void handlePostError(Throwable t) {
//        Log.i(TAG, "handleError: "+t.getMessage());
//    }--
    private void prepareCurrencies(String url) {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        currencyList.clear();
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
                                display.getString("PRICE"),
                                display.getString("OPENDAY"), image);
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("******", "Error");
            }
        });

        queue.add(jsObjRequest);

    }
}
