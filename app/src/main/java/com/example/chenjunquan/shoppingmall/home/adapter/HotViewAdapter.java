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

public class HotViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> mHot_info;
    public HotViewAdapter(Context context, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        mContext=context;
        mHot_info=hot_info;
    }

    @Override
    public int getCount() {
        return mHot_info.size();
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
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = mHot_info.get(i);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText("$"+hotInfoBean.getCover_price());
        return convertView;
    }
    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;

    }
}
