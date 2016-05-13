package com.ysyx.androidvlc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysyx.androidvlc.CameraInfo;
import com.ysyx.androidvlc.R;

import java.util.List;

/**
 * Created by John on 2016-05-06.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<CameraInfo> datas;

    public MyAdapter(Context context, List<CameraInfo> datas) {
        layoutInflater=LayoutInflater.from(context);
        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_device,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.id_camera_pic);
            viewHolder.cameraName= (TextView) convertView.findViewById(R.id.id_camera_name);
            viewHolder.cameraStdID= (TextView) convertView.findViewById(R.id.id_camera_std_id);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        CameraInfo cameraInfo=datas.get(position);
        Log.i("TAG",cameraInfo.getId()+cameraInfo.getNAME());
        //viewHolder.imageView.setImageResource(cameraInfoBean.getCameraPicUrl());
        viewHolder.cameraName.setText(cameraInfo.getNAME());
        viewHolder.cameraStdID.setText(cameraInfo.getSTD_ID());
        return convertView;
    }

    private class ViewHolder{
        private ImageView imageView;
        private TextView cameraName;
        private TextView cameraStdID;
    }
}
