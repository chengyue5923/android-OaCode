package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.utils.common.android.FileIntent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
public class WeiboFilesAdapter extends BaseAdapter {

    List<AttachBean> listBeas;
    Context context;
    private LayoutInflater mLayoutInflater;
    private ViewHolder viewHolder;

    public WeiboFilesAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        listBeas = new ArrayList<>();
    }

    public void setRes(List<AttachBean> listBeas) {
        this.listBeas = listBeas;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return listBeas.size();
    }

    @Override
    public AttachBean getItem(int position) {
        return listBeas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_weibo_file_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.size = (TextView) convertView.findViewById(R.id.size);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.content.setText(getItem(position).getAttach_name());
        viewHolder.size.setText(getShowSize(getItem(position).getSize()));
        viewHolder.content.setTag(position);
        FileIntent.setOfficalImageByExcete(viewHolder.icon ,getItem(position).getExtension());
        return convertView;
    }

    public String getShowSize(String s){
        try {
            long size=Long.parseLong(s);
            return convertFileSize(size);
        }catch (Exception e){
            return "";
        }


    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }


    public static final class ViewHolder {
        public TextView content; //显示名字相关信息
        public TextView size; //显示名字相关信息
        public ImageView icon;
    }


}
