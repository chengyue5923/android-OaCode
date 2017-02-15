package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.AppListBea;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
public class AppAdapter extends BaseAdapter {

    List<AppListBea> listBeas;
    Context context;
    private LayoutInflater mLayoutInflater;
    private ViewHolder viewHolder;

    public AppAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        listBeas = new ArrayList<>();
    }

    public void setRes(List<AppListBea> listBeas) {
        this.listBeas = listBeas;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return listBeas.size();
    }

    @Override
    public AppListBea getItem(int position) {
        return listBeas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_app_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(listBeas.get(position).getName());
        Logger.e("url==" + listBeas.get(position).getIcon());
        Picasso.with(context).load(listBeas.get(position).getIcon()).into(viewHolder.iv);
        return convertView;
    }


    public static final class ViewHolder {
        public TextView name; //显示名字相关信息
        public ImageView iv;

    }
}
