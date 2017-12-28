package com.example.chenjunquan.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chenjunquan.shoppingmall.R;
import com.example.chenjunquan.shoppingmall.home.bean.ResultBeanData;
import com.example.chenjunquan.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class RecommendGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> mRecommend_info;
    public RecommendGridViewAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        mContext=context;
        mRecommend_info=recommend_info;
    }

    @Override
    public int getCount() {
        return mRecommend_info.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup group) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = mRecommend_info.get(i);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendInfoBean.getName());
        viewHolder.tv_price.setText("$"+recommendInfoBean.getCover_price());
        return convertView;
    }
    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;

    }
}
