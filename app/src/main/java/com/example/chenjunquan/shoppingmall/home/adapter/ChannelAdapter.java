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
 * Created by Administrator on 2017/12/27.
 */

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context context, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = context;
        this.datas = channel_info;
    }

    @Override
    public int getCount() {
        return datas.size();
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
    public View getView(int position, View convertView, ViewGroup group) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            holder = new ViewHolder();
            holder.ic_icon = convertView.findViewById(R.id.iv_channel);
            holder.tv_title = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + channelInfoBean.getImage()).into(holder.ic_icon);
        holder.tv_title.setText(channelInfoBean.getChannel_name());
        return convertView;
    }

    static class ViewHolder {
        ImageView ic_icon;
        TextView tv_title;
    }
}
