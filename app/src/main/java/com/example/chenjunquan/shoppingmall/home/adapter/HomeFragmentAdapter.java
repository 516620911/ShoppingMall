package com.example.chenjunquan.shoppingmall.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chenjunquan.shoppingmall.R;
import com.example.chenjunquan.shoppingmall.app.GoodsInfoActivity;
import com.example.chenjunquan.shoppingmall.home.bean.GoodsBean;
import com.example.chenjunquan.shoppingmall.home.bean.ResultBeanData;
import com.example.chenjunquan.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;

import java.text.SimpleDateFormat;
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
    private static final String GOODS_BEAN = "goodsBean";
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
        }else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        }
        else if (viewType == HOT) {
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
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
            seckillViewHolder.setData(mResultBean.getSeckill_info());
        }else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(mResultBean.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(mResultBean.getHot_info());
        }


    }
     class HotViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_hot;
        private GridView gv_hot;
        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            tv_more_hot=itemView.findViewById(R.id.tv_more_hot);
            gv_hot=itemView.findViewById(R.id.gv_hot);


        }

         public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
             HotViewAdapter adapter = new HotViewAdapter(mContext, hot_info);
             gv_hot.setAdapter(adapter);
             gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                     Toast.makeText(mContext,"positon="+i,Toast.LENGTH_LONG).show();
                     ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hot_info.get(i);
                     //热卖商品信息类
                     GoodsBean goodsBean = new GoodsBean();
                     goodsBean.setCover_price(hotInfoBean.getCover_price());
                     goodsBean.setFigure(hotInfoBean.getFigure());
                     goodsBean.setName(hotInfoBean.getName());
                     goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                     startGoodsInfoActivity(goodsBean);
                 }
             });
         }
     }
     class RecommendViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            tv_more_recommend=itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend=itemView.findViewById(R.id.gv_recommend);

        }

         public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
             //设置GridView的适配器
             RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(mContext, recommend_info);
             gv_recommend.setAdapter(adapter);
             gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                     Toast.makeText(mContext,"positon="+i,Toast.LENGTH_LONG).show();
                     ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(i);
                     //热卖商品信息类
                     GoodsBean goodsBean = new GoodsBean();
                     goodsBean.setCover_price(recommendInfoBean.getCover_price());
                     goodsBean.setFigure(recommendInfoBean.getFigure());
                     goodsBean.setName(recommendInfoBean.getName());
                     goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                     startGoodsInfoActivity(goodsBean);
                 }
             });
         }
     }
    //相差多少时间
    private long dt=0;

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("HandlerLeak")
        private Handler mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt=dt-1000;
                //秒杀倒计时
                String time = new SimpleDateFormat("HH:mm:ss").format(dt);
                tv_time_seckill.setText(time);
                mHandler.sendEmptyMessageDelayed(0,1000);
                if(dt<=0){
                    mHandler.removeCallbacksAndMessages(null);
                }
            }
        };
        private TextView tv_more_seckill;
        private TextView tv_time_seckill;
        private RecyclerView rv_seckill;

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);

        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBeans) {
            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, seckillInfoBeans.getList());
            rv_seckill.setAdapter(adapter);
            //是否是倒顺序
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
            adapter.setListener(new SeckillRecyclerViewAdapter.OnSeckillRecyclerViewListener() {
                @Override
                public void OnItemClick(int postion) {
                    Toast.makeText(mContext,"秒杀="+getLayoutPosition(),Toast.LENGTH_LONG).show();
                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean bean = seckillInfoBeans.getList().get(postion);
                    //热卖商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(bean.getCover_price());
                    goodsBean.setFigure(bean.getFigure());
                    goodsBean.setName(bean.getName());
                    goodsBean.setProduct_id(bean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
            //秒杀倒计时
            dt=Integer.valueOf(seckillInfoBeans.getEnd_time())-Integer.valueOf(seckillInfoBeans.getStart_time());
            String time = new SimpleDateFormat("HH:mm:ss").format(dt);
            tv_time_seckill.setText(time);
            mHandler.removeMessages(0);
            mHandler.sendEmptyMessageDelayed(0,1000);

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

        public void setData(final List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
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
                    //startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }
    //启动商品信息列表页面
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return 6;
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
