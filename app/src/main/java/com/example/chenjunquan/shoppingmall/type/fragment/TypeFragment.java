package com.example.chenjunquan.shoppingmall.type.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.chenjunquan.shoppingmall.base.BaseFragment;

/**
 * 分类
 * Created by Administrator on 2017/12/26.
 */

public class TypeFragment extends BaseFragment {
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
        textView.setText("分类内容");
        super.initData();
    }
}
