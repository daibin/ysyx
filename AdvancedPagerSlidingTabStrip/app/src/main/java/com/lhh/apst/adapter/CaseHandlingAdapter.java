package com.lhh.apst.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.lhh.apst.advancedpagerslidingtabstrip.R;
import com.lhh.apst.bean.CaseInfoView;

import java.util.List;


/**
 * Created by John on 2016-05-16.
 */
public class CaseHandlingAdapter extends BaseAdapter {

    private static final String TAG = "CaseHandlingAdapter";
    private List<CaseInfoView> datas;
    private Context context;
    private LayoutInflater inflater;

    public CaseHandlingAdapter() {
    }

    public CaseHandlingAdapter(List<CaseInfoView> datas, Context context) {
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
            convertView =inflater.inflate(R.layout.item_treat_layout,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tv_bjsj= (TextView) convertView.findViewById(R.id.tv_bjsj);
            viewHolder.tv_bjdh= (TextView) convertView.findViewById(R.id.tv_bjdh);
            viewHolder.tv_bjnr= (TextView) convertView.findViewById(R.id.tv_bjnr);
            viewHolder.tv_sfdz= (TextView) convertView.findViewById(R.id.tv_sfdz);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        CaseInfoView caseInfoView = datas.get(position);
        viewHolder.tv_bjsj.setText(caseInfoView.getBjsj());
        viewHolder.tv_bjdh.setText(caseInfoView.getBjdh());
        viewHolder.tv_bjnr.setText(caseInfoView.getBjnr());
        viewHolder.tv_sfdz.setText(caseInfoView.getSfdz());

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_bjsj;
        private TextView tv_bjdh;
        private TextView tv_bjnr;
        private TextView tv_sfdz;

    }
}
