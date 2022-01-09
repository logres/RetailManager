package com.example.market;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.api.ShoppingCartLine;
import com.example.market.databinding.FragmentCartBinding;
import com.example.proxy.ShoppingCartLineProxy;

public class cartInfoAdapter extends SimpleAdapter{

    Context context;
    List<? extends Map<String, ?>> data;
    List<Integer> pick;
    cart cart;

    public cartInfoAdapter (Context context, List<? extends Map<String, ?>> data,
                            int resource, String[] from, int[] to, List<Integer> pick, cart cart) {
        super(context, data, resource, from, to);
        this.context = context;
        this.data = data;
        this.pick = pick;
        this.cart = cart;
    }

    public View getView(int position, View convertView, ViewGroup parent) {     //重写，添加监听器

        View v = super.getView(position, convertView, parent);

        final int p = position;

        Button btn=(Button) v.findViewById(R.id.cart_info_delete);
        CheckBox checkBox = v.findViewById(R.id.cart_info_check);

        btn.setOnClickListener(new OnClickListener() {  //删除购物车项事件
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "删除成功！",Toast.LENGTH_SHORT).show();

                ShoppingCartLine line = cart.list.get(p);
                ShoppingCartLineProxy.delete(line.getId());
                cart.list.remove(p);
                data.remove(p);

                int mark = -1;
                for(int i = 0; i < pick.size(); i++){
                    if(pick.get(i) == p){
                        mark = i;
                    }
                    if(pick.get(i) > p){
                        pick.add(i, pick.get(i)-1);
                        pick.remove(i+1);
                    }
                }
                if(mark != -1){
                    pick.remove(mark);
                }

                cart.calculatePrice();
                notifyDataSetChanged();
            }
        });

        checkBox.setOnClickListener(new OnClickListener() {     //选中购物车项事件
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    if(!pick.contains(p)){
                        pick.add(p);
                        cart.calculatePrice();
                    }
                }
                else {
                    if(pick.contains(p)){
                        for(int i = 0; i < pick.size(); i++){
                            if(pick.get(i) == p){
                                pick.remove(i);
                                break;
                            }
                        }
                        cart.calculatePrice();
                    }
                }
            }
        });

        return v;
    }
}
