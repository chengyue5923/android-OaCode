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
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.model.bean.RemindMeListModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/8.
 */
public class ReminderMeAdapter extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater mLayoutInflater;
    private List<RemindMeListModel> receivedListItemBeans;
    private ViewHolder viewHolder;
    private Context context;


    public ReminderMeAdapter(Context context, List<RemindMeListModel> reminderMeListItemBeans) {
        mLayoutInflater = LayoutInflater.from(context);
        this.receivedListItemBeans = reminderMeListItemBeans;
        this.context = context;
    }

    public void setData(List<RemindMeListModel> remindMeListModels) {
        this.receivedListItemBeans = remindMeListModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (receivedListItemBeans != null)
            return receivedListItemBeans.size();

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
        RemindMeListModel bean = receivedListItemBeans.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_reminderme, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.personName);
            viewHolder.personContent = (TextView) convertView.findViewById(R.id.personContent);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.personTime);
            viewHolder.personReply = (TextView) convertView.findViewById(R.id.personReply);
            viewHolder.personParise = (TextView) convertView.findViewById(R.id.personPraise);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.personPhoto);

            viewHolder.personPhoto.setOnClickListener(this);

            viewHolder.reminderMeZanPic = (ImageView) convertView.findViewById(R.id.reminderMeZanPic);

            viewHolder.reminderMeReplyLayout = (LinearLayout) convertView.findViewById(R.id.reminderMeReplyLayout);
            viewHolder.reminderMeReplyLayout.setOnClickListener(this);
            viewHolder.reminderMeReplyLayout.setTag(R.id.tag_bean, bean);

            viewHolder.reminderMePraiseLayout = (LinearLayout) convertView.findViewById(R.id.reminderMePraiseLayout);
            viewHolder.reminderMePraiseLayout.setOnClickListener(this);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.reminderMePraiseLayout.setTag(R.id.tag_bean, bean);

        viewHolder.personName.setText(bean.getUname());

        if (bean.getAvatar_middle() != null) {
            Picasso.with(context).load(bean.getAvatar_middle()).into(viewHolder.personPhoto);
        } else {
            Picasso.with(context).load(R.drawable.ic_launcher).into(viewHolder.personPhoto);
        }

        String stringReply = context.getResources().getString(R.string.person_reply);
        if (bean.getComment_count() == null) {
            viewHolder.personReply.setText(String.format(stringReply, 0));
        } else {
            String replayFinal = String.format(stringReply, bean.getComment_count());
            viewHolder.personReply.setText(replayFinal);
        }
        viewHolder.personReply.setTag(R.id.tag_bean, bean);

        String sAgeFormat1 = context.getResources().getString(R.string.person_praise);
        String sFinal1 = String.format(sAgeFormat1, bean.getDigg_count());
        viewHolder.personParise.setText(sFinal1);

        if (bean.getIs_digg() == 0) {
            viewHolder.reminderMeZanPic.setImageResource(R.drawable.zan);
        } else {
            viewHolder.reminderMeZanPic.setImageResource(R.drawable.zaned);
        }
        viewHolder.personParise.setTag(R.id.tag_bean, bean);


        viewHolder.personContent.setText(bean.getFeed_content());
        if (bean.getPublish_time() != null) {
            String dateString = DateTools.formatDateWithSecondSince1970("MM月dd日 HH:mm", Long.parseLong(bean.getPublish_time()) * 1000L);
            viewHolder.personTime.setText(dateString);
        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reminderMePraiseLayout:
                RemindMeListModel remindMeListModel = (RemindMeListModel) view.getTag(R.id.tag_bean);
                if (remindMeListModel.getIs_digg() == 0) {
                    remindMeListModel.setDigg_count(remindMeListModel.getDigg_count() + 1);
                    remindMeListModel.setIs_digg(1);
                    notifyDataSetChanged();
                    UserController.getInstance().praiseWeiBo(remindMeListModel.getFeed_id(), new PraiseResult());
                } else {
                    remindMeListModel.setDigg_count(remindMeListModel.getDigg_count() - 1);
                    remindMeListModel.setIs_digg(0);
                    notifyDataSetChanged();
                    UserController.getInstance().cancelPraiseWeiBo(remindMeListModel.getFeed_id(), new PraiseResult());
                }
                break;
            case R.id.reminderMeReplyLayout:
                IntentTool.replyPage(context, 0, view.getTag(R.id.tag_bean));
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
        public TextView personParise; //赞
        public ImageView personPhoto;

        public LinearLayout reminderMeReplyLayout;

        public LinearLayout reminderMePraiseLayout;

        public ImageView reminderMeZanPic;
    }

    public class PraiseResult implements ViewNetCallBack {

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
            PraiseResultModel resultModel = (PraiseResultModel) result;
            if (resultModel.getSuccess() == 1) {

            }
        }
    }
}
