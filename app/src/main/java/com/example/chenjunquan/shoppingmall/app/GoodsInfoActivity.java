package com.example.chenjunquan.shoppingmall.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chenjunquan.shoppingmall.R;
import com.example.chenjunquan.shoppingmall.home.bean.GoodsBean;
import com.example.chenjunquan.shoppingmall.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsInfoActivity extends Activity {

    @Bind(R.id.ib_good_info_back)
    ImageButton mIbGoodInfoBack;
    @Bind(R.id.ib_good_info_more)
    ImageButton mIbGoodInfoMore;
    @Bind(R.id.iv_good_info_image)
    ImageView mIvGoodInfoImage;
    @Bind(R.id.tv_good_info_name)
    TextView mTvGoodInfoName;
    @Bind(R.id.tv_good_info_desc)
    TextView mTvGoodInfoDesc;
    @Bind(R.id.tv_good_info_price)
    TextView mTvGoodInfoPrice;
    @Bind(R.id.tv_good_info_store)
    TextView mTvGoodInfoStore;
    @Bind(R.id.tv_good_info_style)
    TextView mTvGoodInfoStyle;
    @Bind(R.id.wb_good_info_more)
    WebView mWbGoodInfoMore;
    @Bind(R.id.tv_good_info_callcenter)
    TextView mTvGoodInfoCallcenter;
    @Bind(R.id.tv_good_info_collection)
    TextView mTvGoodInfoCollection;
    @Bind(R.id.tv_good_info_cart)
    TextView mTvGoodInfoCart;
    @Bind(R.id.btn_good_info_addcart)
    Button mBtnGoodInfoAddcart;
    @Bind(R.id.ll_goods_root)
    LinearLayout mLlGoodsRoot;
    @Bind(R.id.tv_more_share)
    TextView mTvMoreShare;
    @Bind(R.id.tv_more_search)
    TextView mTvMoreSearch;
    @Bind(R.id.tv_more_home)
    TextView mTvMoreHome;
    @Bind(R.id.btn_more)
    Button mBtnMore;
    @Bind(R.id.ll_root)
    LinearLayout mLlRoot;
    private GoodsBean mDataForView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);

        GoodsBean goodsBean = (GoodsBean) getIntent().getSerializableExtra("goodsBean");
        if (goodsBean != null) {
            setDataForView(goodsBean);
        }
    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.iv_good_info_image, R.id.tv_good_info_name, R.id.tv_good_info_desc, R.id.tv_good_info_price, R.id.tv_good_info_store, R.id.tv_good_info_style, R.id.wb_good_info_more, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id.ll_goods_root, R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home, R.id.btn_more, R.id.ll_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                mLlRoot.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_good_info_image:
                break;
            case R.id.tv_good_info_name:
                break;
            case R.id.tv_good_info_desc:
                break;
            case R.id.tv_good_info_price:
                break;
            case R.id.tv_good_info_store:
                break;
            case R.id.tv_good_info_style:
                break;
            case R.id.wb_good_info_more:
                break;
            case R.id.tv_good_info_callcenter:

                break;
            case R.id.tv_good_info_collection:
                break;
            case R.id.tv_good_info_cart:
                break;
            case R.id.btn_good_info_addcart:
                break;
            case R.id.ll_goods_root:
                break;
            case R.id.tv_more_share:
                break;
            case R.id.tv_more_search:
                break;
            case R.id.tv_more_home:
                break;
            case R.id.btn_more:
                break;
            case R.id.ll_root:
                break;
        }
    }

    public void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(getApplicationContext()).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(mIvGoodInfoImage);
        //设置文本
        mTvGoodInfoName.setText(goodsBean.getName());
        mTvGoodInfoPrice.setText(goodsBean.getCover_price());
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if(product_id!=null){
            mWbGoodInfoMore.loadUrl("http://120.76.213.0:82/zentao/my/");
            WebSettings settings = mWbGoodInfoMore.getSettings();
            settings.setUseWideViewPort(true);//支持双击页面
            settings.setJavaScriptEnabled(true);
            //优先使用缓存
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWbGoodInfoMore.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return super.shouldOverrideUrlLoading(view, url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    }
                    //反回true不调用系统浏览器或第三方
                    return true;
                }
            });
        }
    }
}
