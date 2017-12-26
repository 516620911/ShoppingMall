package com.example.chenjunquan.shoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chenjunquan.shoppingmall.R;
import com.example.chenjunquan.shoppingmall.base.BaseFragment;
import com.example.chenjunquan.shoppingmall.community.fragment.CommunityFragment;
import com.example.chenjunquan.shoppingmall.home.fragment.HomeFragment;
import com.example.chenjunquan.shoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.example.chenjunquan.shoppingmall.type.fragment.TypeFragment;
import com.example.chenjunquan.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_type)
    RadioButton rbType;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton rbCart;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        initFragment();
        initlistener();
        rgMain.check(R.id.rb_home);
    }

    private void initlistener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment fragment = getFragment(position);
                //上次显示的Fragment，当前将要显示的Fragment
                switchFragment(tempFragment,fragment);
            }
        });
    }

    private void switchFragment(BaseFragment fromFragment, BaseFragment nextFragment) {
        if(tempFragment!=nextFragment){
            tempFragment=nextFragment;
            if(nextFragment!=null){
                //开启fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //之前没有添加就添加如果添加过就直接show
                if(!nextFragment.isAdded()){
                    if(fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else{
                    if(fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private BaseFragment getFragment(int position) {
        if(fragments!=null&&fragments.size()>0){
            return fragments.get(position);
        }
        return null;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }


}
