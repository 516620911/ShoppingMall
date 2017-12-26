package com.example.chenjunquan.shoppingmall.shoppingcart.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.chenjunquan.shoppingmall.base.BaseFragment;

/**
 * 购物车
 * Created by Administrator on 2017/12/26.
 */

public class ShoppingCartFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView=new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("购物车内容");
        super.initData();
    }
}
