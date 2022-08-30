package com.example.cnvus.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cnvus.R;
import com.example.cnvus.bean.LuckItemBean;

import java.util.List;

public class LuckAnalysisAdapter extends BaseAdapter {
    Context context;
    List<LuckItemBean> list;

    public LuckAnalysisAdapter(Context context, List<LuckItemBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_luckanalysis_lv,parent,false);
           viewHolder=new ViewHolder(convertView);
           convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        String title1 = list.get(position).getTitle();
        viewHolder.title.setText(title1);
             GradientDrawable drawable = (GradientDrawable) viewHolder.title.getBackground();
             drawable.setColor(list.get(position).getColor());

        viewHolder.content.setText(list.get(position).getContent());

        return convertView;
    }
    class ViewHolder{
        TextView title,content;

        public ViewHolder(View view) {
            title=view.findViewById(R.id.item_luckanalysis_tv_title);
            content=view.findViewById(R.id.item_luckanalysis_tv_content);
        }
    }
}
