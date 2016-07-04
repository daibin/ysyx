package com.lhh.apst.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lhh.apst.adapter.AllCasesAdapter;
import com.lhh.apst.advancedpagerslidingtabstrip.R;
import com.lhh.apst.application.App;
import com.lhh.apst.bean.CaseInfoView;
import com.lhh.apst.util.AsyncHttpService;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by linhonghong on 2015/8/11.
 */
public class ThirdFragment  extends Fragment {

    public static ThirdFragment instance() {
        ThirdFragment view = new ThirdFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, null);
        myApplication= (App) getActivity().getApplication();
        initViews(view);
        initData();
        return view;
    }

    private static final String TAG = "ThirdFragment";
    private ListView listView;
    private AllCasesAdapter allCasesAdapter;
    private List<CaseInfoView> datas;
    private AsyncHttpService asyncHttpService;
    private App myApplication;

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        datas=new ArrayList<CaseInfoView>();
        asyncHttpService=myApplication.getAsyncHttpService();
        asyncHttpService.get("alarm/getCaseInfo.do", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray arr = null;
                try {
                    arr = response.getJSONArray("data");
                    for (int i = 0; i < arr.length()-1; i++) {
                        CaseInfoView caseInfoView=new CaseInfoView();
                        JSONObject obj = (JSONObject) arr.get(i);
                        caseInfoView.setJjdbh(obj.getString("jjdbh"));
                        caseInfoView.setAjzt(obj.getInt("ajzt"));
                        caseInfoView.setBjdh(obj.getString("bjdh"));
                        caseInfoView.setSfdz(obj.getString("sfdz"));
                        caseInfoView.setCaseLevel(obj.getInt("caseLevel"));
                        caseInfoView.setBjnr(obj.getString("bjnr"));
                        caseInfoView.setBjsj(obj.getString("bjsj"));
                        caseInfoView.setMark(obj.getBoolean("mark"));
                        caseInfoView.setBjlbmc(obj.getString("bjlbmc"));
                        caseInfoView.setBjlxmc(obj.getString("bjlxmc"));
                        caseInfoView.setBjxlmc(obj.getString("bjxlmc"));
                        caseInfoView.setGxdwbh(obj.getString("gxdwbh"));
                        if(caseInfoView.getAjzt()==3) {
                            datas.add(caseInfoView);
                            Log.d(TAG, "onSuccess: datas"+datas.size());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                allCasesAdapter =new AllCasesAdapter(datas,getActivity());
                listView.setAdapter(allCasesAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG,"ERROR<-----------------------------------");
            }
        });
    }

    private void initViews(View view) {
        listView= (ListView) view.findViewById(R.id.lv_all_cases);
    }
}