package com.example.chenjunquan.shoppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.chenjunquan.shoppingmall.R;
import com.example.chenjunquan.shoppingmall.base.BaseFragment;
import com.example.chenjunquan.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.example.chenjunquan.shoppingmall.home.bean.ResultBeanData;
import com.example.chenjunquan.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 主页面
 * Created by Administrator on 2017/12/26.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG =
            HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_home,
                null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        ib_top.setVisibility(View.VISIBLE);
        initListener();
        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("response", e.getMessage());
                    }

                    //请求成功的数据
                    @Override
                    public void onResponse(String response, int id) {

                        //Log.i("response", response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            adapter = new HomeFragmentAdapter(mContext, resultBean);
            rvHome.setAdapter(adapter);
            //设置布局
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            //监听跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position<=3) {
                        //隐藏
                        ib_top.setVisibility(View.INVISIBLE);
                    }else{
                        ib_top.setVisibility(View.VISIBLE);
                        //显示
                    }
                    return 1;
                }
            });
            rvHome.setLayoutManager(manager);


        } else {

        }
        //Log.i("re---------------",resultBean.getHot_info().get(0).getName());

    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
