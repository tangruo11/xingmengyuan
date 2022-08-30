package com.example.cnvus.meFrg;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cnvus.Adapter.LuckBaseAdapter;
import com.example.cnvus.R;
import com.example.cnvus.Utils.AssetsUtils;
import com.example.cnvus.bean.StarInfoBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class MeFragment extends Fragment implements View.OnClickListener {
    CircleImageView iconIv;
    TextView nameTv;
    private Map<String, Bitmap> contentLogoImgMap;
    List<StarInfoBean.StarinfoBean> infoList;
    SharedPreferences spf;
    int selectPOs=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //上一个界面值的获取
        Bundle arguments = getArguments();
        StarInfoBean starInfoBean = (StarInfoBean) arguments.getSerializable("info");
         infoList= starInfoBean.getStarinfo();
        spf= getContext().getSharedPreferences("data", Context.MODE_PRIVATE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_me, container, false);
       iconIv=view.findViewById(R.id.mefrag_iv);
       nameTv=view.findViewById(R.id.mefrag_tv_name);
        contentLogoImgMap = AssetsUtils.getContentLogoImgMap();
        String logoname = spf.getString("logoname","baiyang");
        String name = spf.getString("name","白羊座");
        Bitmap bitmap = contentLogoImgMap.get(logoname);
        iconIv.setImageBitmap(bitmap);
        nameTv.setText(name);
        iconIv.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mefrag_iv:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog=new Dialog(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.me_dialog,null,false);
        dialog.setContentView(view);
        dialog.setTitle("请选择您的星座：");
        GridView gv=view.findViewById(R.id.mefrag_dialog_gv);
        LuckBaseAdapter adapter=new LuckBaseAdapter(getContext(),infoList);
        gv.setAdapter(adapter);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);//点击外部可以取消
        //设置gv的点击事件
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarInfoBean.StarinfoBean starinfoBean = infoList.get(position);
                Bitmap bitmap = contentLogoImgMap.get(starinfoBean.getLogoname());
                iconIv.setImageBitmap(bitmap);
                nameTv.setText(starinfoBean.getName());

                selectPOs=position;//保存选择的位置
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        //表示失去焦点的时候再进行保存操作
        SharedPreferences.Editor edit = spf.edit();
        edit.putString("logoname",infoList.get(selectPOs).getLogoname());
        edit.putString("name",infoList.get(selectPOs).getName());
        edit.apply();
    }
}