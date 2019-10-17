package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ravinada.cryptocompare.databinding.ActivityNewsDetailsBinding;

public class NewsDetailsActivity extends AppCompatActivity {

    ActivityNewsDetailsBinding binding;
    private String newsURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details);
        binding.toolbar.setTitle("News Details");

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

         newsURL = getIntent().getStringExtra("NEWS_URL");

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                binding.progress.setVisibility(View.GONE);
            }

        });

        binding.webView.loadUrl(newsURL);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(0,0);
        return true;
    }

}
