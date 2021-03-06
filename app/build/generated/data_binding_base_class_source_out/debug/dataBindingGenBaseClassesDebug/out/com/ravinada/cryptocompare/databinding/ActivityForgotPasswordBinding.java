// Generated by data binding compiler. Do not edit!
package com.ravinada.cryptocompare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ravinada.cryptocompare.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityForgotPasswordBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout back;

  @NonNull
  public final Button btnFPass;

  @NonNull
  public final EditText edtEmail;

  @NonNull
  public final ImageView llll;

  protected ActivityForgotPasswordBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayout back, Button btnFPass, EditText edtEmail, ImageView llll) {
    super(_bindingComponent, _root, _localFieldCount);
    this.back = back;
    this.btnFPass = btnFPass;
    this.edtEmail = edtEmail;
    this.llll = llll;
  }

  @NonNull
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_forgot_password, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityForgotPasswordBinding>inflateInternal(inflater, R.layout.activity_forgot_password, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_forgot_password, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityForgotPasswordBinding>inflateInternal(inflater, R.layout.activity_forgot_password, null, false, component);
  }

  public static ActivityForgotPasswordBinding bind(@NonNull View view) {
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
  public static ActivityForgotPasswordBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityForgotPasswordBinding)bind(component, view, R.layout.activity_forgot_password);
  }
}
