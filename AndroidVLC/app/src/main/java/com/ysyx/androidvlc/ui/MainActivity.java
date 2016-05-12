package com.ysyx.androidvlc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ysyx.androidvlc.R;

/**
 * Created by DaiBin on 2016-05-10.
 */
public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VlcVideoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        button= (Button) findViewById(R.id.btn_start);
    }
}
