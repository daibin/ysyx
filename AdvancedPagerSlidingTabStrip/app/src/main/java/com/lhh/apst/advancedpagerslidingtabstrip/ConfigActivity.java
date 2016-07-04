package com.lhh.apst.advancedpagerslidingtabstrip;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by John on 2016-06-14.
 */
public class ConfigActivity extends AppCompatActivity{
    private static final String TAG = "ConfigActivity";
    private EditText etServerIP;
    private EditText etServerPort;
    private SharedPreferences preferences;
    private Editor editor;
    private String serverIP;
    private String serverPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        preferences=getSharedPreferences("CookiePrefsFile",MODE_PRIVATE);
        editor=preferences.edit();

        initView();
        initDatas();
    }

    private void initDatas() {
        serverIP=preferences.getString("serverIP","192.168.1.100");
        serverPort=preferences.getString("serverPort","8080");
        etServerIP.setText(serverIP);
        etServerPort.setText(serverPort);
    }

    private void initView() {
        etServerIP= (EditText) findViewById(R.id.et_server_ip);
        etServerPort= (EditText) findViewById(R.id.et_server_port);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        serverIP=etServerIP.getText().toString();
        serverPort=etServerPort.getText().toString();
        Log.i(TAG,serverIP+":"+serverPort);
        editor.putString("serverIP",serverIP);
        editor.putString("serverPort",serverPort);
        editor.apply();
        /*Intent intent= new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);*/
        startActivity(new Intent(ConfigActivity.this,LoginActivity.class));
        finish();
        //System.exit(0);
    }
}
