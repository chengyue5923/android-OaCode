package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.PraiseReplyGridItemBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2015/3/12.
 */
public class PraiseReplyGridViewAdapter extends BaseAdapter implements View.OnClickListener {
    private LayoutInflater mLayoutInflater;
    private List<PraiseReplyGridItemBean> praiseReplyGridItemBeans;
    private ViewHolder viewHolder;
    private Context context;


    public PraiseReplyGridViewAdapter(Context context, List<PraiseReplyGridItemBean> praiseReplyGridItemBeans) {
        mLayoutInflater = LayoutInflater.from(context);
        this.praiseReplyGridItemBeans = praiseReplyGridItemBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (praiseReplyGridItemBeans != null)
            return praiseReplyGridItemBeans.size();

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
            convertView = mLayoutInflater.inflate(R.layout.gridview_item_praise, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.grid_item_text);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.grid_item_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PraiseReplyGridItemBean bean = praiseReplyGridItemBeans.get(position);
        viewHolder.personName.setText(bean.personName);

        if (bean.personPicUrl != null) {
            Picasso.with(context).load(bean.personPicUrl).into(viewHolder.personPhoto);
        } else {
            Picasso.with(context).load(R.drawable.ic_launcher).into(viewHolder.personPhoto);
        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public ImageView personPhoto; //图片
    }
}
