package com.ravinada.cryptocompare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ravinada.cryptocompare.databinding.ActivityDashboardBinding;
import com.ravinada.cryptocompare.ui.dashboard.MainListFragment;
import com.ravinada.cryptocompare.ui.notifications.NotificationsFragment;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.watch_list_bar:
                    setViewWatchList();
                    return true;
                case R.id.portfolio_bar:
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    fragmentTransaction1
                            .replace(R.id.llDashboardMain, new NotificationsFragment())
                            .commit();
                    return true;
                case R.id.newsBar: {
//                    FragmentManager fragmentManager2 = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
//                    fragmentTransaction2
//                            .replace(R.id.llDashboardMain, new NotificationsFragment())
//                            .commit();
                    Intent intent = new Intent(DashboardActivity.this, NewsActivity.class);
                    DashboardActivity.this.startActivity(intent);
                }
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        binding.tabs.setupWithViewPager(binding.viewpager, true);
        setViewWatchList();
        binding.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void setViewWatchList() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.addFragment(MainListFragment.newInstance(), "Following");
        viewPagerAdapter.addFragment(MainListFragment.newInstance(), "Top Volume");
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.viewpager.setCurrentItem(0);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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

    public class ScrollDisabledViewpager extends ViewPager {
        private boolean isPagingEnabled = false;

        public ScrollDisabledViewpager(Context context) {
            super(context);
        }

        public ScrollDisabledViewpager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return this.isPagingEnabled && super.onTouchEvent(event);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent event) {
            return this.isPagingEnabled && super.onInterceptTouchEvent(event);
        }

        public void setPagingEnabled(boolean b) {
            this.isPagingEnabled = b;
        }
    }

}
