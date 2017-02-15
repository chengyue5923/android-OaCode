package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.MessageSendListItemBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2015/3/6.
 */
public class MessageSendAdapter extends BaseAdapter implements View.OnClickListener {
    private LayoutInflater mLayoutInflater;
    private List<MessageSendListItemBean> messageListItemBeanLists;
    private ViewHolder viewHolder;
    private Context context;

    public MessageSendAdapter(Context context, List<MessageSendListItemBean> messageListItemBeanLists) {
        mLayoutInflater = LayoutInflater.from(context);
        this.messageListItemBeanLists = messageListItemBeanLists;
        this.context = context;
    }

    public void setData(List<MessageSendListItemBean> messageListItemBeanLists) {
        this.messageListItemBeanLists = messageListItemBeanLists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (messageListItemBeanLists != null)
            return messageListItemBeanLists.size();

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
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_message, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.personName);
            viewHolder.personContent = (TextView) convertView.findViewById(R.id.personContent);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.personTime);
            viewHolder.personReply = (TextView) convertView.findViewById(R.id.personReply);
            viewHolder.delete = (TextView) convertView.findViewById(R.id.personPraise);
            viewHolder.lookupDetails = (TextView) convertView.findViewById(R.id.lookupDetails);

            viewHolder.personReply.setOnClickListener(this);
            viewHolder.delete.setOnClickListener(this);
            viewHolder.lookupDetails.setOnClickListener(this);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.personPhoto);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MessageSendListItemBean bean = messageListItemBeanLists.get(position);
        if (bean.user_info.getAvatar_middle() != null) {
            Picasso.with(context).load(bean.user_info.getAvatar_middle()).into(viewHolder.personPhoto);
        }
        viewHolder.personName.setText(bean.user_info.getUname());
        viewHolder.delete.setText(R.string.delete);
        viewHolder.personReply.setText(R.string.person_reply);
        viewHolder.personContent.setText(bean.content);
        if (bean.user_info.getCtime() != null) {
            String dateString = DateTools.formatDateWithSecondSince1970("MM月dd日 HH:mm", Long.parseLong(bean.user_info.getCtime()) * 1000L);
            viewHolder.personTime.setText(dateString);
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personReply:
                IntentTool.applicationAlarmPage(context);
                break;
            case R.id.personPraise:
                ToastTool.show("click delete");
                break;
            case R.id.lookupDetails:
                ToastTool.show("click details");
                break;
            default:
                break;
        }
    }

    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public TextView personContent; //显示相关内容
        public TextView personTime; //显示时间
        public TextView personReply; //回复
        public TextView delete; //赞
        public TextView lookupDetails; //查看详情
        public ImageView personPhoto;
    }
}
