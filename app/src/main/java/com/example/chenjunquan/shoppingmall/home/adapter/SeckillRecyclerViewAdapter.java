package com.example.chenjunquan.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> mListBeans;

    public SeckillRecyclerViewAdapter(Context context, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> listBeans) {
        this.mContext = context;
        this.mListBeans = listBeans;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure=itemView.findViewById(R.id.iv_figure);
            tv_cover_price=itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price=itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mListener!=null) {
                        mListener.OnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = mListBeans.get(position);
        //绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {

        return mListBeans.size();
    }

    public void setListener(OnSeckillRecyclerViewListener listener) {
        mListener = listener;
    }

    private OnSeckillRecyclerViewListener mListener;
    public interface OnSeckillRecyclerViewListener{
        void OnItemClick(int postion);
    }
}
