package com.example.chenjunquan.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chenjunquan.shoppingmall.R;
import com.example.chenjunquan.shoppingmall.home.bean.ResultBeanData;
import com.example.chenjunquan.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //广告横幅
    public static final int BANNER = 0;
    //频道
    public static final int CHANNEL = 1;
    //活动
    public static final int ACT = 2;
    //秒杀
    public static final int SECKILL = 3;
    //推荐
    public static final int RECOMMEND = 4;
    //热卖
    public static final int HOT = 5;
    private Context mContext;
    private ResultBeanData.ResultBean mResultBean;
    /**
     * 当前类型
     */
    private int currentType = 0;
    private final LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean resultBean) {
        this.mContext = context;
        this.mResultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }
        return null;
    }

    /**
     * 视图绑定数据
     */

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //图片轮播图部分的数据
            List<ResultBeanData.ResultBean.BannerInfoBean> banner_info = mResultBean.getBanner_info();
            bannerViewHolder.setData(banner_info);
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(mResultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(mResultBean.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(mResultBean.getSeckill_info().getList());
        }


    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_seckill;
        private TextView tv_time_seckill;
        private RecyclerView rv_seckill;

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);

        }

        public void setData(List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> listBeans) {
            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, listBeans);
            rv_seckill.setAdapter(adapter);
            //是否是倒顺序
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private ViewPager viewpager;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            viewpager = itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            //设置Viewpager适配器
            ActViewPagerAdapter adapter = new ActViewPagerAdapter(mContext, act_info);
            viewpager.setAdapter(adapter);
            viewpager.setPageMargin(20);
            viewpager.setOffscreenPageLimit(3);//>=3
            viewpager.setPageTransformer(true, new RotateDownPageTransformer());
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private GridView gcChannel;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            gcChannel = itemView.findViewById(R.id.gv_channel);
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //设置GridView的适配器
            ChannelAdapter adapter = new ChannelAdapter(mContext, channel_info);
            gcChannel.setAdapter(adapter);
            gcChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> view, View view1, int i, long l) {

                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private View itemView;
        private Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext = context;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            ArrayList<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            //动画
            banner.setBannerAnimation(Transformer.DepthPage);
            //指示器
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //banner加载viewpager轮播最后要start
            banner.setImages(imagesUrl).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(Constants.BASE_URL_IMAGE + path).into(imageView);
                }
            }).start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    /**
     * 当前
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }
}
