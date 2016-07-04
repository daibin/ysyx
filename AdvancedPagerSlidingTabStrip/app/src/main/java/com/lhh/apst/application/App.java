package com.lhh.apst.application;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.lhh.apst.util.AsyncHttpService;
import com.squareup.okhttp.OkHttpClient;


/**
 * Created by Linhh on 16/2/16.
 */
public class App extends Application{


    private static final String TAG = "App";
    private AsyncHttpService asyncHttpService;

    public AsyncHttpService getAsyncHttpService() {
        return asyncHttpService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig frescoConfig = OkHttpImagePipelineConfigFactory
                .newBuilder(this, new OkHttpClient())
                .build();
        Fresco.initialize(this,frescoConfig);

        //asyncHttpService=new AsyncHttpService(getApplicationContext())
        asyncHttpService=AsyncHttpService.getAsyncHttpServiceInstance(getApplicationContext());
        Log.i(TAG,"asyncHttpService 初始化");
       /* RequestParams requestParams = new RequestParams();
        requestParams.put("policeNumber", "006966");
        requestParams.put("password", "006966");
        asyncHttpService = new AsyncHttpService(getApplicationContext());
        asyncHttpService.saveCookie();
        asyncHttpService.get("appController/login.do", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i(TAG,new String(responseBody));
                asyncHttpService.getCookies();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });*/
    }
}
