package com.ravinada.cryptocompare;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ravinada.cryptocompare.databinding.ActivityDashboardBindingImpl;
import com.ravinada.cryptocompare.databinding.ActivityForgotPasswordBindingImpl;
import com.ravinada.cryptocompare.databinding.ActivityLoginBindingImpl;
import com.ravinada.cryptocompare.databinding.ActivityNewsDetailsBindingImpl;
import com.ravinada.cryptocompare.databinding.ActivitySignupBindingImpl;
import com.ravinada.cryptocompare.databinding.CoinPortfolioDialogBindingImpl;
import com.ravinada.cryptocompare.databinding.FragmentFollowingListBindingImpl;
import com.ravinada.cryptocompare.databinding.FragmentNewsListBindingImpl;
import com.ravinada.cryptocompare.databinding.FragmentPotfolioBindingImpl;
import com.ravinada.cryptocompare.databinding.FragmentTopvolumeListBindingImpl;
import com.ravinada.cryptocompare.databinding.MainListFragmentBindingImpl;
import com.ravinada.cryptocompare.databinding.PortfolioDialogBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYDASHBOARD = 1;

  private static final int LAYOUT_ACTIVITYFORGOTPASSWORD = 2;

  private static final int LAYOUT_ACTIVITYLOGIN = 3;

  private static final int LAYOUT_ACTIVITYNEWSDETAILS = 4;

  private static final int LAYOUT_ACTIVITYSIGNUP = 5;

  private static final int LAYOUT_COINPORTFOLIODIALOG = 6;

  private static final int LAYOUT_FRAGMENTFOLLOWINGLIST = 7;

  private static final int LAYOUT_FRAGMENTNEWSLIST = 8;

  private static final int LAYOUT_FRAGMENTPOTFOLIO = 9;

  private static final int LAYOUT_FRAGMENTTOPVOLUMELIST = 10;

  private static final int LAYOUT_MAINLISTFRAGMENT = 11;

  private static final int LAYOUT_PORTFOLIODIALOG = 12;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(12);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.activity_dashboard, LAYOUT_ACTIVITYDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.activity_forgot_password, LAYOUT_ACTIVITYFORGOTPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.activity_news_details, LAYOUT_ACTIVITYNEWSDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.activity_signup, LAYOUT_ACTIVITYSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.coin_portfolio_dialog, LAYOUT_COINPORTFOLIODIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.fragment_following_list, LAYOUT_FRAGMENTFOLLOWINGLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.fragment_news_list, LAYOUT_FRAGMENTNEWSLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.fragment_potfolio, LAYOUT_FRAGMENTPOTFOLIO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.fragment_topvolume_list, LAYOUT_FRAGMENTTOPVOLUMELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.main_list_fragment, LAYOUT_MAINLISTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.ravinada.cryptocompare.R.layout.portfolio_dialog, LAYOUT_PORTFOLIODIALOG);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYDASHBOARD: {
          if ("layout/activity_dashboard_0".equals(tag)) {
            return new ActivityDashboardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_dashboard is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYFORGOTPASSWORD: {
          if ("layout/activity_forgot_password_0".equals(tag)) {
            return new ActivityForgotPasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_forgot_password is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNEWSDETAILS: {
          if ("layout/activity_news_details_0".equals(tag)) {
            return new ActivityNewsDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_news_details is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSIGNUP: {
          if ("layout/activity_signup_0".equals(tag)) {
            return new ActivitySignupBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_signup is invalid. Received: " + tag);
        }
        case  LAYOUT_COINPORTFOLIODIALOG: {
          if ("layout/coin_portfolio_dialog_0".equals(tag)) {
            return new CoinPortfolioDialogBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for coin_portfolio_dialog is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFOLLOWINGLIST: {
          if ("layout/fragment_following_list_0".equals(tag)) {
            return new FragmentFollowingListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_following_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNEWSLIST: {
          if ("layout/fragment_news_list_0".equals(tag)) {
            return new FragmentNewsListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_news_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPOTFOLIO: {
          if ("layout/fragment_potfolio_0".equals(tag)) {
            return new FragmentPotfolioBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_potfolio is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTOPVOLUMELIST: {
          if ("layout/fragment_topvolume_list_0".equals(tag)) {
            return new FragmentTopvolumeListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_topvolume_list is invalid. Received: " + tag);
        }
        case  LAYOUT_MAINLISTFRAGMENT: {
          if ("layout/main_list_fragment_0".equals(tag)) {
            return new MainListFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for main_list_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_PORTFOLIODIALOG: {
          if ("layout/portfolio_dialog_0".equals(tag)) {
            return new PortfolioDialogBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for portfolio_dialog is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(5);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "currencyAdapter");
      sKeys.put(2, "CurrencyTypePurchaseAdapter");
      sKeys.put(3, "newsAdapter");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(12);

    static {
      sKeys.put("layout/activity_dashboard_0", com.ravinada.cryptocompare.R.layout.activity_dashboard);
      sKeys.put("layout/activity_forgot_password_0", com.ravinada.cryptocompare.R.layout.activity_forgot_password);
      sKeys.put("layout/activity_login_0", com.ravinada.cryptocompare.R.layout.activity_login);
      sKeys.put("layout/activity_news_details_0", com.ravinada.cryptocompare.R.layout.activity_news_details);
      sKeys.put("layout/activity_signup_0", com.ravinada.cryptocompare.R.layout.activity_signup);
      sKeys.put("layout/coin_portfolio_dialog_0", com.ravinada.cryptocompare.R.layout.coin_portfolio_dialog);
      sKeys.put("layout/fragment_following_list_0", com.ravinada.cryptocompare.R.layout.fragment_following_list);
      sKeys.put("layout/fragment_news_list_0", com.ravinada.cryptocompare.R.layout.fragment_news_list);
      sKeys.put("layout/fragment_potfolio_0", com.ravinada.cryptocompare.R.layout.fragment_potfolio);
      sKeys.put("layout/fragment_topvolume_list_0", com.ravinada.cryptocompare.R.layout.fragment_topvolume_list);
      sKeys.put("layout/main_list_fragment_0", com.ravinada.cryptocompare.R.layout.main_list_fragment);
      sKeys.put("layout/portfolio_dialog_0", com.ravinada.cryptocompare.R.layout.portfolio_dialog);
    }
  }
}
