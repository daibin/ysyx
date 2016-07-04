package com.lhh.apst.advancedpagerslidingtabstrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.lhh.apst.adapter.MyAdapter;
import com.lhh.apst.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static boolean isExit = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private GridView gridView;
    private List<ItemBean> datas;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences=getSharedPreferences("CookiePrefsFile",MODE_PRIVATE);
        editor=preferences.edit();
        initView();
        initDatas();

        gridView.setAdapter(myAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Log.d(TAG, "onItemClick: CASE 0");
                        startActivity(new Intent(MainActivity.this,ViewTabActivity.class));
                        break;
                    case 1:
                        Log.d(TAG, "onItemClick: CASE 1");
                        startActivity(new Intent(MainActivity.this,MapActivity.class));
                        break;
                    case 2:
                        Log.d(TAG, "onItemClick: CASE 2");
                        /*startActivity(new Intent(MainActivity.this,MsgPhotoUpActivity.class));*/
                        break;
                    case 3:
                        Log.d(TAG, "onItemClick: CASE 3");
                        /*startActivity(new Intent(MainActivity.this,MsgPhotoUpActivity.class));*/
                        break;
                    case 4:
                        Log.d(TAG, "onItemClick: CASE 4");
                        //startActivity(new Intent(MainActivity.this,MapActivity.class));
                        break;
                    case 5:
                        Log.d(TAG, "onItemClick: CASE 5");
                        //startActivity(new Intent(MainActivity.this,NotificationHistoryActivity.class));
                        break;
                    case 6:
                        Log.d(TAG, "onItemClick: CASE 6");
                        //startActivity(new Intent(MainActivity.this,NotificationHistoryActivity.class));
                        break;
                    case 7:
                        Log.d(TAG, "onItemClick: CASE 7");
                        //startActivity(new Intent(MainActivity.this,NotificationHistoryActivity.class));
                        break;
                    default:
                        break;

                }
            }
        });

    }

    private void initDatas() {
        datas =new ArrayList<ItemBean>();
        ItemBean itemBean=new ItemBean(R.drawable.u11,"接处警");
        datas.add(itemBean);
        itemBean=new ItemBean(R.drawable.u15,"地图");
        datas.add(itemBean);
        itemBean=new ItemBean(R.drawable.u19,"视频监控");
        datas.add(itemBean);
        itemBean=new ItemBean(R.drawable.u39,"查询查证");
        datas.add(itemBean);
        itemBean=new ItemBean(R.drawable.u31,"通知通告");
        datas.add(itemBean);
        itemBean=new ItemBean(R.drawable.u35,"法律法规");
        datas.add(itemBean);
        itemBean=new ItemBean(R.drawable.u23,"即时通讯");
        datas.add(itemBean);
        itemBean=new ItemBean(R.drawable.u45,"手机图传");
        datas.add(itemBean);
        myAdapter=new MyAdapter(this,datas);
    }

    private void initView() {
        gridView= (GridView) findViewById(R.id.gv_main_menu);
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            //return false;
        }
       // return super.onKeyDown(keyCode, event);
        return false;
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            editor.putBoolean("LoginStatus",false);
            editor.apply();
            // new UserLogout(preferences).start();
            Intent intent= new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
            System.exit(0);
        }
    }

/*    private Button mNormalTab;
    private Button mIconTab;
    private Button mCustomTab;
    private Button mViewTab;
    private Button mWeiboTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setButton();
    }

    private void findViews(){
        mIconTab = (Button)findViewById(R.id.icontab);
        mNormalTab = (Button)findViewById(R.id.noramltab);
        mCustomTab = (Button)findViewById(R.id.customtab);
        mViewTab = (Button)findViewById(R.id.viewtab);
        mWeiboTab = (Button)findViewById(R.id.weibotab);
    }

    private void setButton(){
        mIconTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,IconTabActivity.class));
            }
        });
        mNormalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,NormalTabActivity.class));
            }
        });
        mCustomTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,CustomTabActivity.class));
            }
        });
        mViewTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,ViewTabActivity.class));

            }
        });
        mWeiboTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,WeiboTabActivity.class));
            }
        });
    }*/

}
