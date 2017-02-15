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
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.LogUtils;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/9.
 */
public class DealWithDiaryAdapter extends BaseAdapter implements View.OnClickListener, ViewNetCallBack {
    private LayoutInflater mLayoutInflater;
    private List<DealWithDiaryListItemBean> dealWithDiaryListItemBeans;
    private ViewHolder viewHolder;
    private Context context;
    private int type;//type==1 日志详情 type==0 待处理日志

    public DealWithDiaryAdapter(Context context, List<DealWithDiaryListItemBean> dealWithDiaryListItemBeans, int type) {
        mLayoutInflater = LayoutInflater.from(context);
        this.dealWithDiaryListItemBeans = dealWithDiaryListItemBeans;
        this.context = context;
        this.type = type;
    }

    public void setData(List<DealWithDiaryListItemBean> listItemBeans) {
        this.dealWithDiaryListItemBeans = listItemBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (dealWithDiaryListItemBeans != null)
            return dealWithDiaryListItemBeans.size();

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
        DealWithDiaryListItemBean listItemBean = dealWithDiaryListItemBeans.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_dealwithdiary, parent, false);
            viewHolder = new ViewHolder();
            if (type == 0) {
                viewHolder.personReply = (TextView) convertView.findViewById(R.id.dealWithDiary_comment);
                viewHolder.personReply.setOnClickListener(this);
                viewHolder.personReply.setTag(R.id.tag_position, position);

                viewHolder.praise = (TextView) convertView.findViewById(R.id.dealWithDiary_praise);
                viewHolder.praise.setTag(R.id.tag_position, position);

                //赞Layout
                viewHolder.zanLayout = (LinearLayout) convertView.findViewById(R.id.zanLayout);
                viewHolder.zanLayout.setOnClickListener(this);
                viewHolder.zanLayout.setTag(R.id.tag_position, position);

                //点评Layout
                viewHolder.commentLayout = (LinearLayout) convertView.findViewById(R.id.commentLayout);
                viewHolder.commentLayout.setOnClickListener(this);
                viewHolder.commentLayout.setTag(R.id.tag_position, position);

                viewHolder.zanPic = (ImageView) convertView.findViewById(R.id.zanPic);
                View viewUP = convertView.findViewById(R.id.dealWithDiaryCommentBar);
                viewUP.setVisibility(View.VISIBLE);
                View viewBottom = convertView.findViewById(R.id.dealWithDiaryDetailsBar);
                viewBottom.setVisibility(View.GONE);
            } else if (type == 1) {
                View viewUP = convertView.findViewById(R.id.dealWithDiaryCommentBar);
                viewUP.setVisibility(View.GONE);

                View viewBottom = convertView.findViewById(R.id.dealWithDiaryDetailsBar);
                viewBottom.setVisibility(View.VISIBLE);

                viewHolder.bottomBarReply = (TextView) convertView.findViewById(R.id.dealWithDiary_reply);
                viewHolder.bottomPraise = (TextView) convertView.findViewById(R.id.dealWithDiaryDetailsPraise);
                viewHolder.bottomBarSave = (TextView) convertView.findViewById(R.id.dealWithDiary_save);


                viewHolder.bottomBarReply.setOnClickListener(this);
                viewHolder.bottomPraise.setOnClickListener(this);
                viewHolder.bottomBarSave.setOnClickListener(this);


            }
            viewHolder.commentPerson = (TextView) convertView.findViewById(R.id.dealWithDiary_commentPerson);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.dealWithDiary_personPic);
            viewHolder.personName = (TextView) convertView.findViewById(R.id.dealWithDiary_personName);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.dealWithDiary_personTime);
            viewHolder.commentPerson = (TextView) convertView.findViewById(R.id.dealWithDiary_commentPerson);
            viewHolder.dealWithDiaryState = (TextView) convertView.findViewById(R.id.dealWithDiary_state);
            viewHolder.todaySummary = (TextView) convertView.findViewById(R.id.dealWithDiary_todayWorkSummaryContent);
            viewHolder.tomorrowSummary = (TextView) convertView.findViewById(R.id.dealWithDiary_tomorrowWorkPlanContent);
            viewHolder.workSummary = (TextView) convertView.findViewById(R.id.dealWithDiary_workSummaryContent);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String sAgeFormat1 = context.getResources().getString(R.string.person_praise);
        String sFinal1 = String.format(sAgeFormat1, listItemBean.digg_count);
        viewHolder.praise.setText(sFinal1);

        if (listItemBean.is_digg == 0) {
            viewHolder.zanPic.setImageResource(R.drawable.zan);
        } else {
            viewHolder.zanPic.setImageResource(R.drawable.zaned);
        }

//        String dateString = DateTools.formatDateWithSecondSince1970("MM月dd日 HH:mm", Long.parseLong(listItemBean.publish_time) * 1000L);
        String dateString = DateTools.friendlyTime(Long.parseLong(listItemBean.publish_time) * 1000L);
        viewHolder.personName.setText(listItemBean.uname);
        viewHolder.personTime.setText(dateString);
        Picasso.with(context).load(listItemBean.avatar_middle).into(viewHolder.personPhoto);

        viewHolder.todaySummary.setText(listItemBean.content_summary);
        viewHolder.tomorrowSummary.setText(listItemBean.content_plan);


        viewHolder.workSummary.setText(listItemBean.content);
        Logger.e(">>>>>>>>>>>>>>>getFeedContent>>>>>>>>>>>>>>>>>>>" + listItemBean.content);
        Logger.e(">>>>>>>>>>>>>getContent>>>>>>>>>>>>>>>>>" + listItemBean.content);
        viewHolder.commentPerson.setText("该日志由" + listItemBean.commentator_uname + "点评");

        if (listItemBean.feed_work_log_result.equals("unknow")) {
            viewHolder.dealWithDiaryState.setText("待点评");
        } else if (listItemBean.feed_work_log_result.equals("done")) {
            viewHolder.dealWithDiaryState.setText("已点评");
        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zanLayout:
                int position = Integer.valueOf(view.getTag(R.id.tag_position).toString());
                DealWithDiaryListItemBean dealWithDiaryListItemBean = dealWithDiaryListItemBeans.get(position);
                if (dealWithDiaryListItemBean.is_digg == 0) {
                    dealWithDiaryListItemBean.digg_count = dealWithDiaryListItemBean.digg_count + 1;
                    dealWithDiaryListItemBean.is_digg = 1;
                    UserController.getInstance().praiseWeiBo(dealWithDiaryListItemBean.feed_id, this);
                } else {
                    dealWithDiaryListItemBean.digg_count = dealWithDiaryListItemBean.digg_count - 1;
                    dealWithDiaryListItemBean.is_digg = 0;
                    UserController.getInstance().cancelPraiseWeiBo(dealWithDiaryListItemBean.feed_id, this);
                }
                notifyDataSetChanged();
                break;
            case R.id.commentLayout:
                int positionComment = Integer.valueOf(view.getTag(R.id.tag_position).toString());
                IntentTool.replyPage(context, 6, dealWithDiaryListItemBeans.get(positionComment));
                break;
            case R.id.dealWithDiary_reply:
//                IntentTool.replyPage(context, 0);
                break;
            case R.id.dealWithDiary_save:
                ToastTool.show("save click!");
                break;
            case R.id.dealWithDiaryDetailsPraise:
                ToastTool.show("praise click!");
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
        public TextView personTime; //显示时间
        public TextView personReply; //回复
        public TextView praise; //赞

        public LinearLayout commentLayout;

        public LinearLayout zanLayout;

        public ImageView zanPic;
        public ImageView personPhoto; //头像
        public TextView commentPerson; //點評人
        public TextView dealWithDiaryState; //日志状态

        public TextView todaySummary; //今日工作總結

        public TextView tomorrowSummary;

        public TextView workSummary;

        public TextView bottomBarReply; //底部回复
        public TextView bottomBarSave; //收藏
        public TextView bottomPraise; //底部赞
    }


    private class ViewCallBack implements com.idxk.mobileoa.utils.net.callback.ViewNetCallBack {

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
            PraiseResultModel praiseResultModel = (PraiseResultModel) result;
            if (praiseResultModel.getSuccess() == 1) {
                LogUtils.e("==============================");
            } else {
                LogUtils.e("============failed==================");
            }
        }
    }
}
