package com.lhh.apst.advancedpagerslidingtabstrip;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lhh.apst.adapter.AllCasesAdapter;
import com.lhh.apst.adapter.PoliceAdapter;
import com.lhh.apst.application.App;
import com.lhh.apst.bean.CaseInfoView;
import com.lhh.apst.bean.PoliceInfoBean;
import com.lhh.apst.fragments.FirstFragment;
import com.lhh.apst.fragments.FourthFragment;
import com.lhh.apst.fragments.SecondFragment;
import com.lhh.apst.fragments.ThirdFragment;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.lhh.apst.util.AsyncHttpService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import cz.msebera.android.httpclient.Header;
import lib.lhh.fiv.library.FrescoImageView;

/**
 * Created by Linhh on 16/2/16.
 */
public class DispatchPoliceActivity extends AppCompatActivity{

    private static final String TAG = "DispatchPoliceActivity";
    private AsyncHttpService asyncHttpService;
    private App myApplication;
    private SharedPreferences preferences;
    private List<PoliceInfoBean> datas;
    private PoliceAdapter policeAdapter;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_police);
        myApplication= (App) getApplication();
        asyncHttpService=myApplication.getAsyncHttpService();
        preferences=getSharedPreferences("CookiePrefsFile",MODE_PRIVATE);
        initViews();
        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=getIntent();
                String jjdbh=intent.getStringExtra("jjdbh");
                RequestParams params = new RequestParams();
                String pjPoliceId=datas.get(position).getCode();
                params.put("jjdbh",jjdbh);
                params.put("pjPoliceId", pjPoliceId);
                asyncHttpService.get("alarm/dispatchPolice.do", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.i("TAG",response.toString());

                        try {
                            Toast.makeText(DispatchPoliceActivity.this,response.getString("description"),Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
            }
        });
    }


    private void initData() {
        datas=new ArrayList<PoliceInfoBean>();
        RequestParams params = new RequestParams();
        int rootId=preferences.getInt("orgId",72);
        params.put("rootId", rootId+"");

        asyncHttpService.get("organWeb/listWithPoliceForApp.do",params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("TAG",response.toString());
                JSONArray arr = null;
                try {
                    arr = response.getJSONArray("data");
                    for (int i = 0; i < arr.length()-1; i++) {
                        PoliceInfoBean policeInfoBean=new PoliceInfoBean();
                        JSONObject obj = (JSONObject) arr.get(i);
                        policeInfoBean.setId(obj.getString("id"));
                        policeInfoBean.setRid(obj.getInt("rid"));
                        policeInfoBean.setName(obj.getString("name"));
                        policeInfoBean.setDataType(obj.getInt("dataType"));
                        policeInfoBean.setCode(obj.getString("code"));
                        policeInfoBean.setReportsTo(obj.getInt("ReportsTo"));
                        policeInfoBean.setOrgName(obj.getString("orgName"));
                        policeInfoBean.setState(obj.getString("state"));
                        policeInfoBean.setText(obj.getString("text"));
                        policeInfoBean.setOrg(obj.getBoolean("isOrg"));
                        datas.add(policeInfoBean);
                    }
                   policeAdapter=new PoliceAdapter(datas,DispatchPoliceActivity.this);
                    listView.setAdapter(policeAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG,"ERROR<-----------------------------------"+throwable.toString());
            }
        });

    }

    private void initViews() {
        listView= (ListView) findViewById(R.id.lv_dispatch_police);
    }
}

