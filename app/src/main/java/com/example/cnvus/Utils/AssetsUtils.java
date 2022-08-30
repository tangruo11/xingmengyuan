package com.example.cnvus.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cnvus.bean.StarInfoBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetsUtils {
    private static Map<String,Bitmap> contentLogoImgMap;
    private static Map<String,Bitmap> logoImgMap;
    /**
     * 读取assets当中内容，存放到字符串中
     */
    public static String getJsonFromAssets(Context context,String filename){
        StringBuilder data=new StringBuilder();
        String line="";
        //1.获取Assets文件夹管理器
        AssetManager am = context.getResources().getAssets();

        try (InputStream is = am.open(filename);
             BufferedReader br=new BufferedReader(new InputStreamReader(is));)
        {
            while ((line=br.readLine())!=null){
                data.append(line);}
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            return data.toString();

    }

    /**
     * 读取文件当中的bitmap图片
     * @param context
     * @param filename
     * @return
     */
    public static Bitmap getBitMapFromAssets(Context context,String filename){
        Bitmap bitmap=null;

        AssetManager am = context.getResources().getAssets();


        try(InputStream is = am.open(filename);)
        {
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e)
        {e.printStackTrace();}


        return bitmap;
    }
    /**
     * 将Assets文件夹当中的图片一起读取，放置内存中，便于管理
     */
    public static void saveBitmapFromAssets(Context context, StarInfoBean starInfoBean){
                     contentLogoImgMap=new HashMap<>();
                     logoImgMap=new HashMap<>();
        List<StarInfoBean.StarinfoBean> starList = starInfoBean.getStarinfo();
        for (int i = 0; i <starList.size() ; i++) {
            String logoname = starList.get(i).getLogoname();
            String filename="xzlogo/"+logoname+".png";
            Bitmap logoBm=getBitMapFromAssets(context,filename);
            logoImgMap.put(logoname,logoBm);

            String contentfilename="xzcontentlogo/"+logoname+".png";
            Bitmap contentbm=getBitMapFromAssets(context,contentfilename);
            contentLogoImgMap.put(logoname,contentbm);

        }
    }

    public static Map<String, Bitmap> getLogoImgMap() {
        return logoImgMap;
    }

    public static Map<String, Bitmap> getContentLogoImgMap() {
        return contentLogoImgMap;
    }
}
