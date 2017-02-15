package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.PraiseReceivedListItemBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2015/3/8.
 */
public class PraiseReceivedAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<PraiseReceivedListItemBean> praiseReceivedListItemBeans;
    private ViewHolder viewHolder;
    private Context context;


    public PraiseReceivedAdapter(Context context, List<PraiseReceivedListItemBean> praiseReceivedListItemBeans) {
        mLayoutInflater = LayoutInflater.from(context);
        this.praiseReceivedListItemBeans = praiseReceivedListItemBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (praiseReceivedListItemBeans != null)
            return praiseReceivedListItemBeans.size();

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
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_praisereceived, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.praiseReceivedPersonName);
            viewHolder.personPic = (ImageView) convertView.findViewById(R.id.praiseReceivedPersonPic);
            viewHolder.shareContent = (TextView) convertView.findViewById(R.id.praiseReceivedShareContent);
            viewHolder.showTime = (TextView) convertView.findViewById(R.id.praiseReceivedShowTime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PraiseReceivedListItemBean bean = praiseReceivedListItemBeans.get(position);
        viewHolder.personName.setText(bean.personName);


        if (bean.personPicUrl != null) {
            Picasso.with(context).load(bean.personPicUrl).into(viewHolder.personPic);
        } else {
            Picasso.with(context).load(R.drawable.ic_launcher).into(viewHolder.personPic);
        }
        viewHolder.shareContent.setText(bean.shareContent);
        viewHolder.showTime.setText(bean.showTime);
        return convertView;
    }


    public static final class ViewHolder {
        public TextView personName;
        public ImageView personPic;
        public TextView showTime;
        public TextView shareContent;
    }
}
