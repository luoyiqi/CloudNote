package com.example.administraor.cloudnote.activity.activity.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administraor on 2015/12/5.
 */
public class BitmapChange {
    /*
    * 这方法是用来将leancloud给出的URL缩略图换成bitmap从而显示在页面上
    *
    * */
    public static Bitmap getHttpBitmap(String url){
        URL fileUrl;
        Bitmap bitmap=null;
        try{
            fileUrl=new URL(url);
            HttpURLConnection conn=(HttpURLConnection)fileUrl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            //不使用缓存，就不需要将其存起来
            conn.setUseCaches(false);
            InputStream is=conn.getInputStream();
            //解析获得图片
            bitmap= BitmapFactory.decodeStream(is);
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

}
