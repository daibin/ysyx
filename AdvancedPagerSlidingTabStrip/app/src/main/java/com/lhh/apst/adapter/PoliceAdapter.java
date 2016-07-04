package com.lhh.apst.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lhh.apst.advancedpagerslidingtabstrip.R;
import com.lhh.apst.bean.PoliceInfoBean;

import java.util.List;

/**
 * Created by John on 2016-05-16.
 */
public class PoliceAdapter extends BaseAdapter {

    private static final String TAG = "PoliceAdapter";
    private List<PoliceInfoBean> datas;
    private Context context;
    private LayoutInflater inflater;

    public PoliceAdapter() {
    }

    public PoliceAdapter(List<PoliceInfoBean> datas, Context context) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
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
            convertView =inflater.inflate(R.layout.item_dispatch_police_layout,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tv_police_name= (TextView) convertView.findViewById(R.id.tv_police_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        PoliceInfoBean policeInfoBean = datas.get(position);
        viewHolder.tv_police_name.setText(policeInfoBean.getName());
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_police_name;
    }
}
