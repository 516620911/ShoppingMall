package com.example.chenjunquan.shoppingmall.community.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.chenjunquan.shoppingmall.base.BaseFragment;

/**
 * 发现
 * Created by Administrator on 2017/12/26.
 */

public class CommunityFragment extends BaseFragment {
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
        textView.setText("发现内容");
        super.initData();
    }
}
