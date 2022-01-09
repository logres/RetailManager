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
import android.widget.Toast;

import com.example.api.Good;
import com.example.market.databinding.FragmentShopBinding;
import com.example.proxy.GoodProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link shop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class shop extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public shop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment shop.
     */
    // TODO: Rename and change types and number of parameters
    public static shop newInstance(String param1, String param2) {
        shop fragment = new shop();
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
    private FragmentShopBinding binding;
    private List<Good> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(getLayoutInflater());

        binding.shopBut1.setOnClickListener(new View.OnClickListener() {        //在售事件
            @Override
            public void onClick(View view) {
                List<Good> allList = GoodProxy.getAll();
                List<Good> newList = new ArrayList<>();
                if(allList != null){
                    for(int i = 0; i < allList.size(); i++){
                        Good good = allList.get(i);
                        if(good.getState().equals("在售")){
                            newList.add(good);
                        }
                    }
                }
                list = newList;
                loadList();
            }
        });

        binding.shopBut2.setOnClickListener(new View.OnClickListener() {        //热销事件
            @Override
            public void onClick(View view) {
                List<Good> allList = GoodProxy.getAll();
                List<Good> newList = new ArrayList<>();
                if(allList != null){
                    for(int i = 0; i < allList.size(); i++){
                        Good good = allList.get(i);
                        if(good.getState().equals("热销")){
                            newList.add(good);
                        }
                    }
                }
                list = newList;
                loadList();

                Toast.makeText(getActivity(), "共找到： " + newList.size() + " 条商品！", Toast.LENGTH_SHORT).show();
            }
        });

        binding.shopBut3.setOnClickListener(new View.OnClickListener() {        //其他事件
            @Override
            public void onClick(View view) {
                List<Good> allList = GoodProxy.getAll();
                List<Good> newList = new ArrayList<>();
                if(allList != null){
                    for(int i = 0; i < allList.size(); i++){
                        Good good = allList.get(i);
                        if(!good.getState().equals("热销") && !good.getState().equals("在售")){
                            newList.add(good);
                        }
                    }
                }
                list = newList;
                loadList();

                Toast.makeText(getActivity(), "共找到： " + newList.size() + " 条商品！", Toast.LENGTH_SHORT).show();
            }
        });

        binding.shopSearch.setOnClickListener(new View.OnClickListener() {        //搜索事件
            @Override
            public void onClick(View view) {
                List<Good> allList = list;
                List<Good> newList = new ArrayList<>();
                String search = binding.shopSearchInfo.getText().toString();

                if(allList != null){
                    for(int i = 0; i < allList.size(); i++){
                        Good good = allList.get(i);
                        if(good.getName().contains(search)){
                            newList.add(good);
                        }
                    }
                }
                list = newList;
                loadList();

                Toast.makeText(getActivity(), "共找到： " + newList.size() + " 条商品！", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity)getActivity();

        list = GoodProxy.getAll();   //全部商品信息
        loadList();
    }

    private void loadList(){  //将list数据导入界面

        ListView view = binding.goodInfoList;

        view.setDivider(null);  //设置无边框

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        if(list != null){
            for(int i = 0; i < list.size();i++)
            {
                Good good = list.get(i);

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemImage", R.drawable.image_1);//加入图片
                map.put("ItemInfo", good.getInfo());
                map.put("ItemName", good.getName());
                map.put("ItemPrice", good.getPriceA().doubleValue() + " 元");
                listItem.add(map);
            }
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this.getActivity(),listItem,//需要绑定的数据
                R.layout.fragment_good_info,//每一行的布局
                //动态数组中的数据源的键对应到定义布局的View中
                new String[] {"ItemImage","ItemInfo", "ItemName", "ItemPrice"},
                new int[] {R.id.good_info_image,R.id.good_info_info,R.id.good_info_name, R.id.good_info_price});

        view.setAdapter(mSimpleAdapter);//为ListView绑定适配器

        Fragment fragment = this;
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Navigation.createNavigateOnClickListener(R.id.action_navi_shop_to_navi_good, null);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", list.get(position).getId());
                NavHostFragment.findNavController(fragment).navigate(R.id.action_navi_shop_to_navi_good, bundle);
            }
        });
    }
}