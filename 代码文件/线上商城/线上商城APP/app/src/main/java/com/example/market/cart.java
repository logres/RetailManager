package com.example.market;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.api.Customer;
import com.example.api.Good;
import com.example.api.ShoppingCart;
import com.example.api.ShoppingCartLine;
import com.example.market.databinding.FragmentCartBinding;
import com.example.proxy.CustomerProxy;
import com.example.proxy.GoodProxy;
import com.example.proxy.NetCustomerProxy;
import com.example.proxy.ShoppingCartLineProxy;
import com.example.proxy.ShoppingCartProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cart.
     */
    // TODO: Rename and change types and number of parameters
    public static cart newInstance(String param1, String param2) {
        cart fragment = new cart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //
    public FragmentCartBinding binding;
    public MainActivity activity;
    public List<ShoppingCartLine> list;

    public List<Integer> pick = new ArrayList<>();      //????????????????????????

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initCart();
    }

    public void calculatePrice(){   //??????????????????
        double price = 0;
        for(int i = 0; i < pick.size(); i++){
            ShoppingCartLine line = list.get(pick.get(i));
            price += line.getAmount() * line.getUnitPrice().doubleValue();
        }
        binding.cartPrice.setText(String.valueOf(price) + " ???");
    }

    public void initCart(){  //????????????????????????

        ListView view = binding.cartInfoList;

        MainActivity activity = (MainActivity)getActivity();    //????????????
        Customer customer = CustomerProxy.findById(NetCustomerProxy.findById(activity.ID).getCustomerId());
        binding.cartAddress.setText(customer.getAddress());
        binding.cartAddress.setFocusable(false);
        binding.cartAddress.setFocusableInTouchMode(false);

        view.setDivider(null);  //???????????????

        /*????????????????????????*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        pick.clear();

        activity = (MainActivity)getActivity();
        activity.cart = this;
        ShoppingCart cart = ShoppingCartProxy.findByNetCustomerId(activity.ID).get(0);
        list = ShoppingCartLineProxy.findByShoppingCartId(cart.getId());

        if(list != null){
            /*????????????????????????*/
            for(int i = 0; i < list.size(); i++)
            {
                ShoppingCartLine line = list.get(i);
                Good good = GoodProxy.findById(line.getGoodId());

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemImage", R.drawable.image_1);//????????????
                map.put("ItemNum", line.getAmount() + " ???");
                map.put("ItemName", good.getName());
                map.put("ItemPrice", String.valueOf(line.getAmount() * line.getUnitPrice().doubleValue()) + " ???");
                listItem.add(map);
            }
        }

        cartInfoAdapter mSimpleAdapter = new cartInfoAdapter(this.getActivity(),listItem,//?????????????????????,???????????????
                R.layout.fragment_chart_info,//??????????????????
                //?????????????????????????????????????????????????????????View???
                new String[] {"ItemImage","ItemNum", "ItemName", "ItemPrice"},
                new int[] {R.id.cart_info_image,R.id.cart_info_num,R.id.cart_info_name, R.id.cart_info_price},
                pick,
                this);

        view.setAdapter(mSimpleAdapter);//???ListView???????????????

        calculatePrice();
    }


}