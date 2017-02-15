package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.FileInfo;

import java.util.List;

/**
 * Created by lenovo on 2015/6/9.
 */
public class PhoneMemoryAdapter extends BaseAdapter {
    private List<FileInfo> fileInfoList;
    private LayoutInflater mLayoutInflater;
    private ViewHolder viewHolder;

    public PhoneMemoryAdapter(List<FileInfo> fileInfoList, Context context) {
        this.fileInfoList = fileInfoList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (null != fileInfoList) {
            return fileInfoList.size();
        }
        return 0;
    }

    @Override
    public FileInfo getItem(int position) {
        return fileInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FileInfo musicInfo = fileInfoList.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_music_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.size = (TextView) convertView.findViewById(R.id.size);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(musicInfo.fileName);
        viewHolder.size.setText(musicInfo.filePath);
        return convertView;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView size;
        public ImageView icon;
    }
}
