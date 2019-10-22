package com.ravinada.cryptocompare;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.ravinada.cryptocompare.modelclasses.CurrencyDetailPOJO;
import com.ravinada.cryptocompare.modelclasses.DailyHistoricalData;
import com.ravinada.cryptocompare.room.FavouriteCoin;
import com.ravinada.cryptocompare.ui.dashboard.TopVolumeFragment;
import com.ravinada.cryptocompare.viewmodels.CoinDetailViewModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ravinada.cryptocompare.R.layout.marker_view;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

public class CurrencyDetailActivity extends AppCompatActivity implements OnChartValueSelectedListener, View.OnClickListener {
    private static final String TAG = CurrencyDetailActivity.class.getSimpleName();
    ImageView coinImage, detailBackSign;
    Button oneMinute, oneHour, oneDay, oneWeek, sixMonth;
    CurrencyDetailPOJO result;
    static List<DailyHistoricalData> dailyHistoricalDataList = new ArrayList<>();
    TextView coinName, currencySelector, currentCoinPrice, rateChg, follow, marketCap, totalVolume24h, directVolume24h, open24h, directVolumeSigned, lowHigh;
    String name, imageURL, selectedCurrency, fullName;
    String BASE_URL = "https://min-api.cryptocompare.com/data/pricemultifull?";
    static LineChart lineChart;
    CoinDetailViewModel coinDetailViewModel;
    public static String time = "histominute";
    public static final String DATA_SET = "dataSet";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);
        inflateViews();
        getIntent().getExtras();
        //coinDetailViewModel = ViewModelProviders.of(this).get(CoinDetailViewModel.class);
        //dailyHistoricalDataList = coinDetailViewModel.get(this,"BTC","USD",10);
        name = Objects.requireNonNull(getIntent().getExtras()).getString("COIN_TAG");
        imageURL = getIntent().getExtras().getString("IMAGE_URL");
        fullName = getIntent().getExtras().getString("COIN_NAME");
        Picasso.get().load(imageURL).into(coinImage);
        coinName.setText(fullName);
        selectedCurrency = currencySelector.getText().toString();
        getCurrencyData(name, selectedCurrency);
        getGraphData(this, selectedCurrency, name, 10);

        currencySelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrencyDetailActivity.this, CurrencySelector.class);
                startActivityForResult(intent, 1);
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
                if (checkExistance(name)) {
                    deleteCoin();
                    follow.setText("Follow");
                    follow.setTextColor(getColor(R.color.colorBlack));
                    follow.setBackground(getDrawable(R.drawable.rounded_border_follow));
                } else {
                    saveCoin();
                    follow.setText("Following");
                    follow.setTextColor(getColor(R.color.white));
                    follow.setBackground(getDrawable(R.drawable.rounded_drawable_following));
                }
            }
        });
        if (checkExistance(name)) {
            follow.setText("Following");
            follow.setTextColor(getColor(R.color.white));
            follow.setBackground(getDrawable(R.drawable.rounded_drawable_following));
        } else {
            follow.setText("Follow");
            follow.setTextColor(getColor(R.color.colorBlack));
            follow.setBackground(getDrawable(R.drawable.rounded_border_follow));
        }

        oneMinute.setOnClickListener(this);
        oneHour.setOnClickListener(this);
        oneDay.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        selectedCurrency = currencySelector.getText().toString();
        getCurrencyData(name, selectedCurrency);
        getGraphData(this, selectedCurrency, name, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            assert data != null;
            String currencyType = data.getStringExtra("CURRENCY_TYPE");
            currencySelector.setText(currencyType);
        }
    }

    public void inflateViews() {
        coinImage = findViewById(R.id.detail_coin_symbol_image);
        detailBackSign = findViewById(R.id.detail_backSign);
        lineChart = findViewById(R.id.line_chart);
        oneMinute = findViewById(R.id.one_minute);
        oneHour = findViewById(R.id.one_hour);
        oneDay = findViewById(R.id.one_day);
        oneWeek = findViewById(R.id.one_week);
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

    private void getCurrencyData(final String fsym, final String tsym) {
        String url = BASE_URL + "fsyms=" + fsym + "&tsyms=" + tsym;
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
        }, error -> {
        });
        queue.add(jsonObjectRequest);
    }

    private void saveCoin() {
        coinDetailViewModel = ViewModelProviders.of(this).get(CoinDetailViewModel.class);
        FavouriteCoin favouriteCoin = new FavouriteCoin(name, fullName, imageURL,
                currentCoinPrice.getText().toString(), rateChg.getText().toString()
                , marketCap.getText().toString(), totalVolume24h.getText().toString(),
                directVolume24h.getText().toString(), open24h.getText().toString(),
                directVolumeSigned.getText().toString(), lowHigh.getText().toString(), true);
        coinDetailViewModel.insert(favouriteCoin);
    }

    private void deleteCoin() {

        FavouriteCoin favouriteCoin = new FavouriteCoin(name, fullName, imageURL,
                currentCoinPrice.getText().toString(), rateChg.getText().toString()
                , marketCap.getText().toString(), totalVolume24h.getText().toString(),
                directVolume24h.getText().toString(), open24h.getText().toString(),
                directVolumeSigned.getText().toString(), lowHigh.getText().toString(), true);
        coinDetailViewModel.delete(favouriteCoin);
    }

    private Boolean checkExistance(String tag) {
        coinDetailViewModel = ViewModelProviders.of(this).get(CoinDetailViewModel.class);
        if (coinDetailViewModel.existance(tag) == null) {
            return false;
        }
        return coinDetailViewModel.existance(tag);
    }

    public void getGraphData(Context graphContext, String fsym, String tsym, int limit) {
        dailyHistoricalDataList.clear();
        final String DAILY_DATA_URL = "https://min-api.cryptocompare.com/data/v2/histoday?fsym=";
        String url = DAILY_DATA_URL + fsym + "&tsym=" + tsym + "&limit" + limit;
        RequestQueue queue = Volley.newRequestQueue(graphContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject data = response.getJSONObject("Data");
                JSONArray dailyDataObjects = data.getJSONArray("Data");
                for (int iterator = 0; iterator <= dailyDataObjects.length() - 1; iterator++) {
                    JSONObject dailyData = dailyDataObjects.getJSONObject(iterator);
                    DailyHistoricalData dailyHistoricalData = new DailyHistoricalData();
                    dailyHistoricalData.setTime(dailyData.getLong("time"));
                    dailyHistoricalData.setClose(dailyData.getDouble("close"));
                    dailyHistoricalData.setHigh(dailyData.getDouble("high"));
                    dailyHistoricalData.setLow(dailyData.getDouble("low"));
                    dailyHistoricalDataList.add(dailyHistoricalData);
                }
                plotGraph();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
        });
        queue.add(jsonObjectRequest);

    }

    public void plotGraph() {
        ArrayList<Entry> dataVal = new ArrayList<Entry>();
        if (dailyHistoricalDataList != null) {
            int i = 0;
            for (DailyHistoricalData dailyHistoricalData : dailyHistoricalDataList) {
                Log.e("plotGraphMonthly: ", dailyHistoricalData.getClose() + "");
                dataVal.add(new Entry(i, Float.valueOf(dailyHistoricalData.getClose() + "")));
                i++;
            }
        }

        final ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        LineDataSet lineDataSet = new LineDataSet(dataVal, DATA_SET);
        dataSets.add(lineDataSet);
        //for x-axis-
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //for Y-axis-
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        //rightside y-axis
        YAxis yAxisright = lineChart.getAxisRight();
        yAxisright.setEnabled(false);
        //color
        lineDataSet.setFillAlpha(80);
        lineDataSet.setFillColor(Color.RED);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawValues(false);
        //setting data
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        //hide background grid lines
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        lineChart.setPinchZoom(true);
        lineChart.setTouchEnabled(true);
        lineChart.getDescription().setEnabled(false);

        lineChart.setTouchEnabled(true);
        lineChart.setOnChartValueSelectedListener(CurrencyDetailActivity.this);

        MyMarkerView mv = new MyMarkerView(getApplicationContext(), marker_view);
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.invalidate();

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_minute: {
                time = "histominute";
                oneMinute.setTextColor(getResources().getColor(R.color.colorWhite));
                oneMinute.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                oneDay.setTextColor(getResources().getColor(R.color.colorBlack));
                oneDay.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                oneHour.setTextColor(getResources().getColor(R.color.colorBlack));
                oneHour.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                plotGraph();
                break;
            }
            case R.id.one_hour: {
                time = "histohour";
                oneHour.setTextColor(getResources().getColor(R.color.colorWhite));
                oneHour.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                oneDay.setTextColor(getResources().getColor(R.color.colorBlack));
                oneDay.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                oneMinute.setTextColor(getResources().getColor(R.color.colorBlack));
                oneMinute.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                plotGraph();
                break;
            }
            case R.id.one_day: {
                time = "histoday";
                oneDay.setTextColor(getResources().getColor(R.color.colorWhite));
                oneDay.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                oneMinute.setTextColor(getResources().getColor(R.color.colorBlack));
                oneMinute.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                oneHour.setTextColor(getResources().getColor(R.color.colorBlack));
                oneHour.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                plotGraph();
                break;
            }
        }
    }
}
