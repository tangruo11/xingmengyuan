package com.example.cnvus.starFrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cnvus.R;

import java.util.List;

public class AnalysisBaseAdapter extends BaseAdapter {
    Context context;
    List<StarAnalysisBean> list;

    public AnalysisBaseAdapter(Context context, List<StarAnalysisBean> list) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_staranalysis_lv,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        StarAnalysisBean starAnalysisBean = list.get(position);
        viewHolder.title.setText(starAnalysisBean.getTitle());
        viewHolder.content.setText(starAnalysisBean.getContent());
        viewHolder.content.setBackgroundResource(starAnalysisBean.getColor());
        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView content;

        public ViewHolder(View view) {
            title=view.findViewById(R.id.item_staranalysis_tv_title);
            content=view.findViewById(R.id.item_staranalysis_tv_content);
        }
    }
}
