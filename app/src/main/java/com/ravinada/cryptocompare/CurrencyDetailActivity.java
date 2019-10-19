package com.ravinada.cryptocompare;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.github.mikephil.charting.charts.LineChart;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.ravinada.cryptocompare.room.FavouriteCoin;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

public class CurrencyDetailActivity extends AppCompatActivity  {
    ImageView coinImage, detailBackSign;
    Button oneMonth,oneHour,oneDay,oneWeek,sixMonth;
    CurrencyDetailPOJO result;
    TextView coinName,currencySelector,currentCoinPrice,rateChg,follow,marketCap,totalVolume24h,directVolume24h,open24h,directVolumeSigned,lowHigh;
    String name,imageURL,selectedCurrency,fullName;
    String BASE_URL ="https://min-api.cryptocompare.com/data/pricemultifull?";
    LineChart lineChart;
    CoinViewModel coinViewModel;
    public static String time="histominute";
    public static final String DATA_SET = "dataSet";
    GraphView graph;
    ArrayList<String> data = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);
        inflateViews();
        getIntent().getExtras();
        name = Objects.requireNonNull(getIntent().getExtras()).getString("COIN_TAG");
        imageURL = getIntent().getExtras().getString("IMAGE_URL");
        fullName = getIntent().getExtras().getString("COIN_NAME");
        Picasso.get().load(imageURL).into(coinImage);
        coinName.setText(fullName);
        selectedCurrency = currencySelector.getText().toString();
        getCurrencyData(name,selectedCurrency);


        currencySelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrencyDetailActivity.this, CurrencySelector.class);
                startActivityForResult(intent,1);
            }
        });
        detailBackSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(CurrencyDetailActivity.this, DashboardActivity.class);
               startActivity(intent);
            }
        });
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkExistance(name)){
                    deleteCoin();
                    follow.setText("Follow");
                    follow.setTextColor(getColor(R.color.colorBlack));
                    follow.setBackground(getDrawable(R.drawable.rounded_border_follow));
                }else {
                    saveCoin();
                    follow.setText("Following");
                    follow.setTextColor(getColor(R.color.white));
                    follow.setBackground(getDrawable(R.drawable.rounded_drawable_following));
                }
            }
        });
        if(checkExistance(name)){
            follow.setText("Following");
            follow.setTextColor(getColor(R.color.white));
            follow.setBackground(getDrawable(R.drawable.rounded_drawable_following));
        }else{
            follow.setText("Follow");
            follow.setTextColor(getColor(R.color.colorBlack));
            follow.setBackground(getDrawable(R.drawable.rounded_border_follow));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        selectedCurrency = currencySelector.getText().toString();
        getCurrencyData(name,selectedCurrency);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode ==RESULT_OK){
            assert data != null;
            String currencyType = data.getStringExtra("CURRENCY_TYPE");
            currencySelector.setText(currencyType);
        }
        plotGraph();
    }
    public void inflateViews(){
        coinImage = findViewById(R.id.detail_coin_symbol_image);
        detailBackSign =findViewById(R.id.detail_backSign);
        graph = findViewById(R.id.graph);
        oneMonth=findViewById(R.id.one_month);
        oneHour=findViewById(R.id.one_hour);
        oneDay=findViewById(R.id.one_day);
        oneWeek =findViewById(R.id.one_week);
        sixMonth = findViewById(R.id.six_month);
        coinName = findViewById(R.id.detail_coin_Name);
        currencySelector = findViewById(R.id.currencyTypeSelector);
        currentCoinPrice = findViewById(R.id.detail_current_coin_price);
        rateChg = findViewById(R.id.detail_chg24h);
        follow = findViewById(R.id.detail_follow);
        marketCap = findViewById(R.id.detail_market_cap_number);
        totalVolume24h = findViewById(R.id.detail_total_vol_24h_number);
        directVolume24h = findViewById(R.id.detail_direct_vol_24h_number);
        open24h = findViewById(R.id.detail_open_24h_number);
        directVolumeSigned = findViewById(R.id.detail_direct_sign_number);
        lowHigh = findViewById(R.id.detail_low_24h_number);
    }
    private void getCurrencyData(final String fsym, final String tsym){
            String url = BASE_URL + "fsyms="+fsym+"&tsyms="+tsym;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {

                JSONObject details = response.getJSONObject("DISPLAY");
                JSONObject coin = details.getJSONObject(fsym);
                JSONObject currency = coin.getJSONObject(tsym);
                result = new CurrencyDetailPOJO(currency.getString("PRICE"),
                        currency.getString("CHANGEPCT24HOUR"),
                        currency.getString("MKTCAP"),
                        currency.getString("TOTALVOLUME24H"),
                        currency.getString("VOLUME24HOUR"),
                        currency.getString("OPEN24HOUR"),
                        currency.getString("VOLUME24HOURTO"),
                        currency.getString("LOWHOUR"),
                        currency.getString("HIGHHOUR")
                        );
                currentCoinPrice.setText(result.getCurrentCoinPrice());
                rateChg.setText(result.getRateChg());
                marketCap.setText(result.getMarketCap());
                totalVolume24h.setText(result.getTotalVolume24h());
                directVolume24h.setText(result.getDirectVolume24h());
                open24h.setText(result.getOpen24h());
                directVolumeSigned.setText(result.getDirectVolumeSigned());
                lowHigh.setText(String.format("%s/%s", result.getLow(), result.getHigh()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonObjectRequest);
    }
    private void saveCoin() {
        coinViewModel = ViewModelProviders.of(this).get(CoinViewModel.class);
        FavouriteCoin favouriteCoin = new FavouriteCoin(name,fullName,imageURL,
                currentCoinPrice.getText().toString(),rateChg.getText().toString()
                ,marketCap.getText().toString(),totalVolume24h.getText().toString(),
                directVolume24h.getText().toString(),open24h.getText().toString(),
                directVolumeSigned.getText().toString(),lowHigh.getText().toString(),true);
        coinViewModel.insert(favouriteCoin);
    }
    private void deleteCoin(){
        coinViewModel = ViewModelProviders.of(this).get(CoinViewModel.class);
        FavouriteCoin favouriteCoin = new FavouriteCoin(name,fullName,imageURL,
                currentCoinPrice.getText().toString(),rateChg.getText().toString()
                ,marketCap.getText().toString(),totalVolume24h.getText().toString(),
                directVolume24h.getText().toString(),open24h.getText().toString(),
                directVolumeSigned.getText().toString(),lowHigh.getText().toString(),true);
        coinViewModel.delete(favouriteCoin);
    }
    private Boolean checkExistance(String tag){
        coinViewModel = ViewModelProviders.of(this).get(CoinViewModel.class);
        if(coinViewModel.existance(tag)==null){
            return false;
        }
        return coinViewModel.existance(tag);
    }
    private void plotGraph() {
        String GRAPH_BASE_URL = "https://min-api.cryptocompare.com";
//        String graphURL = GRAPH_BASE_URL + "/data/histoday?fsym="+ data.get(1) + "&tsym=USD&limit=7";
        String graphURL = BASE_URL + "/data/top/histoday?limit=20&tsym=USD";

        RequestQueue queue = Volley.newRequestQueue(CurrencyDetailActivity.this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, graphURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("****dfsfds**", "Error");

                    JSONArray js = response.getJSONArray("Data");
                    ArrayList<String> date = new ArrayList<>();
                    ArrayList<Double> price = new ArrayList<>();
                    DataPoint[] values = new DataPoint[js.length()];
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject data = js.getJSONObject(i);
                        date.add(getDate(data.getLong("time")));
                        price.add(data.getDouble("close"));
                        DataPoint v = new DataPoint(new Date(data.getLong("time")).getTime(), price.get(i));
                        values[i] = v;
                    }
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(values);
                    Log.i("***sdf***", "Error");

                    graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                        @Override
                        public String formatLabel(double value, boolean isValueX) {
                            if (isValueX) {
                                // show normal x values
                                return getDate(new Double(value).longValue());
                            } else {

                                // show currency for y values
                                return super.formatLabel(value, isValueX);
                            }

                        }
                    });
                    Log.i("****fd**", "Error");

                    graph.addSeries(series);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Log.i("******", "Error"));

        queue.add(jsObjRequest);

    }
    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM", cal).toString();
        return date;
    }

}
