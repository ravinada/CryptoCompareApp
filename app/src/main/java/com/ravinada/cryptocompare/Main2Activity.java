package com.ravinada.cryptocompare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ravinada.cryptocompare.ui.notifications.NotificationsFragment;

public class Main2Activity extends AppCompatActivity {
//    TextView following, topVolume;
//    ProgressBar progressBar;
//    ConstraintLayout constraintlayoutsearch, constraintfollowoing;
//    View downsearchView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//        topVolume = findViewById(R.id.top_volume);
//        following = findViewById(R.id.following);
////        progressBar = findViewById(R.id.progress_bar);
//        constraintlayoutsearch = findViewById(R.id.constraintlayoutsearch);
//        constraintfollowoing = findViewById(R.id.constraintlayoutFollowing);
//        downsearchView = findViewById(R.id.downsearchview);
//
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//      //  navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction
////                .replace(R.id.insertFragmentHere, new FragmentFollowing())
////                .commit();
//
//        following.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                following.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                following.setTextColor(getResources().getColor(R.color.colorWhite));
//                topVolume.setBackgroundColor(getResources().getColor(R.color.colorWhite));
//                topVolume.setTextColor(getResources().getColor(R.color.colorBlack));
//                // progressBar.setVisibility(View.GONE);
//                constraintfollowoing.setVisibility(View.VISIBLE);
//                constraintlayoutsearch.setVisibility(View.VISIBLE);
//                downsearchView.setVisibility(View.VISIBLE);
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction
//                        .replace(R.id.insertFragmentHere, new FragmentFollowing())
//                        .commit();
//            }
//        });
//        topVolume.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                following.setBackgroundColor(getResources().getColor(R.color.colorWhite));
//                following.setTextColor(getResources().getColor(R.color.colorBlack));
//                topVolume.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                topVolume.setTextColor(getResources().getColor(R.color.colorWhite));
//            //    progressBar.setVisibility(View.VISIBLE);
//                constraintfollowoing.setVisibility(View.VISIBLE);
//                constraintlayoutsearch.setVisibility(View.VISIBLE);
//                downsearchView.setVisibility(View.VISIBLE);
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction
//                        .replace(R.id.insertFragmentHere, new FragmentTopVolume())
//                        .commit();
//            }
//        });
//    }
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.watch_list_bar: {
//                    following.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                    following.setTextColor(getResources().getColor(R.color.colorWhite));
//                    topVolume.setBackgroundColor(getResources().getColor(R.color.colorWhite));
//                    topVolume.setTextColor(getResources().getColor(R.color.colorBlack));
//                    // progressBar.setVisibility(View.GONE);
//                    constraintfollowoing.setVisibility(View.VISIBLE);
//                    constraintlayoutsearch.setVisibility(View.VISIBLE);
//                    downsearchView.setVisibility(View.VISIBLE);
//
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction
//                            .replace(R.id.insertFragmentHere, new FragmentFollowing())
//                            .commit();
//                }
//                return true;
////                case R.id.portfolio_bar:
////                    return true;
//                case R.id.newsBar: {
//                    progressBar.setVisibility(View.VISIBLE);
//                    constraintfollowoing.setVisibility(View.GONE);
//                    constraintlayoutsearch.setVisibility(View.GONE);
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction
//                            .replace(R.id.insertFragmentHere, new NotificationsFragment())
//                            .commit();
//                }
//
//                return true;
//            }
//            return false;
//        }
//    };
}
