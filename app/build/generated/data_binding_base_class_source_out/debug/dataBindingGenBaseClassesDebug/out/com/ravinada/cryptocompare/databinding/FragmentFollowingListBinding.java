// Generated by data binding compiler. Do not edit!
package com.ravinada.cryptocompare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.adapters.FavouriteCurrencyAdapter;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentFollowingListBinding extends ViewDataBinding {
  @NonNull
  public final SwipeRefreshLayout followingSwipeRefresh;

  @NonNull
  public final RecyclerView recyclerView;

  @Bindable
  protected FavouriteCurrencyAdapter mFavouriteCurrencyAdapter;

  protected FragmentFollowingListBinding(Object _bindingComponent, View _root, int _localFieldCount,
      SwipeRefreshLayout followingSwipeRefresh, RecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.followingSwipeRefresh = followingSwipeRefresh;
    this.recyclerView = recyclerView;
  }

  public abstract void setFavouriteCurrencyAdapter(@Nullable FavouriteCurrencyAdapter FavouriteCurrencyAdapter);

  @Nullable
  public FavouriteCurrencyAdapter getFavouriteCurrencyAdapter() {
    return mFavouriteCurrencyAdapter;
  }

  @NonNull
  public static FragmentFollowingListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_following_list, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentFollowingListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentFollowingListBinding>inflateInternal(inflater, R.layout.fragment_following_list, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentFollowingListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_following_list, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentFollowingListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentFollowingListBinding>inflateInternal(inflater, R.layout.fragment_following_list, null, false, component);
  }

  public static FragmentFollowingListBinding bind(@NonNull View view) {
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
  public static FragmentFollowingListBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentFollowingListBinding)bind(component, view, R.layout.fragment_following_list);
  }
}
