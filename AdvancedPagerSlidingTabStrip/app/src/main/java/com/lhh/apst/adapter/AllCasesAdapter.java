package com.lhh.apst.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lhh.apst.advancedpagerslidingtabstrip.R;
import com.lhh.apst.bean.CaseInfoView;

import java.util.List;

/**
 * Created by John on 2016-05-16.
 */
public class AllCasesAdapter extends BaseAdapter {

    private static final String TAG = "AllCasesAdapter";
    private List<CaseInfoView> datas;
    private Context context;
    private LayoutInflater inflater;

    public AllCasesAdapter() {
    }

    public AllCasesAdapter(List<CaseInfoView> datas, Context context) {
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
            viewHolder.tv_bjlbmc= (TextView) convertView.findViewById(R.id.tv_bjlbmc);
            viewHolder.tv_ajzt_desc= (TextView) convertView.findViewById(R.id.tv_ajzt_desc);
            viewHolder.ll_item= (LinearLayout) convertView.findViewById(R.id.ll_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        CaseInfoView caseInfoView = datas.get(position);
        viewHolder.tv_bjsj.setText(caseInfoView.getBjsj());
        viewHolder.tv_bjdh.setText(caseInfoView.getBjdh());
        viewHolder.tv_bjnr.setText(caseInfoView.getBjnr());
        viewHolder.tv_sfdz.setText(caseInfoView.getSfdz());
        viewHolder.tv_bjlbmc.setText(caseInfoView.getBjlbmc());
        switch (caseInfoView.getAjzt()){
            case 1:
                viewHolder.ll_item.setBackgroundColor(Color.parseColor("#ff0000"));
                viewHolder.tv_ajzt_desc.setText("出警");
                break;
            case 2:
                viewHolder.ll_item.setBackgroundColor(Color.parseColor("#ffff00"));
                viewHolder.tv_ajzt_desc.setText("回告");
                break;
            case 3:
                viewHolder.ll_item.setBackgroundColor(Color.parseColor("#0000ff"));
                viewHolder.tv_ajzt_desc.setText("处警");
                break;
            case 4:
                viewHolder.ll_item.setBackgroundColor(Color.parseColor("#55cc88"));
                viewHolder.tv_ajzt_desc.setText("警员");
                break;
            default:
                break;
        }

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_bjsj;
        private TextView tv_bjdh;
        private TextView tv_bjnr;
        private TextView tv_sfdz;
        private TextView tv_bjlbmc;
        private TextView tv_ajzt_desc;
        private LinearLayout ll_item;

    }
}
