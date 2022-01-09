package com.example.market;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.api.Good;
import com.example.market.databinding.FragmentGoodBinding;
import com.example.proxy.GoodProxy;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link good#newInstance} factory method to
 * create an instance of this fragment.
 */
public class good extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public good() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment good.
     */
    // TODO: Rename and change types and number of parameters
    public static good newInstance(String param1, String param2) {
        good fragment = new good();
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
    public FragmentGoodBinding binding;
    public int ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentGoodBinding.inflate(getLayoutInflater());

        ID = getArguments().getInt("ID");
        binding.goodNum.setText(String.valueOf(ID));

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity)getActivity();
        activity.goodBinding = binding;
        activity.goodID = ID;

        Good good = GoodProxy.findById(ID);

        binding.goodPrice.setText(good.getPriceA().doubleValue() + " 元");
        binding.goodInfo.setText(good.getInfo());
        binding.goodNum.setText("0");
        binding.goodName.setText(good.getName());
    }
}