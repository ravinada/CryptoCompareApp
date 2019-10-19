package com.ravinada.cryptocompare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import androidx.viewpager.widget.ViewPager;
import com.ravinada.cryptocompare.databinding.ActivityDashboardBinding;
import com.ravinada.cryptocompare.ui.dashboard.MainListFragment;
import com.ravinada.cryptocompare.ui.dashboard.NewsListFragment;
import com.ravinada.cryptocompare.ui.dashboard.PortfolioFragment;


public class DashboardActivity extends AppCompatActivity {
    TextView currencySelector;
    ActivityDashboardBinding binding;
    private int[] tabIcons = {
            R.drawable.ic_home_black_16dp,
            R.drawable.ic_dashboard_black_16dp,
            R.drawable.ic_notifications_black_16dp
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        currencySelector = findViewById(R.id.currencyTag);
        currencySelector.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this,CurrencySelector.class);
            startActivityForResult(intent,1);
        });


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.addFragment(MainListFragment.newInstance(),"Watchlist");
        viewPagerAdapter.addFragment(PortfolioFragment.newInstance(),"Portfolio");
        viewPagerAdapter.addFragment(NewsListFragment.newInstance(),"News");
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tabsBottom.setupWithViewPager(binding.viewPager);
        setupTabIcons();
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void setupTabIcons() {
        binding.tabsBottom.getTabAt(0).setIcon(tabIcons[0]);
        binding.tabsBottom.getTabAt(1).setIcon(tabIcons[1]);
        binding.tabsBottom.getTabAt(2).setIcon(tabIcons[2]);
    }
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode ==RESULT_OK){
            String currencyType = data.getStringExtra("CURRENCY_TYPE");
            currencySelector.setText(currencyType);
        }
    }


}
