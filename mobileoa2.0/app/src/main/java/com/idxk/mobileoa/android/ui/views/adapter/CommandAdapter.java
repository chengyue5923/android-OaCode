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
import com.idxk.mobileoa.utils.common.android.ToastTool;

import java.util.List;

/**
 * Created by lenovo on 2015/3/9.
 */
public class CommandAdapter extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater mLayoutInflater;
    private List<CommandListItemBean> commandListItemBeans;
    private ViewHolder viewHolder;
    private Context context;
    private String bigPhotoUrl;

    public CommandAdapter(Context context, List<CommandListItemBean> commandListItemBeans) {
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
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_command, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.command_personName);
            viewHolder.personContent = (TextView) convertView.findViewById(R.id.command_personContent);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.command_showTime);
            viewHolder.personReply = (TextView) convertView.findViewById(R.id.command_personReply);
            viewHolder.commandDelete = (TextView) convertView.findViewById(R.id.command_delete);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.command_personPhoto);
            viewHolder.lookupDetails = (TextView) convertView.findViewById(R.id.command_lookupDetails);

            viewHolder.commandDelete.setOnClickListener(this);
            viewHolder.personReply.setOnClickListener(this);
            viewHolder.lookupDetails.setOnClickListener(this);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.command_lookupDetails:
                ToastTool.show("details click");
                break;
            case R.id.command_delete:
                ToastTool.show("delete click");
                break;
            case R.id.command_personReply:
                ToastTool.show("personReply click");
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
        public TextView commandDelete; //赞
        public ImageView personPhoto;
        public TextView lookupDetails; //查看详情
    }
}
