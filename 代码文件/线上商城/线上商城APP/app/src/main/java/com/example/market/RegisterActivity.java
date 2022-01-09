package com.example.market;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.api.Customer;
import com.example.api.NetCustomer;
import com.example.api.ShoppingCart;

import com.example.market.databinding.ActivityRegisterBinding;
import com.example.proxy.CustomerProxy;
import com.example.proxy.NetCustomerProxy;
import com.example.proxy.ShoppingCartProxy;

public class RegisterActivity extends AppCompatActivity {

    public static ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void butRegister(View view){     //注册事件

        if(TextUtils.isEmpty(binding.registerUsername.getText())||
           TextUtils.isEmpty(binding.registerPassword.getText())){
            Toast.makeText(RegisterActivity.this, "请输入全部用户信息！", Toast.LENGTH_SHORT).show();
            return;
        }

        Customer customer;
        int ID = -1;
        if(!TextUtils.isEmpty(binding.registerID.getText().toString())){       //有关联ID
            ID = Integer.valueOf(binding.registerID.getText().toString());

            customer = CustomerProxy.findById(ID);
            if(customer == null){
                Toast.makeText(RegisterActivity.this, "未找到关联用户！", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else{                                                                                       //无关联ID
            if(TextUtils.isEmpty(binding.registerPhone.getText()) ||
               TextUtils.isEmpty(binding.registerName.getText()) ||
               TextUtils.isEmpty(binding.registerAddr.getText())){
                Toast.makeText(RegisterActivity.this, "请输入全部用户信息！", Toast.LENGTH_SHORT).show();
                return;
            }

            customer = new Customer(
                    -1,
                    binding.registerName.getText().toString(),
                    binding.registerPhone.getText().toString(),
                    binding.registerAddr.getText().toString(),
                    "零售",
                    "");

            int id = CustomerProxy.insert(customer);
            customer = CustomerProxy.findById(id);
        }

        NetCustomer netCustomer = new NetCustomer(  //生成网络用户
                -1,
                customer.getId(),
                binding.registerUsername.getText().toString(),
                binding.registerPassword.getText().toString());
        int id = NetCustomerProxy.insert(netCustomer);

        ShoppingCart cart = new ShoppingCart(
                -1,
                id);

        ShoppingCartProxy.insert(cart);


        Intent intent = new Intent();   //切换界面
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);

        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();

        RegisterActivity.this.finish();
    }

    public void butReturn(View view){     //返回事件

        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);

        RegisterActivity.this.finish();
    }

}