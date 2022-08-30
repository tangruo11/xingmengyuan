package com.example.cnvus.luckFrg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cnvus.Adapter.LuckAnalysisAdapter;
import com.example.cnvus.R;
import com.example.cnvus.Utils.HttpUtils;
import com.example.cnvus.Utils.URLContent;
import com.example.cnvus.bean.LuckBean;
import com.example.cnvus.bean.LuckItemBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LuckAnalysisActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lucklv;
    TextView nameTv;
    ImageView backIv;
    String name;
    List<LuckItemBean> list;
    Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 2:
                    LuckAnalysisAdapter analysisAdapter=new LuckAnalysisAdapter(LuckAnalysisActivity.this, (List<LuckItemBean>) msg.obj);
                    lucklv.setAdapter(analysisAdapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_luck_analysis);
        Intent intent = getIntent();
       name= intent.getStringExtra("name");//星座名称
        String luckurl= URLContent.getLuckURL(name);//网址
        initView();
        loadNet(luckurl);

    }

    private void loadNet(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final LuckAnalysisAdapter analysisAdapter;
                String luckjsonFromNet = HttpUtils.getJsonFromNet(url);
                Gson gson=new Gson();
                LuckBean luckBean = gson.fromJson(luckjsonFromNet, LuckBean.class);
                list=new ArrayList<>();
                LuckItemBean im1=new LuckItemBean("综合运势",luckBean.getSummary(), Color.RED);
                LuckItemBean im2=new LuckItemBean("爱情运势",luckBean.getLove(),Color.BLUE);
                LuckItemBean im3=new LuckItemBean("事业运势",luckBean.getWork(),Color.GREEN);
                LuckItemBean im4=new LuckItemBean("健康运势",luckBean.getHealth(),Color.GRAY);
                LuckItemBean im5=new LuckItemBean("财富运势",luckBean.getQFriend(),Color.BLUE);
                list.add(im1);
                list.add(im2);
                list.add(im3);
                list.add(im4);
                list.add(im5);
                Message msg=new Message();
                msg.what=2;
                msg.obj=list;
                handler.sendMessage(msg);


                }


            }).start();
        }



    private void initView() {
        lucklv=findViewById(R.id.luckanalysis_lv);
        nameTv=findViewById(R.id.star_title_tv);
        nameTv.setText(name);
        backIv=findViewById(R.id.star_title_iv_back);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.star_title_iv_back:
                finish();
                break;
        }
    }
}