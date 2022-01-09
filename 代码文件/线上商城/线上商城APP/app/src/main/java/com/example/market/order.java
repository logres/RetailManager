package com.example.market;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.api.Customer;
import com.example.api.Good;
import com.example.api.Invoice;
import com.example.api.InvoiceLine;
import com.example.api.NetCustomer;
import com.example.api.User;
import com.example.market.databinding.FragmentHistoryBinding;
import com.example.market.databinding.FragmentOrderBinding;
import com.example.proxy.CustomerProxy;
import com.example.proxy.GoodProxy;
import com.example.proxy.InvoiceLineProxy;
import com.example.proxy.InvoiceProxy;
import com.example.proxy.NetCustomerProxy;
import com.example.proxy.UserProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link order#newInstance} factory method to
 * create an instance of this fragment.
 */
public class order extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public order() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment order.
     */
    // TODO: Rename and change types and number of parameters
    public static order newInstance(String param1, String param2) {
        order fragment = new order();
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
    private FragmentOrderBinding binding;
    public int ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_shop, container, false);

        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        ID = getArguments().getInt("ID");

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        //添加商品列表

        ListView view = binding.orderInfoList;

        view.setDivider(null);  //设置无边框

        /*定义一个动态数组*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        MainActivity activity = (MainActivity) getActivity();
        int userID = activity.ID;
        NetCustomer netCustomer = NetCustomerProxy.findById(userID);

        List<InvoiceLine> list = InvoiceLineProxy.findByInvoiceId(ID);

        if(list != null){
            /*在数组中存放数据*/
            for(int i = 0; i < list.size(); i++)
            {
                Invoice invoice = InvoiceProxy.findById(ID);
                InvoiceLine invoiceLine = list.get(i);
                Good good = GoodProxy.findById(invoiceLine.getGoodId());

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemNum", invoiceLine.getAmount() + " 件");
                map.put("ItemPrice", invoiceLine.getUnitPrice().doubleValue() * invoiceLine.getAmount() + " 元");
                map.put("ItemName", good.getName());
                listItem.add(map);
            }
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this.getActivity(),listItem,//需要绑定的数据
                R.layout.fragment_order_item_info,//每一行的布局
                //动态数组中的数据源的键对应到定义布局的View中
                new String[] {"ItemNum","ItemPrice", "ItemName"},
                new int[] {R.id.order_item_info_num,R.id.order_item_info_price,R.id.order_item_info_name});

        view.setAdapter(mSimpleAdapter);//为ListView绑定适配器

        Invoice invoice = InvoiceProxy.findById(ID);
        Customer customer = CustomerProxy.findById(netCustomer.getCustomerId());

        binding.orderTime.setText(Tools.formatTimestamp(invoice.getTime()));
        binding.orderAddr.setText(customer.getAddress());
        binding.orderPrice.setText(invoice.getTotalPrice().doubleValue() + " 元");

    }
}