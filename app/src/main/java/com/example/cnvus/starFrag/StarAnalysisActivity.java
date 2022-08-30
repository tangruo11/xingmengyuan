package com.example.cnvus.starFrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cnvus.R;
import com.example.cnvus.Utils.AssetsUtils;
import com.example.cnvus.bean.StarInfoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAnalysisActivity extends AppCompatActivity implements View.OnClickListener {
    TextView titleTv,nameTv,dateTv;
    ImageView backIv;
    CircleImageView iconIV;
    ListView analysisLv;
    StarInfoBean.StarinfoBean infobean;
    private Map<String, Bitmap> contentLogoImgMap;
    TextView footerTv;
    List<StarAnalysisBean> mdatas;
    AnalysisBaseAdapter analysisBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_analysis);
        //获取上一级界面传递过来的数据
        Intent intent = getIntent();
        infobean = (StarInfoBean.StarinfoBean) intent.getSerializableExtra("info");
        initView();
        mdatas=new ArrayList<>();
        addDataToList();
         analysisBaseAdapter=new AnalysisBaseAdapter(this,mdatas);
        analysisLv.setAdapter(analysisBaseAdapter);
        



    }

    /**
     * 加载listview当中的数据源
     */
    private void addDataToList() {
        StarAnalysisBean sab1=new StarAnalysisBean("性格特点：",infobean.getTd(),R.color.lightblue);
        StarAnalysisBean sab2=new StarAnalysisBean("掌管工位：",infobean.getGw(),R.color.lightpink);
        StarAnalysisBean sab3=new StarAnalysisBean("显阴阳性：",infobean.getYy(),R.color.lightgreen);
        StarAnalysisBean sab4=new StarAnalysisBean("最大特征：",infobean.getTz(),R.color.purple);
        StarAnalysisBean sab5=new StarAnalysisBean("主管星球：",infobean.getZg(),R.color.orange);
        StarAnalysisBean sab6=new StarAnalysisBean("幸运颜色：",infobean.getYs(),R.color.colorAccent);
        StarAnalysisBean sab7=new StarAnalysisBean("搭配珠宝：",infobean.getZb(),R.color.colorPrimary);
        StarAnalysisBean sab8=new StarAnalysisBean("幸运号码：",infobean.getHm(),R.color.grey);
        StarAnalysisBean sab9=new StarAnalysisBean("相配属性：",infobean.getJs(),R.color.lightblue);
        mdatas.add(sab1);
        mdatas.add(sab2);
        mdatas.add(sab3);
        mdatas.add(sab4);
        mdatas.add(sab5);
        mdatas.add(sab6);
        mdatas.add(sab7);
        mdatas.add(sab8);
        mdatas.add(sab9);


    }

    private void initView() {
        titleTv=findViewById(R.id.star_title_tv);
        nameTv=findViewById(R.id.staranalysis_tv_name);
        backIv=findViewById(R.id.star_title_iv_back);
        iconIV=findViewById(R.id.staranalysis_iv);
        dateTv=findViewById(R.id.staranalysis_tv_date);
        analysisLv=findViewById(R.id.staranalysis_lv);
        titleTv.setText("星座详情");
        backIv.setOnClickListener(this);
        nameTv.setText(infobean.getName());
        dateTv.setText(infobean.getDate());
        contentLogoImgMap = AssetsUtils.getContentLogoImgMap();
        Bitmap bitmap = contentLogoImgMap.get(infobean.getLogoname());
        iconIV.setImageBitmap(bitmap);
        View view=LayoutInflater.from(this).inflate(R.layout.footer_star_analysis,null,false);
        analysisLv.addFooterView(view);
        footerTv = view.findViewById(R.id.footerstar_tv_info);
        footerTv.setText(infobean.getInfo());


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