package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.SendRangebean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/1.
 */
public class SendRangeAdapter extends BaseAdapter {

    List<SendRangebean> names;
    private LayoutInflater inflater;

    public SendRangeAdapter(Context context) {
        names = new ArrayList<SendRangebean>();
//        DialogFacory.getInstance().createRunDialog(context);
        this.inflater = LayoutInflater.from(context);

    }

    public void setRes(List<SendRangebean> list) {
        names = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_sendrange_item, null);
            holder = new ViewHolder();
            holder.content = (TextView) convertView.findViewById(R.id.item);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);

        }
        holder = (ViewHolder) convertView.getTag();
        holder.content.setText(names.get(position).getName());

        if (names.get(position).isShowTitle()) {
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(names.get(position).getType().getName());
        } else {
            holder.title.setVisibility(View.GONE);
        }
//        holder.content.setOnClickListener(this);
        holder.content.setTag(position);
        return convertView;
    }

    private class ViewHolder {
        TextView content;
        TextView title;
    }


}
