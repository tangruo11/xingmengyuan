package com.example.cnvus.Utils;

public class URLContent {
    //封装星座配对接口地址
    public static String getPartnerURL(String man,String woman){
        man=man.replace("座","");
        woman=woman.replace("座","");
        String url="https://apis.juhe.cn/xzpd/query?men="+man+"&women="+woman+"&key=aab7f23b9a6149ef03e1b8136e38b640";
        return url;
    }
    //星座运势接口
    public  static String getLuckURL(String consName){
    String url="http://web.juhe.cn:8080/constellation/getAll?consName="+consName+"&type=today&key=37654c72ae968cedc1f7f173181eb019";
    return url;
    }


}
