package com.example.cnvus.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cnvus.Adapter.GuideAdapter;
import com.example.cnvus.Mainactivity;
import com.example.cnvus.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    ViewPager guideVp;
    int resId[]={R.mipmap.loading1,R.mipmap.loading2,R.mipmap.loading3};
    List<ImageView> mdatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        guideVp=findViewById(R.id.guide_vp);
        initPager();
        setListener();//为第三个图片设置监听事件

    }

    private void setListener() {
        ImageView lastIv = mdatas.get(mdatas.size() - 1);
        lastIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this, Mainactivity.class);
                startActivity(intent);
            }
        });

    }

    private void initPager() {
        mdatas=new ArrayList<>();
        for (int i = 0; i <resId.length ; i++) {
            ImageView im1=new ImageView(this);
            im1.setImageResource(resId[i]);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            im1.setLayoutParams(lp);
            mdatas.add(im1);
        }
        GuideAdapter adapter=new GuideAdapter(this,mdatas);
        guideVp.setAdapter(adapter);

    }
}