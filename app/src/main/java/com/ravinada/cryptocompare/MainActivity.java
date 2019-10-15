package com.ravinada.cryptocompare;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import com.ravinada.cryptocompare.utils.AppConstant;
import com.ravinada.cryptocompare.utils.PreferenceData;
import com.ravinada.cryptocompare.utils.Utils;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;



import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity  {

//    private static final String TAG = MainActivity.class.getSimpleName();
//    ActivityMainBinding binding;
//    private MainListFragment mainListFragment;
//    private LikeListFragment likeListFragment;
//    private BookmarkListFragment bookmarkListFragment;
//    private GalleryFragment galleryFragment;
//    private SearchManager searchManager;
//    private int selectedBottomMenu = 1;
//    private boolean isBack = false;
//    private boolean isTopSearchHide = false;
//    private boolean isTopFilterHide = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//
//        setSupportActionBar(binding.viewMain.toolbar);
//        // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.viewMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        // binding.drawerLayout.addDrawerListener(toggle);
//        // toggle.syncState();
//        // binding.navView.setNavigationItemSelectedListener(this);
//
//        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//
//        mainListFragment = MainListFragment.newInstance();
//        likeListFragment = LikeListFragment.newInstance();
//        bookmarkListFragment = BookmarkListFragment.newInstance();
//        galleryFragment = GalleryFragment.newInstance();
//
//        binding.viewMain.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // back button pressed
//                if (isBack) {
//                    selectedBottomMenu = 1;
//                    isBack = false;
//                    setTopBar(isBack, null);
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.main_frame_layout, mainListFragment, MainListFragment.class.getSimpleName())
//                            .commit();
//                } else {
//                    // binding.drawerLayout.openDrawer(GravityCompat.START);
//                }
//            }
//        });
//
//        binding.tabs.setupWithViewPager(binding.viewpager,true);
//        com.statusforest.adapters.ViewPagerAdapter viewPagerAdapter = new com.statusforest.adapters.ViewPagerAdapter(this.getSupportFragmentManager());
//        viewPagerAdapter.addFragment(MainListFragment.newInstance(), "Photo");
//        viewPagerAdapter.addFragment(MainListFragment.newInstance(), "Video");
//        binding.viewpager.setAdapter(viewPagerAdapter);
//        binding.viewpager.setCurrentItem(0);
//
//        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        binding.mainFrameLayout.setVisibility(View.GONE);
//
//        /*getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_frame_layout, mainListFragment, MainListFragment.class.getSimpleName())
//                .commit();*/
//
//    }
//
//    boolean doubleBackToExitPressedOnce = false;
//
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Utils.showToast("Press again to exit!");
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        if (isTopSearchHide) {
//            for (int i = 0; i < menu.size(); i++)
//                menu.getItem(i).setVisible(false);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_search) {
//            SearchView searchView = (SearchView) item.getActionView();
//            searchView.setQueryHint("Search text");
//            searchView.setOnQueryTextListener(this);
//            return true;
//        }else if (id == R.id.action_more){
//
//            searchManager.stopSearch();
//            PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.action_search));
//            //Inflating the Popup using xml file
//            popup.getMenuInflater().inflate(R.menu.bottom_nav_menu, popup.getMenu());
//
//            //registering popup with OnMenuItemClickListener
//            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                public boolean onMenuItemClick(MenuItem item) {
//
//                    switch (item.getItemId()){
//                        case R.id.action_privacy_policy:
//                            Intent intent = new Intent(MainActivity.this,TermsAndPrivacyPolicyActivity.class);
//                            intent.putExtra(AppConstant.EXTRA_IS_FROM,1);
//                            startActivity(intent);
//                            overridePendingTransition(0,0);
//                            break;
//                        case R.id.action_contact_us:
//                            Intent intent1 = new Intent(MainActivity.this,ContactUsActivity.class);
//                            startActivity(intent1);
//                            overridePendingTransition(0,0);
//                            break;
//                        case R.id.action_about_us:
//                            Intent intent2 = new Intent(MainActivity.this,TermsAndPrivacyPolicyActivity.class);
//                            intent2.putExtra(AppConstant.EXTRA_IS_FROM,2);
//                            startActivity(intent2);
//                            overridePendingTransition(0,0);
//                            break;
//                        case R.id.action_sign_out:
//                            if (Utils.isNetworkAvailable(MainActivity.this)) {
//                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        switch (which) {
//                                            case DialogInterface.BUTTON_POSITIVE: //Yes button clicked
//                                                dialog.dismiss();
//                                                PreferenceData.clear();
//                                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                                startActivity(intent);
//                                                overridePendingTransition(0,0);
//                                                finish();
//                                                //signOut();
//                                                break;
//                                            case DialogInterface.BUTTON_NEGATIVE: //No button clicked
//                                                dialog.dismiss();
//                                                break;
//                                        }
//                                    }
//                                };
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                                builder.setMessage("Are you sure you want to logout?").setPositiveButton("Logout", dialogClickListener)
//                                        .setNegativeButton("Cancel", dialogClickListener).show();
//                            } else {
//                                Utils.showToast(getString(R.string.no_internet_connection));
//                            }
//                            break;
//                    }
//
//                    return true;
//                }
//            });
//
//            popup.show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setTopBar(boolean isBack, String title) {
//        if (isBack) {
//            isBack = false;
//            // add back arrow to toolbar
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                getSupportActionBar().setDisplayShowHomeEnabled(true);
//                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right);
//                if (title != null && title.length() > 0) {
//                    getSupportActionBar().setTitle(getString(R.string.title_home));
//                } else {
//                    getSupportActionBar().setTitle(title);
//                }
//            }
//        } else {
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
//                getSupportActionBar().setDisplayShowHomeEnabled(true);
//            }
//        }
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        Log.i(TAG, "onQueryTextSubmit: " + query);
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        Log.i(TAG, "onQueryTextChange: " + newText);
//        return false;
//    }
}
