package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.MusicInfo;

import java.util.List;

/**
 * Created by lenovo on 2015/6/9.
 */
public class MusicAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<MusicInfo> musicInfoList;
    private ViewHolder viewHolder;

    public MusicAdapter(List<MusicInfo> musicInfoList, Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        this.musicInfoList = musicInfoList;
    }

    public void setData(List<MusicInfo> musicInfoList) {
        this.musicInfoList = musicInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (musicInfoList.size() > 0) {
            return musicInfoList.size();
        }
        return 0;
    }

    @Override
    public MusicInfo getItem(int position) {
        return musicInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MusicInfo musicInfo = musicInfoList.get(position);
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

        viewHolder.name.setText(musicInfo.musicName);
        viewHolder.size.setText(String.valueOf(musicInfo.size) + "B");
        return convertView;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView size;
        public ImageView icon;
    }
}
