package com.example.cnvus.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cnvus.R;
import com.example.cnvus.Utils.AssetsUtils;
import com.example.cnvus.bean.StarInfoBean;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LuckBaseAdapter extends BaseAdapter {
    Context context;
    List<StarInfoBean.StarinfoBean> list;
    private Map<String, Bitmap> contentLogoImgMap;

    public LuckBaseAdapter(Context context, List<StarInfoBean.StarinfoBean> list) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_luckfrag_gv,parent,false);
             viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        String logoname = list.get(position).getLogoname();
        contentLogoImgMap = AssetsUtils.getContentLogoImgMap();
        String name = list.get(position).getName();
        viewHolder.textView.setText(name);
        viewHolder.circleImageView.setImageBitmap(contentLogoImgMap.get(logoname));
        return convertView;
    }
    class ViewHolder{
        CircleImageView circleImageView;
        TextView textView;

        public ViewHolder(View view) {
            circleImageView=view.findViewById(R.id.item_luck_iv);
            textView=view.findViewById(R.id.item_luck_tv);

        }
    }

}
