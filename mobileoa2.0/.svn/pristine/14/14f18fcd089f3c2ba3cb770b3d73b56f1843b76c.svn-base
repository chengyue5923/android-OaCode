package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.CommandListItemBean;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.utils.common.android.ToastTool;

import java.util.List;

/**
 * Created by lenovo on 2015/3/9.
 */
public class MobileAdapter extends BaseAdapter  {

    private LayoutInflater mLayoutInflater;
    private List<ContactBean.MoileNum> commandListItemBeans;
    private ViewHolder viewHolder;
    private Context context;

    public MobileAdapter(Context context, List<ContactBean.MoileNum> commandListItemBeans) {
        mLayoutInflater = LayoutInflater.from(context);
        this.commandListItemBeans = commandListItemBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (commandListItemBeans != null)
            return commandListItemBeans.size();

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.view_mobile_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.key = (TextView) convertView.findViewById(R.id.key);
            viewHolder.value = (TextView) convertView.findViewById(R.id.value);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.key.setText(commandListItemBeans.get(position).getTyle().getName()+":");
        viewHolder.value.setText(commandListItemBeans.get(position).getNum());
        return convertView;
    }


    public static final class ViewHolder {
        public TextView key; //显示名字相关信息
        public TextView value; //显示相关内容

    }
}
