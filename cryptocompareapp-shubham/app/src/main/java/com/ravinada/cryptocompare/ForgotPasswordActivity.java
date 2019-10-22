package com.ravinada.cryptocompare;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.ravinada.cryptocompare.R;
import com.ravinada.cryptocompare.databinding.ActivityForgotPasswordBinding;
import com.ravinada.cryptocompare.utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(ForgotPasswordActivity.this, R.layout.activity_forgot_password);
        binding.btnFPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.getText().toString().trim()).matches()) {
                    Utils.showToast("Invalid email address!");
                    return;
                }

                binding.btnFPass.setClickable(false);
                Map<String, Object> map = new HashMap<>();
                map.put("email", binding.edtEmail.getText().toString().trim());
                finish();
               // forgotPassword(map);
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
            }
        });
    }

    /*void forgotPassword(Map<String, Object> map) {
        Utils.showDialog(this);
        connectPost(this,  null, forgotPass(map), new ApiConnection.ConnectListener() {
            @Override
            public void onResponseSuccess(String response, Headers headers, int StatusCode) {
                Utils.hideDialog();
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onResponseFailure(ResponseBody responseData, Headers headers, int StatusCode) {
                binding.btnFPass.setClickable(true);
                Utils.hideDialog();
                //Toast.makeText(getApplicationContext(), responseData.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Headers headers) {
                Utils.hideDialog();
                binding.btnFPass.setClickable(true);        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onConnectionFailure() {
                Utils.hideDialog();
                binding.btnFPass.setClickable(true);   Toast.makeText(getApplicationContext(), "CC Failure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onException(Headers headers, int StatusCode) {
                Utils.hideDialog();
                binding.btnFPass.setClickable(true);     Toast.makeText(getApplicationContext(), "EE Failure" + StatusCode, Toast.LENGTH_LONG).show();
            }
        });

    }*/
}
