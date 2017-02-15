package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.MessageReceivedListItemBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/6.
 */
public class MessageReceivedAdapter extends BaseAdapter implements View.OnClickListener, ViewNetCallBack {
    private LayoutInflater mLayoutInflater;
    private List<MessageReceivedListItemBean> messageListItemBeanLists;
    private ViewHolder viewHolder;
    private Context context;
    private int type;//type=0 收到的回复，type=1发出的回复

    public MessageReceivedAdapter(Context context, List<MessageReceivedListItemBean> messageListItemBeanLists, int type) {
        mLayoutInflater = LayoutInflater.from(context);
        this.messageListItemBeanLists = messageListItemBeanLists;
        this.context = context;
        this.type = type;
    }

    public void setData(List<MessageReceivedListItemBean> messageReceivedLists) {
        this.messageListItemBeanLists = messageReceivedLists;
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
        MessageReceivedListItemBean bean = messageListItemBeanLists.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_message, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.personName);
            viewHolder.personContent = (TextView) convertView.findViewById(R.id.personContent);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.personTime);
            viewHolder.personReply = (TextView) convertView.findViewById(R.id.personReply);
            viewHolder.personParise = (TextView) convertView.findViewById(R.id.personPraise);
            viewHolder.lookupDetails = (TextView) convertView.findViewById(R.id.lookupDetails);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.personPhoto);

            viewHolder.messageReceivedReply = (LinearLayout) convertView.findViewById(R.id.messageReceivedReply);
            viewHolder.messageReceivedReply.setOnClickListener(this);


            viewHolder.messageReceivedPraise = (LinearLayout) convertView.findViewById(R.id.messageReceivedPraise);
            viewHolder.messageReceivedPraise.setOnClickListener(this);

            viewHolder.messageDeleteLayout = (LinearLayout) convertView.findViewById(R.id.messageDeleteLayout);
            viewHolder.messageDeleteLayout.setOnClickListener(this);


            viewHolder.zanIcon = (ImageView) convertView.findViewById(R.id.messageReceivedPraiseZan);

            viewHolder.messageLookupDetailsLayout = (LinearLayout) convertView.findViewById(R.id.messageLookupDetailsLayout);
            viewHolder.messageLookupDetailsLayout.setOnClickListener(this);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (type == IConstant.TYPERECEIVEMESSAGE) {
            viewHolder.messageReceivedPraise.setVisibility(View.VISIBLE);
            viewHolder.messageDeleteLayout.setVisibility(View.GONE);
        } else if (type == IConstant.TYPESENDMESSAGE) {
            viewHolder.messageDeleteLayout.setVisibility(View.VISIBLE);
            viewHolder.messageReceivedPraise.setVisibility(View.GONE);
        }

        viewHolder.messageReceivedPraise.setTag(R.id.tag_bean, bean);
        viewHolder.messageReceivedReply.setTag(R.id.tag_bean, bean);

        viewHolder.messageLookupDetailsLayout.setTag(R.id.tag_bean, bean);


        if (bean.user_info.getAvatar_middle() != null) {
            Picasso.with(context).load(bean.user_info.getAvatar_middle()).into(viewHolder.personPhoto);
        }

        if (bean.sourceInfo.is_digg == 0) {
            viewHolder.zanIcon.setImageResource(R.drawable.zan);
        } else {
            viewHolder.zanIcon.setImageResource(R.drawable.zaned);
        }

        String praiseFormat = context.getResources().getString(R.string.person_praise);
        String praiseFinal = String.format(praiseFormat, bean.sourceInfo.digg_count);
        viewHolder.personParise.setText(praiseFinal);

        viewHolder.personName.setText(bean.user_info.getUname());
        viewHolder.personName.append(" ： " + bean.getContent());

        String replyFormat = context.getResources().getString(R.string.person_reply);
        String replyFinal = String.format(replyFormat, bean.sourceInfo.comment_count);
        viewHolder.personReply.setText(replyFinal);


        if (bean.getSourceInfo() == null || StringTools.isNullOrEmpty(bean.getSourceInfo().getFeed_content())) {
            viewHolder.personContent.setVisibility(View.INVISIBLE);


        } else {
            Logger.e("----" + bean.getSourceInfo().getFeed_content());
            viewHolder.personContent.setVisibility(View.VISIBLE);
            viewHolder.personContent.setText("回复" + (type == 0 ? "我" : bean.getSourceInfo().getUname()) + "的" + bean.getSourceInfo().getChannelName() + " : " + bean.getSourceInfo().getFeed_content());
        }
        if (bean.getCtime() != null) {
//            String dateString = DateTools.getNewDate(bean.getCtime());
            String dateString = DateTools.getNewDateFormat(bean.getCtime());
            viewHolder.personTime.setText(dateString);
            viewHolder.personTime.setVisibility(View.VISIBLE);
        } else {
            viewHolder.personTime.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messageReceivedReply:
                IntentTool.replyPage(context, 0, view.getTag(R.id.tag_bean));
                break;
            case R.id.messageReceivedPraise:
                if (type == IConstant.TYPERECEIVEMESSAGE) {
                    MessageReceivedListItemBean freshListModel = (MessageReceivedListItemBean) view.getTag(R.id.tag_bean);
                    if (freshListModel.sourceInfo.is_digg == 0) {
                        freshListModel.sourceInfo.digg_count = freshListModel.sourceInfo.digg_count + 1;
                        freshListModel.sourceInfo.is_digg = 1;
                        viewHolder.zanIcon.setImageResource(R.drawable.zaned);
                        UserController.getInstance().praiseWeiBo(freshListModel.row_id, this);
                    } else {
                        freshListModel.sourceInfo.digg_count = freshListModel.sourceInfo.digg_count - 1;
                        freshListModel.sourceInfo.is_digg = 0;
                        viewHolder.zanIcon.setImageResource(R.drawable.zan);
                        UserController.getInstance().cancelPraiseWeiBo(freshListModel.row_id, this);
                    }
                    notifyDataSetChanged();
                }
                break;
            case R.id.messageDeleteLayout:
                if (type == IConstant.TYPESENDMESSAGE) {
                    ToastTool.show("click delete");
                }
                break;
            case R.id.messageLookupDetailsLayout:
                MessageReceivedListItemBean listItemBean = (MessageReceivedListItemBean) view.getTag(R.id.tag_bean);
                IntentTool.weiboDetailsPage(context, listItemBean.row_id, listItemBean.channelId);
                break;
            default:
                break;
        }
    }


    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, boolean fromNet, Object o) {

    }

    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public TextView personContent; //显示相关内容
        public TextView personTime; //显示时间
        public TextView personReply; //回复
        public TextView personParise; //赞
        public TextView lookupDetails; //查看详情
        public ImageView personPhoto;

        public LinearLayout messageReceivedReply;

        public LinearLayout messageReceivedPraise;

        public LinearLayout messageDeleteLayout;

        public LinearLayout messageLookupDetailsLayout;
        public ImageView zanIcon;
    }


}

