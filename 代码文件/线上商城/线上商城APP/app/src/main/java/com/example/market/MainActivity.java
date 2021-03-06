package com.example.market;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.api.Customer;
import com.example.api.Good;
import com.example.api.Inventory;
import com.example.api.Invoice;
import com.example.api.InvoiceLine;
import com.example.api.NetCustomer;
import com.example.api.Payment;
import com.example.api.ShoppingCart;
import com.example.api.ShoppingCartLine;
import com.example.api.User;
import com.example.api.Warehouse;
import com.example.market.databinding.ActivityMainBinding;
import com.example.market.databinding.FragmentGoodBinding;
import com.example.market.databinding.FragmentMyBinding;
import com.example.proxy.CustomerProxy;
import com.example.proxy.GoodProxy;
import com.example.proxy.InventoryProxy;
import com.example.proxy.InvoiceLineProxy;
import com.example.proxy.InvoiceProxy;
import com.example.proxy.NetCustomerProxy;
import com.example.proxy.PaymentProxy;
import com.example.proxy.ShoppingCartLineProxy;
import com.example.proxy.ShoppingCartProxy;
import com.example.proxy.UserProxy;
import com.example.proxy.WarehouseProxy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding binding;

    public static FragmentMyBinding myBinding;

    public static FragmentGoodBinding goodBinding;

    public static cart cart;

    public static Activity activity;

    public static int ID;       //NetCustomerID

    public static int goodID;   //GoodID

    public static boolean myEditable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = this;

        Intent intent = getIntent();
        ID = intent.getIntExtra("ID",-1);   //????????????ID

        Toast.makeText(activity, "???????????????", Toast.LENGTH_SHORT).show();

        //????????????
        BottomNavigationView bottomNavigationView = binding.naviView;
        //??????????????????????????????
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                bottomNavigationView.getMenu())
                .build();

        //??????????????????
        //NavController navController = Navigation.findNavController(this, binding.naviWindow.getId());

        NavController navController;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.naviWindow.getId());
        navController = navHostFragment.getNavController();

        // ????????????????????????????????????
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp(){

        NavController navController;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.naviWindow.getId());
        navController = navHostFragment.getNavController();
        return navController.navigateUp();
    }

    //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

    public void butGoodAdd(View view){     //??????????????????
        if(goodBinding.goodNum.getText()!= null && !goodBinding.goodNum.getText().equals("")){
            goodBinding.goodNum.setText(String.valueOf(Integer.valueOf(goodBinding.goodNum.getText().toString()) + 1));
        }
        else{
            goodBinding.goodNum.setText(String.valueOf(1));
        }
    }

    public void butGoodDelete(View view){     //??????????????????
        if(goodBinding.goodNum.getText()!= null && !goodBinding.goodNum.getText().equals("")){
            if(Integer.valueOf(goodBinding.goodNum.getText().toString()) >= 1){
                goodBinding.goodNum.setText(String.valueOf(Integer.valueOf(goodBinding.goodNum.getText().toString()) - 1));
            }
            else{
                goodBinding.goodNum.setText("0");
            }
        }
        else{
            goodBinding.goodNum.setText(0);
        }
    }

    public void butGoodToCart(View view) {     //??????????????????????????????

        if(goodBinding.goodNum.getText() == null || goodBinding.goodNum.getText().toString().equals("")){
            Toast.makeText(activity, "??????????????????", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Integer.valueOf(goodBinding.goodNum.getText().toString()) == 0){
            Toast.makeText(activity, "???????????????0???", Toast.LENGTH_SHORT).show();
            return;
        }

        ShoppingCart cart = ShoppingCartProxy.findByNetCustomerId(ID).get(0);  //??????????????????
        ShoppingCartLine shoppingCartLine = new ShoppingCartLine(
                -1,
                cart.getId(),
                goodID,
                GoodProxy.findById(goodID).getPriceA(),
                Integer.valueOf(goodBinding.goodNum.getText().toString()));

        ShoppingCartLineProxy.insert(shoppingCartLine);

        Toast.makeText(activity, "???????????????", Toast.LENGTH_SHORT).show();

        goodBinding.goodNum.setText("0");
    }

    public void butMakeOrder(View view){    //??????????????????

        if(cart.pick.size() == 0){
            Toast.makeText(activity, "??????????????????", Toast.LENGTH_SHORT).show();
            return;
        }

        for(int i = 0; i < cart.pick.size(); i++){
            ShoppingCartLine line = cart.list.get(cart.pick.get(i));
            Good good = GoodProxy.findById(line.getGoodId());
            Warehouse warehouse = WarehouseProxy.findById(good.getDefaultWarehouseId());
            List<Inventory> inventoryList = InventoryProxy.findByWarehouseId(warehouse.getId());

            if(inventoryList != null && inventoryList.size() > 0){  //??????????????????
                for(int j = 0; j < inventoryList.size(); j++){
                    Inventory inventory = inventoryList.get(j);
                    if(inventory.getGoodId() == good.getId() && inventory.getAmount() >= line.getAmount()){
                        break;
                    }
                    if(j == inventoryList.size() - 1){
                        Toast.makeText(activity, "??????:" + good.getName() + " ???????????????", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            else {
                Toast.makeText(activity, "??????:" + good.getName() + " ???????????????", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        double price = 0;
        double profit = 0;
        for(int i = 0; i < cart.pick.size(); i++){  //?????????????????????
            ShoppingCartLine line = cart.list.get(cart.pick.get(i));
            Good good = GoodProxy.findById(line.getGoodId());
            price += line.getAmount() * good.getPriceA().doubleValue();
            profit += line.getAmount() * (good.getPriceA().doubleValue() - good.getPurchasePrice().doubleValue());
        }

        List<User> userList = UserProxy.getAll();   //?????????????????????
        User defaultUser = null;
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getName().equals("Default")){
                defaultUser = userList.get(i);
                break;
            }
        }

        Invoice invoice = new Invoice(  //????????????
                -1,
                NetCustomerProxy.findById(ID).getCustomerId(),
                new Timestamp(System.currentTimeMillis()),
                new BigDecimal(price),
                "?????????",
                new BigDecimal(profit),
                defaultUser.getId());
        int id = InvoiceProxy.insert(invoice);

        for(int i = 0; i < cart.pick.size(); i++){
            ShoppingCartLine line = cart.list.get(cart.pick.get(i));
            Good good = GoodProxy.findById(line.getGoodId());
            Warehouse warehouse = WarehouseProxy.findById(good.getDefaultWarehouseId());
            List<Inventory> inventoryList = InventoryProxy.findByWarehouseId(warehouse.getId());

            InvoiceLine invoiceLine = new InvoiceLine(  //???????????????
                    -1,
                    id,
                    good.getId(),
                    warehouse.getId(),
                    line.getUnitPrice(),
                    line.getAmount());
            InvoiceLineProxy.insert(invoiceLine);

            if(inventoryList != null && inventoryList.size() > 0){  //????????????
                for(int j = 0; j < inventoryList.size(); j++){
                    Inventory inventory = inventoryList.get(j);
                    if(inventory.getGoodId() == good.getId()){
                        if(inventory.getAmount() > line.getAmount()){
                            inventory.setAmount(inventory.getAmount() - line.getAmount());
                            InventoryProxy.update(inventory);
                        }
                        else{
                            InventoryProxy.delete(inventory.getId());
                        }
                        break;
                    }
                }
            }
        }

        Payment payment = new Payment(      //???????????????
                -1,
                id,
                new BigDecimal(price),
                new Timestamp(new java.util.Date().getTime()));

        PaymentProxy.insert(payment);

        List<ShoppingCartLine> delete = new ArrayList<>();      //???????????????
        for(int i = 0; i < cart.pick.size(); i++){
            delete.add(cart.list.get(cart.pick.get(i)));
        }
        while(delete.size() > 0){
            ShoppingCartLine line = delete.get(0);
            ShoppingCartLineProxy.delete(line.getId());
            delete.remove(0);
        }

        cart.initCart();    //????????????????????????

        Toast.makeText(activity, "???????????????", Toast.LENGTH_SHORT).show();
    }

    public void butLogout(View view){   //????????????

        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);

        MainActivity.this.finish();
    }

    public void butEditMy(View view){   //??????/????????????????????????
        if(myEditable == false){
            setEditable(myBinding, true);
            myBinding.myEdit.setText("??????");
        }
        else{

            if(TextUtils.isEmpty(myBinding.myName.getText())||
               TextUtils.isEmpty(myBinding.myAddr.getText()) ||
               TextUtils.isEmpty(myBinding.myPhone.getText()) ||
               TextUtils.isEmpty(myBinding.myUsername.getText()) ||
               TextUtils.isEmpty(myBinding.myPassword.getText() )){

                Toast.makeText(activity, "????????????????????????", Toast.LENGTH_SHORT).show();
                return;
            }

            NetCustomer netCustomer = NetCustomerProxy.findById(ID);
            Customer customer = CustomerProxy.findById(netCustomer.getCustomerId());

            String name = myBinding.myName.getText().toString();
            String addr = myBinding.myAddr.getText().toString();
            String phone = myBinding.myPhone.getText().toString();
            String username = myBinding.myUsername.getText().toString();
            String password = myBinding.myPassword.getText().toString();

            customer.setName(name);
            customer.setAddress(addr);
            customer.setPhone(phone);
            netCustomer.setPassword(password);
            netCustomer.setUsername(username);

            CustomerProxy.update(customer);
            NetCustomerProxy.update(netCustomer);

            Toast.makeText(activity, "???????????????????????????", Toast.LENGTH_SHORT).show();
            setEditable(myBinding, false);
            myBinding.myEdit.setText("??????");
        }
    }

    //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????

    public void setEditable(FragmentMyBinding binding, Boolean editable){     //?????????????????????????????????

        binding.myName.setFocusable(editable);
        binding.myName.setFocusableInTouchMode(editable);
        binding.myPhone.setFocusable(editable);
        binding.myPhone.setFocusableInTouchMode(editable);
        binding.myAddr.setFocusable(editable);
        binding.myAddr.setFocusableInTouchMode(editable);
        binding.myPassword.setFocusable(editable);
        binding.myPassword.setFocusableInTouchMode(editable);
        binding.myUsername.setFocusable(editable);
        binding.myUsername.setFocusableInTouchMode(editable);

        myEditable = editable;
    }
}