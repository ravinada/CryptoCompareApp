package com.ravinada.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.ravinada.cryptocompare.databinding.ActivitySignupBinding;
import com.ravinada.cryptocompare.utils.PreferenceData;
import com.ravinada.cryptocompare.utils.Utils;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

     ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.btnSignUp.setOnClickListener(this);
        binding.llFacebook.setOnClickListener(this);
        binding.llGoogle.setOnClickListener(this);
        binding.signIn.setOnClickListener(this);

        Spannable wordtoSpan = new SpannableString("Already have account ?  SIGN IN");
        wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 22, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.signIn.setText(wordtoSpan);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUp:
                if (binding.edtEmail.getText().toString().length() == 0){
                    Utils.showToast("Please enter email address");
                } else if (binding.edtPhoneNumber.getText().toString().length() == 0){
                    Utils.showToast("Please enter phone number");
                } else if (binding.edtPassword.getText().toString().length() == 0){
                    Utils.showToast("Please enter password");
                } else if (binding.edtPassword.getText().toString().length() == 0){
                    Utils.showToast("Please enter confirm password");
                } else if (binding.edtPassword.getText().toString().equalsIgnoreCase(binding.edtConfirmPassword.getText().toString())){
                    Utils.showToast("Password and confirm password does not match!");
                }else{
                    PreferenceData.setLogin(true);
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                }
                break;
            case R.id.llFacebook:
                PreferenceData.setLogin(true);
                Intent i = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();
                break;
            case R.id.llGoogle:
                binding.llFacebook.performClick();
                break;
            case R.id.signIn:
                Intent intentLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                overridePendingTransition(0, 0);
                finish();
                break;
        }
    }
}
