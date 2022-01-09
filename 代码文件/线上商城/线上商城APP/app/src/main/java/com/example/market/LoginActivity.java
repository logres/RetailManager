package com.example.market;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.example.api.NetCustomer;
import com.example.market.databinding.ActivityLoginBinding;
import com.example.proxy.NetCustomerProxy;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    public void butLogin(View view){    //登录事件

        String username = binding.loginUsername.getText().toString();
        String password = binding.loginPassword.getText().toString();
        int ID = -1;

        List<NetCustomer> list = NetCustomerProxy.findByUsername(username);
        if(list == null || list.size() == 0){
            Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
            return;
        }
        for(int i = 0; i < list.size(); i++){

            if(list.get(i).getPassword().equals(password)){
                ID = list.get(i).getId();
                break;
            }

            if(i == list.size() - 1){
                Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Intent intent = new Intent();
        intent.putExtra("ID", ID);  //传用户ID
        intent.setClass(this,MainActivity.class);
        startActivity(intent);

        LoginActivity.this.finish();
    }

    public void butRegister(View view){     //注册事件

        Intent intent = new Intent();
        intent.setClass(this,RegisterActivity.class);
        startActivity(intent);

        LoginActivity.this.finish();
    }

}