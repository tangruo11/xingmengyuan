package com.example.cnvus;


import android.os.Bundle;

import android.widget.RadioGroup;


import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cnvus.Adapter.FragPagerAdapter;
import com.example.cnvus.Utils.AssetsUtils;
import com.example.cnvus.bean.StarInfoBean;
import com.example.cnvus.luckFrg.LuckFragment;
import com.example.cnvus.meFrg.MeFragment;
import com.example.cnvus.partnerFrg.PartnerFragment;
import com.example.cnvus.starFrag.StarFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class Mainactivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup mainRg;
    ViewPager vp;
//申明四个按钮对应的Fragment,并初始化对象
    Fragment starFragment,partnerFragment,luckFragment,meFragment;
    Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRg=findViewById(R.id.main_rg);
        mainRg.setOnCheckedChangeListener(this);
         vp=findViewById(R.id.main_vp);

         //加载星座相关数据 /assets/xzcontent/xzcontent.json
        StarInfoBean infoBean = loadData();
        AssetsUtils.saveBitmapFromAssets(this,infoBean);
        bundle=new Bundle();
        bundle.putSerializable("info",infoBean);
        initVP();

    }

    private StarInfoBean loadData() {
        String json = AssetsUtils.getJsonFromAssets(this, "xzcontent/xzcontent.json");
        Gson gson=new Gson();
        StarInfoBean starInfoBean = gson.fromJson(json, StarInfoBean.class);
        return starInfoBean;

    }

    private void initVP() {
        List<Fragment> fragmentList=new ArrayList<>();
        starFragment=new StarFragment();
        starFragment.setArguments(bundle);
        partnerFragment=new PartnerFragment();
        partnerFragment.setArguments(bundle);
        luckFragment=new LuckFragment();
        luckFragment.setArguments(bundle);
        meFragment=new MeFragment();
        meFragment.setArguments(bundle);
        fragmentList.add(starFragment);
        fragmentList.add(partnerFragment);
        fragmentList.add(luckFragment);
        fragmentList.add(meFragment);
        //创建GV适配器
        FragPagerAdapter fragPagerAdapter=new FragPagerAdapter(getSupportFragmentManager(),fragmentList);
        vp.setAdapter(fragPagerAdapter);
        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
               mainRg.check(mainRg.getChildAt(position).getId());
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //监听点击了哪个单选按钮
        switch (checkedId) {
            case R.id.main_rb_star:
                vp.setCurrentItem(0);
                break;
            case R.id.main_rb_partner:
                vp.setCurrentItem(1);
                break;
            case R.id.main_rb_luck:
                vp.setCurrentItem(2);
                break;
            case R.id.main_rb_me:
                vp.setCurrentItem(3);
                break;
        }
    }
}