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

import com.example.api.Invoice;
import com.example.api.NetCustomer;
import com.example.market.databinding.FragmentHistoryBinding;
import com.example.market.databinding.FragmentShopBinding;
import com.example.proxy.InvoiceProxy;
import com.example.proxy.NetCustomerProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link history#newInstance} factory method to
 * create an instance of this fragment.
 */
public class history extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public history() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment history.
     */
    // TODO: Rename and change types and number of parameters
    public static history newInstance(String param1, String param2) {
        history fragment = new history();
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
    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_shop, container, false);

        binding = FragmentHistoryBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        //添加商品列表

        ListView view = binding.historyInfoList;

        view.setDivider(null);  //设置无边框

        /*定义一个动态数组*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        MainActivity activity = (MainActivity) getActivity();
        int ID = activity.ID;
        NetCustomer netCustomer = NetCustomerProxy.findById(ID);

        List<Invoice> list = InvoiceProxy.findByCustomerId(netCustomer.getCustomerId());

        if(list != null){
            /*在数组中存放数据*/
            for(int i = 0; i < list.size(); i++)
            {
                Invoice invoice = list.get(i);
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemNum", "订单号： " + invoice.getId());
                map.put("ItemPrice", invoice.getTotalPrice().doubleValue() + " 元");
                map.put("ItemTime", Tools.formatTimestamp(invoice.getTime()));
                listItem.add(map);
            }
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this.getActivity(),listItem,//需要绑定的数据
                R.layout.fragment_order_info,//每一行的布局
                //动态数组中的数据源的键对应到定义布局的View中
                new String[] {"ItemNum","ItemPrice", "ItemTime"},
                new int[] {R.id.order_info_num,R.id.order_info_price,R.id.order_info_time});

        view.setAdapter(mSimpleAdapter);//为ListView绑定适配器


        Fragment fragment = this;
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                //Navigation.createNavigateOnClickListener(R.id.action_navi_shop_to_navi_good, null);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", list.get(arg2).getId());
                NavHostFragment.findNavController(fragment).navigate(R.id.action_navi_history_to_navi_order, bundle);
            }
        });
    }
}