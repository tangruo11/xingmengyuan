package com.example.cnvus.partnerFrg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cnvus.R;
import com.example.cnvus.Utils.AssetsUtils;
import com.example.cnvus.bean.StarInfoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PartnerFragment extends Fragment implements View.OnClickListener , AdapterView.OnItemSelectedListener {
  ImageView maniv,womaniv;
  Spinner mansp,womansp;
  Button prizebtn,analysisbtn;
  List<StarInfoBean.StarinfoBean> starinfo;
  List<String> nameList;//存放星座名称
    private ArrayAdapter<String> adapter;
    private Map<String, Bitmap> contentLogoImgMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_partner, container, false);
        initView(view);
        Bundle bundle = getArguments();
        StarInfoBean starInfoBean = (StarInfoBean) bundle.getSerializable("info");
        starinfo = starInfoBean.getStarinfo();
        nameList=new ArrayList<>();
        for (StarInfoBean.StarinfoBean t:starinfo)
        {
            nameList.add(t.getName());
        }
        //创建适配，只有一个textview,list也只有一种数据类型，所以用简单的ArrayAdapter即可
        adapter = new ArrayAdapter<>(getContext(), R.layout.item_partnerfrag_sp,R.id.item_partner_tv,nameList);
        //设置适配器
         mansp.setAdapter(adapter);
         womansp.setAdapter(adapter);
         //获取第一个图片资源显示在imageview上
        String logoname = starinfo.get(0).getLogoname();
        contentLogoImgMap = AssetsUtils.getContentLogoImgMap();
        Bitmap bitmap = contentLogoImgMap.get(logoname);
        maniv.setImageBitmap(bitmap);
        womaniv.setImageBitmap(bitmap);
        mansp.setOnItemSelectedListener(this);
        womansp.setOnItemSelectedListener(this);
        return view;
    }



    private void initView(View view) {
        maniv=view.findViewById(R.id.partner_frag_iv_man);
        womaniv=view.findViewById(R.id.partner_frag_iv_woman);
        mansp=view.findViewById(R.id.partner_frag_sp_man);
        womansp=view.findViewById(R.id.partner_frag_sp_woman);
        prizebtn=view.findViewById(R.id.partner_frag_btn_prize);
        analysisbtn=view.findViewById(R.id.partner_frag_btn_analysis);
        prizebtn.setOnClickListener(this);
        analysisbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.partner_frag_btn_prize:
                break;
            case R.id.partner_frag_btn_analysis:
                Intent intent=new Intent(getContext(),PartnerAnalysActivity.class);
                //获取sp选中的位置
                int position1 = mansp.getSelectedItemPosition();
                int position2 = womansp.getSelectedItemPosition();
                intent.putExtra("mantv",starinfo.get(position1).getName());
                intent.putExtra("womantv",starinfo.get(position2).getName());
                String logoname1 = starinfo.get(position1).getLogoname();
                String logoname2 = starinfo.get(position2).getLogoname();
                intent.putExtra("manivstr",logoname1);
                intent.putExtra("womanivstr",logoname2);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.partner_frag_sp_man:
                String logoname = starinfo.get(position).getLogoname();
                Bitmap bitmap = contentLogoImgMap.get(logoname);
                maniv.setImageBitmap(bitmap);
                break;
            case R.id.partner_frag_sp_woman:
                String logoname1 = starinfo.get(position).getLogoname();
                Bitmap bitmap1 = contentLogoImgMap.get(logoname1);
                womaniv.setImageBitmap(bitmap1);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}