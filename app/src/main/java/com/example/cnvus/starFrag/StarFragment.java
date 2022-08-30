package com.example.cnvus.starFrag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cnvus.Adapter.StarBaseAdapter;
import com.example.cnvus.Adapter.StarPagerAdapter;
import com.example.cnvus.R;
import com.example.cnvus.bean.StarInfoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 星座fragment,包括viewpager和gridview
 */

public class StarFragment extends Fragment {
    ViewPager starVp;
    GridView starGv;
    LinearLayout pointLayout;
   private List<StarInfoBean.StarinfoBean> mdatas;
   int[] imgId={R.mipmap.pic_guanggao,R.mipmap.pic_star};
   List<ImageView> ivList;
   List<ImageView>pointList;
   Handler handler=new Handler(Looper.getMainLooper()){
       @Override
       public void handleMessage(@NonNull Message msg) {
           switch (msg.what) {
               case 1:
                   //切换图片
                   int currentItem = starVp.getCurrentItem();
                   if (currentItem==ivList.size()-1){
                       currentItem=0;
                   }else {currentItem+=1;}
                    starVp.setCurrentItem(currentItem);
                   break;

           }
           //形成循环发送-接收消息的效果，在接收消息的同时也要进行消息发送
           handler.sendEmptyMessageDelayed(1,5000);
       }
   };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_star, container, false);
         initView(view);
         //获取主界面传来的数据
        Bundle bundle=getArguments();
        StarInfoBean starInfoBean = (StarInfoBean) bundle.getSerializable("info");
        mdatas= starInfoBean.getStarinfo();//星座集合的数据
        //创建适配器
        StarBaseAdapter adapter=new StarBaseAdapter(getContext(),mdatas);
        starGv.setAdapter(adapter);
        initPager();
        setVPListener();
        setGVlistener();
        handler.sendEmptyMessageDelayed(1,5000);
        return view;
    }

    /**
     * 设置GV的监听事件
     */
    private void setGVlistener() {
        starGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarInfoBean.StarinfoBean starinfoBean = mdatas.get(position);
                Intent intent=new Intent(getContext(),StarAnalysisActivity.class);
                intent.putExtra("info",starinfoBean);
                startActivity(intent);

            }
        });
    }

    /**
     * 设置VP的滑动监听
     */
    private void setVPListener() {
        starVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < pointList.size(); i++) {
                    pointList.get(i).setImageResource(R.mipmap.point_normal);
                }
                pointList.get(position).setImageResource(R.mipmap.point_focus);
            }
        });
    }

    private void initPager() {
        ivList=new ArrayList<>();
        pointList=new ArrayList<>();
        for (int i = 0; i <imgId.length ; i++) {
            ImageView iv=new ImageView(getContext());
            iv.setImageResource(imgId[i]);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);
            ivList.add(iv);
            ImageView piv=new ImageView(getContext());
            piv.setImageResource(R.mipmap.point_normal);
            LinearLayout.LayoutParams Plp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Plp.setMargins(20,0,0,0);
            piv.setLayoutParams(Plp);
            pointList.add(piv);//圆球控件imageview加入集合进行统一管理
            pointLayout.addView(piv);//添加到布局当中
        }
        //默认第一个小圆点是选中状态
        pointList.get(0).setImageResource(R.mipmap.point_focus);
        StarPagerAdapter adapter=new StarPagerAdapter(getContext(),ivList);
        starVp.setAdapter(adapter);

    }

    private void initView(View view) {
        starVp=view.findViewById(R.id.starfrag_vp);
        starGv=view.findViewById(R.id.starfrag_gv);
        pointLayout=view.findViewById(R.id.starfrag_layout);
    }
}
