package com.example.chenjunquan.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chenjunquan.shoppingmall.home.bean.ResultBeanData;
import com.example.chenjunquan.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class ActViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ActInfoBean> mAct_info;
    public ActViewPagerAdapter(Context context, List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
        this.mContext=context;
        this.mAct_info=act_info;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+mAct_info.get(position).getIcon_url()).into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"position="+position,Toast.LENGTH_LONG).show();
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mAct_info.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
