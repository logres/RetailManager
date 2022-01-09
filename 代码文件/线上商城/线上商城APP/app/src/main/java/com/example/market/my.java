package com.example.market;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.api.Customer;
import com.example.api.NetCustomer;
import com.example.market.databinding.FragmentMyBinding;
import com.example.proxy.CustomerProxy;
import com.example.proxy.NetCustomerProxy;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link my#newInstance} factory method to
 * create an instance of this fragment.
 */
public class my extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public my() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment my.
     */
    // TODO: Rename and change types and number of parameters
    public static my newInstance(String param1, String param2) {
        my fragment = new my();
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
    public static FragmentMyBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity activity = (MainActivity)this.getActivity();
        activity.myBinding = binding;

        int ID = activity.ID;
        NetCustomer netCustomer = NetCustomerProxy.findById(ID);
        Customer customer = CustomerProxy.findById(netCustomer.getCustomerId());

        binding.myName.setText(customer.getName());
        binding.myAddr.setText(customer.getAddress());
        binding.myPhone.setText(customer.getPhone());
        binding.myUsername.setText(netCustomer.getUsername());
        binding.myPassword.setText(netCustomer.getPassword());

        activity.setEditable(binding, false);
    }
}