package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.RecentModel;

import java.util.List;

/**
 * Created by lenovo on 2015/4/3.
 */
public class RecentAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<RecentModel> recentModels;

    public RecentAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setData(List<RecentModel> recentModels) {
        this.recentModels = recentModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recentModels.size();
    }

    @Override
    public Object getItem(int i) {
        return recentModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        RecentModel recentModel = recentModels.get(position);
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.adapter_recent_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.recentName);
            holder.notChecked = (ImageView) view.findViewById(R.id.notChecked);
            holder.isChecked = (ImageView) view.findViewById(R.id.isChecked);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (recentModel.isChecked()) {
            holder.notChecked.setVisibility(View.GONE);
            holder.isChecked.setVisibility(View.VISIBLE);
        } else {
            holder.notChecked.setVisibility(View.VISIBLE);
            holder.isChecked.setVisibility(View.GONE);
        }

        holder.name.setText(recentModel.getName());
        return view;
    }

    private static final class ViewHolder {
        private ImageView isChecked;
        private ImageView notChecked;
        private TextView name;
    }
}
