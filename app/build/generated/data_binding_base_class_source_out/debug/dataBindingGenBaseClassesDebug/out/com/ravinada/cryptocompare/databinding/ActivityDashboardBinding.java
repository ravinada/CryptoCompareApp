// Generated by data binding compiler. Do not edit!
package com.ravinada.cryptocompare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.ravinada.cryptocompare.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityDashboardBinding extends ViewDataBinding {
  @NonNull
  public final CoordinatorLayout activityMain;

  @NonNull
  public final RelativeLayout container;

  @NonNull
  public final TextView currencyTag;

  @NonNull
  public final TextView portfolioEdit;

  @NonNull
  public final SearchView searchBar;

  @NonNull
  public final TabItem tabMenuNews;

  @NonNull
  public final TabItem tabMenuPortfolio;

  @NonNull
  public final TabItem tabMenuWatchList;

  @NonNull
  public final TabLayout tabsBottom;

  @NonNull
  public final RelativeLayout toolbar;

  @NonNull
  public final TextView txtPortfolioName;

  @NonNull
  public final ImageButton userMenu;

  @NonNull
  public final ViewPager viewPager;

  protected ActivityDashboardBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CoordinatorLayout activityMain, RelativeLayout container, TextView currencyTag,
      TextView portfolioEdit, SearchView searchBar, TabItem tabMenuNews, TabItem tabMenuPortfolio,
      TabItem tabMenuWatchList, TabLayout tabsBottom, RelativeLayout toolbar,
      TextView txtPortfolioName, ImageButton userMenu, ViewPager viewPager) {
    super(_bindingComponent, _root, _localFieldCount);
    this.activityMain = activityMain;
    this.container = container;
    this.currencyTag = currencyTag;
    this.portfolioEdit = portfolioEdit;
    this.searchBar = searchBar;
    this.tabMenuNews = tabMenuNews;
    this.tabMenuPortfolio = tabMenuPortfolio;
    this.tabMenuWatchList = tabMenuWatchList;
    this.tabsBottom = tabsBottom;
    this.toolbar = toolbar;
    this.txtPortfolioName = txtPortfolioName;
    this.userMenu = userMenu;
    this.viewPager = viewPager;
  }

  @NonNull
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_dashboard, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityDashboardBinding>inflateInternal(inflater, R.layout.activity_dashboard, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_dashboard, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityDashboardBinding>inflateInternal(inflater, R.layout.activity_dashboard, null, false, component);
  }

  public static ActivityDashboardBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityDashboardBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityDashboardBinding)bind(component, view, R.layout.activity_dashboard);
  }
}
