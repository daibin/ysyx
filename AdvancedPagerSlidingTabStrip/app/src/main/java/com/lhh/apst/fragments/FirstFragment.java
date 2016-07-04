package com.lhh.apst.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.lhh.apst.adapter.AllCasesAdapter;
import com.lhh.apst.advancedpagerslidingtabstrip.DispatchPoliceActivity;
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
public class FirstFragment extends Fragment {

    private static final String TAG = "FirstFragment";
    private ListView listView;
    private AllCasesAdapter allCasesAdapter;
    private List<CaseInfoView> datas;
    private AsyncHttpService asyncHttpService;
    private App myApplication;
    private PopupWindow popupWindow;
    private View contentView;

    private LinearLayout ll_tel;
    private LinearLayout ll_dispatch;

    public static FirstFragment instance() {
        FirstFragment view = new FirstFragment();
		return view;
	}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, null);
        myApplication= (App) getActivity().getApplication();
        initViews(view);
        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                dismissPopWnd();
                contentView=View.inflate(getActivity().getApplicationContext(),R.layout.item_popwnd,null);
                ll_tel= (LinearLayout) contentView.findViewById(R.id.ll_tel);
                ll_dispatch= (LinearLayout) contentView.findViewById(R.id.ll_dispatch);
                popupWindow=new PopupWindow(contentView,-2,-2);
                CaseInfoView caseInfoView=datas.get(position);
                final String tel=caseInfoView.getBjdh();
                final String jjdbh=caseInfoView.getJjdbh();
                ll_tel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent();
                        intent.setAction("android.intent.action.CALL");
                        intent.setData(Uri.parse("tel:"+tel));
                        startActivity(intent);
                    }
                });
                ll_dispatch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismissPopWnd();
                        Intent intent=new Intent();
                        intent.putExtra("jjdbh",jjdbh);
                        intent.setClass(getActivity(),DispatchPoliceActivity.class);
                        startActivity(intent);
                    }
                });
                //popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
                int[] location=new int[2];
                view.getLocationInWindow(location);
                popupWindow.showAtLocation(parent, Gravity.LEFT|Gravity.TOP,location[0],location[1]-80);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                dismissPopWnd();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        return view;
    }

    private void dismissPopWnd() {
        if(popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow=null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissPopWnd();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        initData();
    }

    private void initData() {

        //progressBar.setVisibility(View.VISIBLE);
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
                        //caseInfoView.setBjfsbh(obj.getString("bjfsbh"));
                        caseInfoView.setCaseLevel(obj.getInt("caseLevel"));
                        caseInfoView.setBjnr(obj.getString("bjnr"));
                        caseInfoView.setBjsj(obj.getString("bjsj"));
                        caseInfoView.setMark(obj.getBoolean("mark"));
                        caseInfoView.setBjlbmc(obj.getString("bjlbmc"));
                        caseInfoView.setBjlxmc(obj.getString("bjlxmc"));
                        caseInfoView.setBjxlmc(obj.getString("bjxlmc"));
                        caseInfoView.setGxdwbh(obj.getString("gxdwbh"));
                        if(caseInfoView.getAjzt()==1) {
                            datas.add(caseInfoView);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                allCasesAdapter =new AllCasesAdapter(datas,getActivity());
                listView.setAdapter(allCasesAdapter);
                Log.i(TAG,"size:"+datas.size());
                //progressBar.setVisibility(View.INVISIBLE);
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
