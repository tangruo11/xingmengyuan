package com.example.cnvus.partnerFrg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cnvus.R;
import com.example.cnvus.Utils.AssetsUtils;
import com.example.cnvus.Utils.HttpUtils;
import com.example.cnvus.Utils.URLContent;
import com.example.cnvus.bean.HttpBean;
import com.google.gson.Gson;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PartnerAnalysActivity extends AppCompatActivity implements View.OnClickListener {

    TextView manTv;
    TextView womanTv;
    TextView pdtv;
    TextView vstv;
    TextView pftv;
    TextView bztv;
    TextView jxtv;
    TextView zytv;
    TextView titletv;
    CircleImageView manIv,womanIv;
    ImageView backIv;
    private String mantv;
    private String womantv;
    private String manivstr;
    private String womanivstr;
    private Map<String, Bitmap> contentLogoImgMap;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
           switch (msg.what){
               case 1:
                   HttpBean.ResultBean   result= (HttpBean.ResultBean) msg.obj;
                   pftv.setText("配对评分："+result.getXiangyue()+" "+result.getJieguo() );
                   bztv.setText("星座比重："+result.getBizhong());
                   jxtv.setText("解析：\n"+result.getLianai());
                   zytv.setText("注意事项：\n"+result.getZhuyi());
                   pdtv.setText("星座配对-"+mantv+"和"+womantv+"配对");
                   vstv.setText(mantv+" VS "+womantv);
                   titletv.setText("配对分析");
                   break;

           }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_analys);
        initView();
        getLastData();
        getHttpData();
    }

    private void getHttpData() {


        final String url = URLContent.getPartnerURL(mantv, womantv);
        new Thread(new Runnable() {
            @Override
            public void run() {
             String   jsonFromNet=  HttpUtils.getJsonFromNet(url);
                Gson gson=new Gson();
                final HttpBean httpBean = gson.fromJson(jsonFromNet, HttpBean.class);
                if (httpBean!=null) {
                        final HttpBean.ResultBean result = httpBean.getResult();
                          Message msg=new Message();
                          msg.obj=result;
                          msg.what=1;
                          handler.sendMessage(msg);

                }


            }
        }).start();





    }

    private void getLastData() {
        Intent intent=getIntent();
        mantv = intent.getStringExtra("mantv");
        womantv = intent.getStringExtra("womantv");
        manivstr = intent.getStringExtra("manivstr");
        womanivstr = intent.getStringExtra("womanivstr");
        //设置能够显示的控件的信息
        contentLogoImgMap = AssetsUtils.getContentLogoImgMap();
        Bitmap bitmap = contentLogoImgMap.get(manivstr);
        Bitmap bitmap1 = contentLogoImgMap.get(womanivstr);
        manIv.setImageBitmap(bitmap);
        womanIv.setImageBitmap(bitmap1);
        manTv.setText(mantv);
        womanTv.setText(womantv);


    }

    private void initView() {
        backIv=findViewById(R.id.star_title_iv_back);
        manIv=findViewById(R.id.patneranalysis_iv_man);
        womanIv=findViewById(R.id.patneranalysis_iv_woman);
        pdtv=findViewById(R.id.partneranlysis_tv_pd);
        vstv=findViewById(R.id.partneranlysis_tv_vs);
        pftv=findViewById(R.id.partneranlysis_tv_pf);
        bztv=findViewById(R.id.partneranlysis_tv_bz);
        jxtv=findViewById(R.id.partneranlysis_tv_jx);
        zytv=findViewById(R.id.partneranlysis_tv_zy);
        titletv=findViewById(R.id.star_title_tv);
        manTv=findViewById(R.id.patneranalysis_tv_man);
        womanTv=findViewById(R.id.patneranalysis_tv_woman);
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