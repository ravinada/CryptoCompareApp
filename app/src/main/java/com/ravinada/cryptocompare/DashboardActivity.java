package com.ravinada.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.ravinada.cryptocompare.databinding.ActivityDashboardBinding;
import com.ravinada.cryptocompare.ui.dashboard.MainListFragment;
import com.ravinada.cryptocompare.ui.dashboard.NewsListFragment;
import com.ravinada.cryptocompare.ui.dashboard.PortfolioFragment;


public class DashboardActivity extends AppCompatActivity {
    TextView currencySelector;
    ActivityDashboardBinding binding;
    private ViewPager viewPager;
    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        currencySelector = findViewById(R.id.currencyTag);
        currencySelector.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, CurrencySelector.class);
            startActivityForResult(intent,1);
        });

        binding.bottomNav.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.tabMenuWatchList:
                            binding.viewPager.setCurrentItem(0);
                            break;
                        case R.id.tabMenuPortfolio:
                            binding.viewPager.setCurrentItem(1);
                            break;
                        case R.id.tabMenuNews:
                            binding.viewPager.setCurrentItem(2);
                            break;
                    }
                    return false;
                });
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    binding.bottomNav.getMenu().getItem(0).setChecked(false);
                }
                binding.bottomNav.getMenu().getItem(position).setChecked(true);
                prevMenuItem = binding.bottomNav.getMenu().getItem(position);


                if (position == 1){
                    binding.txtPortfolioName.setVisibility(View.VISIBLE);
                    binding.searchBar.setVisibility(View.GONE);
                    binding.currencyTag.setVisibility(View.GONE);
                    binding.portfolioEdit.setVisibility(View.VISIBLE);
                }else if (position == 2){
                    binding.searchBar.setVisibility(View.GONE);
                    binding.txtPortfolioName.setVisibility(View.VISIBLE);
                    binding.txtPortfolioName.setText("News");
                    binding.currencyTag.setVisibility(View.GONE);
                    binding.portfolioEdit.setVisibility(View.GONE);

                }else {
                    binding.searchBar.setVisibility(View.VISIBLE);
                    binding.txtPortfolioName.setVisibility(View.GONE);
                    binding.currencyTag.setVisibility(View.VISIBLE);
                    binding.portfolioEdit.setVisibility(View.GONE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.addFragment(MainListFragment.newInstance(),"Watchlist");
        viewPagerAdapter.addFragment(PortfolioFragment.newInstance(),"Portfolio");
        viewPagerAdapter.addFragment(NewsListFragment.newInstance(),"News");
        binding.viewPager.setAdapter(viewPagerAdapter);
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
