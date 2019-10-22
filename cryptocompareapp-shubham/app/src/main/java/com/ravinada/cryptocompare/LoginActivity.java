package com.ravinada.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ravinada.cryptocompare.databinding.ActivityLoginBinding;
import com.ravinada.cryptocompare.utils.PreferenceData;
import com.ravinada.cryptocompare.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;
    private boolean showP = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btnLogin.setOnClickListener(this);
        binding.llFacebook.setOnClickListener(this);
        binding.llGoogle.setOnClickListener(this);
        binding.signUp.setOnClickListener(this);
        binding.forgotPassword.setOnClickListener(this);
        binding.switchPass.setOnClickListener(this);

        Spannable wordtoSpan = new SpannableString("Don't have an account ?  SIGN UP");
        wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 22, 32, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.signUp.setText(wordtoSpan);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                if (binding.edtEmail.getText().toString().length() == 0){
                    Utils.showToast("Please enter email address");
                }else if (binding.edtPassword.getText().toString().length() == 0){
                    Utils.showToast("Please enter password");
                }else{
                    PreferenceData.setLogin(true);
                    Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                }
                break;
            case R.id.llFacebook:
                PreferenceData.setLogin(true);
                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();
                break;
            case R.id.llGoogle:
                binding.llFacebook.performClick();
                break;
            case R.id.signUp:
                Intent intentSignUp = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intentSignUp);
                overridePendingTransition(0, 0);
                finish();
                break;
            case R.id.switchPass:
                showP = !showP;
                showPass(showP);
                break;
            case R.id.forgotPassword:
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }

    void showPass(boolean flag) {
        try {
            if (flag) {
                binding.switchPass.setImageResource(R.drawable.ic_enhanced_encryption_black_24dp);
                binding.edtPassword.setTransformationMethod(null);
            } else {
                binding.switchPass.setImageResource(R.drawable.ic_no_encryption_black_24dp);
                binding.edtPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
            binding.edtPassword.setSelection(binding.edtPassword.getText().length());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
