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

public class StarBaseAdapter extends BaseAdapter {
    Context context;
    List<StarInfoBean.StarinfoBean> mdatas;
    Map<String, Bitmap> logoMap;

    public StarBaseAdapter(Context context, List<StarInfoBean.StarinfoBean> mdatas) {
        this.context = context;
        this.mdatas = mdatas;
        logoMap= AssetsUtils.getLogoImgMap();
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_star_gv,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        StarInfoBean.StarinfoBean starinfoBean = mdatas.get(position);
        String logoname = starinfoBean.getLogoname();
        Bitmap bitmap=logoMap.get(logoname);
        viewHolder.circleImageView.setImageBitmap(bitmap);
        viewHolder.tv.setText(starinfoBean.getName());

        return convertView;
    }
    class ViewHolder{
        CircleImageView circleImageView;
        TextView tv;

        public ViewHolder(View view) {
            circleImageView=view.findViewById(R.id.item_star_iv);
            tv=view.findViewById(R.id.item_star_tv);
        }
    }
}
