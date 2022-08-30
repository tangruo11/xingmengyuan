package com.example.cnvus.luckFrg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.cnvus.Adapter.LuckBaseAdapter;
import com.example.cnvus.R;
import com.example.cnvus.bean.StarInfoBean;

import java.util.List;


public class LuckFragment extends Fragment {
    List <StarInfoBean.StarinfoBean> mdatas;
    /**
     * 运势界面
     */
    GridView luckgv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view= inflater.inflate(R.layout.fragment_luck, container, false);
      luckgv=view.findViewById(R.id.luckfrag_gv);
      //获取数据源
        Bundle bundle = getArguments();
        StarInfoBean  starInfoBean= (StarInfoBean) bundle.getSerializable("info");
        mdatas= starInfoBean.getStarinfo();
        LuckBaseAdapter adapter=new LuckBaseAdapter(getContext(),mdatas);
        luckgv.setAdapter(adapter);
        //设置GV每一项的监听事件
        luckgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = mdatas.get(position).getName();
                Intent intent=new Intent(getContext(),LuckAnalysisActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });
        return view;
    }

}