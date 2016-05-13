package com.ysyx.androidvlc.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ysyx.androidvlc.CameraInfo;
import com.ysyx.androidvlc.CameraInfoDao;
import com.ysyx.androidvlc.DaoSession;
import com.ysyx.androidvlc.R;
import com.ysyx.androidvlc.VLCApplication;
import com.ysyx.androidvlc.adapter.MyAdapter;
import com.ysyx.androidvlc.utils.MyAsyncTask;

import java.util.List;

public class MainActivity extends Activity {
		

	 	private Button button;
	    private ListView listView;
	    private List<CameraInfo> datas;
	    private MyAdapter myAdapter;
		private DaoSession daoSession;
		private VLCApplication application;
		private CameraInfoDao cameraInfoDao;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        //String url = "http://192.168.1.114/baemysql/userServlet";
            //new MyAsyncTask(application).execute(url);
			application= (VLCApplication) getApplication();
			application.setupDatabase();
			cameraInfoDao=application.getCameraInfoDao();
	        initData();
	        initView();
	        button.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	//DataSupport.deleteAll(CameraInfo.class);
	                String url = "http://192.168.1.114/baemysql/userServlet";
	                new MyAsyncTask(application).execute(url);
	                initData();
					//Intent intent=new Intent(MainActivity.this, VlcVideoActivity.class);
					//intent.putExtra("STD_ID", "42088100001310400784");
					//intent.setClass(MainActivity.this, VlcVideoActivity.class);
					//startActivity(intent);
	            }
	        });
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					CameraInfo cameraInfo = datas.get(position);
					String name=cameraInfo.getNAME();
					String std_id=cameraInfo.getSTD_ID();
					Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
	                Intent intent=new Intent(MainActivity.this, VlcVideoActivity.class);
	                intent.putExtra("STD_ID", std_id);
	                //intent.setClass(MainActivity.this, VlcVideoActivity.class);
	                startActivity(intent);
				}
			});
	    }

	    private void initData() {
			datas=cameraInfoDao.loadAll();
	        //datas = DataSupport.findAll(CameraInfo.class);
	        //ItemBean itemBean=new ItemBean(R.mipmap.ic_launcher,"����ͷ����");
	        //datas.add(itemBean);
	        myAdapter=new MyAdapter(this,datas);

	    }

	    private void initView() {
	        button= (Button) findViewById(R.id.btn_update);
	        listView= (ListView) findViewById(R.id.devices_list);
	        listView.setAdapter(myAdapter);

	    }
}
