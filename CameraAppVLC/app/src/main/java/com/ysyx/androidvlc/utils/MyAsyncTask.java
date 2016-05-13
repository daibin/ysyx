package com.ysyx.androidvlc.utils;


import android.os.AsyncTask;
import android.util.Log;

import com.ysyx.androidvlc.CameraInfo;
import com.ysyx.androidvlc.VLCApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2016-05-05.
 */

public class MyAsyncTask extends AsyncTask<String,Void,Void>{
    private VLCApplication application;

    public MyAsyncTask(VLCApplication application) {
        this.application = application;
    }

    @Override
    protected Void doInBackground(String... params) {
        //DataSupport.deleteAll(CameraInfo.class);
        String url=params[0];
        List<CameraInfo> cameras=new ArrayList<CameraInfo>();
        try {
            HttpURLConnection connection= (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
           // Log.i("TAG",connection.getResponseCode()+"");
            if(200==connection.getResponseCode()){
                System.out.println("success!");
                InputStream is = connection.getInputStream();
                String result = StreamTools.readFromStream(is);
                //Log.i("TAG",result);
                JSONArray arr = new JSONObject(result).getJSONArray("DevicesList");
                for (int i = 0; i < arr.length()-1; i++) {
                    CameraInfo cameraInfo=new CameraInfo();
                    JSONObject obj = (JSONObject) arr.get(i);
                    //Log.i("TAG",cameraInfo.toString());
                    //cameraInfo.setNAME(obj.getString("NAME"));
                    cameraInfo.setNAME(URLDecoder.decode(obj.getString("NAME"), "UTF-8"));
                    cameraInfo.setSTD_ID(obj.getString("STD_ID"));
                    cameraInfo.setGPS_X(obj.getString("GPS_X"));
                    cameraInfo.setGPS_Y(obj.getString("GPS_Y"));
                    cameraInfo.setGPS_Z(obj.getString("GPS_Z"));
                    application.getCameraInfoDao().insert(cameraInfo);
                    //cameraInfo.save();
                    //cameras.add(cameraInfo);
                    Log.i("TAG", "NAME:" + cameraInfo.getNAME()+"STD_ID:"+obj.getString("STD_ID"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

