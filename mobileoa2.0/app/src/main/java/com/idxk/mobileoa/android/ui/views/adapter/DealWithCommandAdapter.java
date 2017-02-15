package com.idxk.mobileoa.android.ui.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.dialog.WriteReplyDialog;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.FileListsBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/10.
 */
public class DealWithCommandAdapter extends BaseAdapter implements View.OnClickListener, ViewNetCallBack {
    private LayoutInflater mLayoutInflater;
    private List<DealWithDiaryListItemBean> dealWithCommandListItemBeans;
    private ViewHolder viewHolder;
    private Context context;
    private Activity activity;
    private int type; //0==指令页面 1==审批页面

    public DealWithCommandAdapter(Context context, List<DealWithDiaryListItemBean> dealWithCommandListItemBeans, int type) {
        mLayoutInflater = LayoutInflater.from(context);
        this.dealWithCommandListItemBeans = dealWithCommandListItemBeans;
        this.context = context;
        this.activity = (Activity) context;
        this.type = type;
    }

    public void setDate(List<DealWithDiaryListItemBean> listItemBeans) {
        this.dealWithCommandListItemBeans = listItemBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (dealWithCommandListItemBeans != null)
            return dealWithCommandListItemBeans.size();

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return dealWithCommandListItemBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DealWithDiaryListItemBean dealWithDiaryListItemBean = dealWithCommandListItemBeans.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_dealwithcommand, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.replyResult = (TextView) convertView.findViewById(R.id.command_writeReply);
            viewHolder.bottomBarReply = (TextView) convertView.findViewById(R.id.command_save);
            viewHolder.bottomBarSave = (TextView) convertView.findViewById(R.id.command_praise);
            viewHolder.bottomPraise = (TextView) convertView.findViewById(R.id.command_cancel);
            viewHolder.command_timeout = (TextView) convertView.findViewById(R.id.command_timeout);
            viewHolder.command_zanPic = (ImageView) convertView.findViewById(R.id.command_zanPic);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.command_personPhoto);
            viewHolder.personName = (TextView) convertView.findViewById(R.id.command_personName);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.command_showTime);
            viewHolder.commentPerson = (TextView) convertView.findViewById(R.id.command_replyContent);
            viewHolder.dealWithCommandState = (TextView) convertView.findViewById(R.id.command_state);


            viewHolder.content = (TextView) convertView.findViewById(R.id.command_content);

            viewHolder.imageShow = (LinearLayout) convertView.findViewById(R.id.imageShow);
            viewHolder.fileImage = (ImageView) convertView.findViewById(R.id.fileImage);
            viewHolder.fileShow = (TextView) convertView.findViewById(R.id.fileShow);


//            viewHolder.replyResult.setOnClickListener(this);
//            viewHolder.bottomBarReply.setOnClickListener(this);
//            viewHolder.bottomBarSave.setOnClickListener(this);
//            viewHolder.bottomPraise.setOnClickListener(this);
            viewHolder.examine_praise = (ImageView) convertView.findViewById(R.id.examine_praise);

            //汇报结果
            viewHolder.noticeResultLayout = (LinearLayout) convertView.findViewById(R.id.noticeResultLayout);
            viewHolder.noticeResultLayout.setOnClickListener(this);

            //回复

            viewHolder.replyLayout = (LinearLayout) convertView.findViewById(R.id.command_replyLayout);
            viewHolder.replyLayout.setOnClickListener(this);
            //赞
            viewHolder.commandPraiseLayout = (LinearLayout) convertView.findViewById(R.id.commandPraiseLayout);
            viewHolder.commandPraiseLayout.setOnClickListener(this);

            viewHolder.commandView = (ImageView) convertView.findViewById(R.id.commandView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DealWithDiaryListItemBean bean = dealWithCommandListItemBeans.get(position);
        if (bean.getHas_attach() == 0) {
            viewHolder.fileImage.setVisibility(View.GONE);
            viewHolder.fileShow.setVisibility(View.GONE);
        } else {
            viewHolder.imageShow.setVisibility(View.VISIBLE);
            if (bean.isFile()) {
                viewHolder.fileImage.setVisibility(View.GONE);
                viewHolder.fileShow.setVisibility(View.VISIBLE);
                viewHolder.fileShow.setOnClickListener(this);
                viewHolder.fileShow.setTag(position);
            } else {
                viewHolder.fileImage.setOnClickListener(this);
//                viewHolder. imageShow.setOnClickListener(this);
                viewHolder.fileImage.setVisibility(View.VISIBLE);
                viewHolder.fileShow.setVisibility(View.GONE);
                try {

                    viewHolder.fileImage.setTag(bean.getAttach().get(0).getAttach_url());
                    Picasso.with(context).load(bean.getAttach().get(0).getAttach_small()).into(viewHolder.fileImage);
                    viewHolder.imageShow.setTag(bean.getAttach().get(0).getAttach_url());
                } catch (Exception e) {
                    viewHolder.imageShow.setVisibility(View.GONE);
                }
            }


        }


        if (type == 0) {

            //指令頁面處理
            viewHolder.replyResult.setText(R.string.replyResult);

//            viewHolder.bottomBarReply.setText(R.string.reply);
            viewHolder.bottomBarReply.setTag(R.id.tag_bean, dealWithDiaryListItemBean);
            viewHolder.command_zanPic.setImageResource(R.drawable.replay);

            viewHolder.bottomBarSave.setText(R.string.person_tab_collect);
            viewHolder.bottomPraise.setText(R.string.praise);

            viewHolder.command_timeout.setVisibility(View.VISIBLE);

            //汇报结果
            viewHolder.noticeResultLayout.setTag(R.id.tag_bean, dealWithDiaryListItemBean);

            viewHolder.commandPraiseLayout.setTag(R.id.tag_bean, dealWithDiaryListItemBean);

            viewHolder.replyLayout.setTag(R.id.tag_bean, dealWithDiaryListItemBean);

            String sAgeFormat1 = context.getResources().getString(R.string.person_praise);
            String sFinal1 = String.format(sAgeFormat1, dealWithDiaryListItemBean.digg_count);
            viewHolder.bottomPraise.setText(sFinal1);

            String replyFormat = context.getResources().getString(R.string.person_reply);
            String replyFinal = String.format(replyFormat, dealWithDiaryListItemBean.comment_count);
            viewHolder.bottomBarReply.setText(replyFinal);

            if (dealWithDiaryListItemBean.is_digg == 0) {
                viewHolder.command_zanPic.setImageResource(R.drawable.zan);
            } else {
                viewHolder.command_zanPic.setImageResource(R.drawable.zaned);
            }

            String dateString = DateTools.formatDateWithSecondSince1970("MM月dd日 HH:mm", Long.parseLong(dealWithDiaryListItemBean.publish_time) * 1000L);
            viewHolder.commentPerson.setText("该指令由" + dealWithDiaryListItemBean.uname + "执行应于" + dateString + "完成");

            //“feed_order_result”: “wait”, //channelId=3时，wait：等待处理，doing：处理中，done：完成，cancel：取消
            if (dealWithDiaryListItemBean.feed_order_result != null) {
                if (dealWithDiaryListItemBean.feed_order_result.equals("wait")) {
                    viewHolder.dealWithCommandState.setText("指令-等待处理");
                    viewHolder.content.setText("该指令等待处理...");
                } else if (dealWithDiaryListItemBean.feed_order_result.equals("doing")) {
                    viewHolder.dealWithCommandState.setText("指令-处理中");
                    viewHolder.content.setText("该指令处理中...");
                } else if (dealWithDiaryListItemBean.feed_order_result.equals("done")) {
                    viewHolder.dealWithCommandState.setText("指令-已完成");
                    viewHolder.content.setText("该指令已完成...");
                } else if (dealWithDiaryListItemBean.equals("cancel")) {
                    viewHolder.dealWithCommandState.setText("指令-已取消");
                    viewHolder.content.setText("该指令已取消...");
                }
            }
            viewHolder.command_timeout.setText(dealWithDiaryListItemBean.content);
        } else if (type == 1) {

            //审批页面
            viewHolder.replyResult.setText(R.string.writeReply);
            viewHolder.bottomBarReply.setText(R.string.praise);
            viewHolder.bottomPraise.setText(R.string.cancel);
            viewHolder.command_timeout.setVisibility(View.GONE);
            viewHolder.commandPraiseLayout.setVisibility(View.GONE);
            viewHolder.commandView.setVisibility(View.GONE);

            viewHolder.replyResult.setTag(R.id.tag_bean, dealWithDiaryListItemBean);

            //“feed_approval_result”: “unknow”, 审批结果 yes：通过 ，no ：否决，unknow：未批复
            if (dealWithDiaryListItemBean.feed_approval_result != null) {
                if (dealWithDiaryListItemBean.feed_approval_result.equals("unknow")) {
                    viewHolder.dealWithCommandState.setText("审批-未批复");
                } else if (dealWithDiaryListItemBean.feed_approval_result.equals("yes")) {
                    viewHolder.dealWithCommandState.setText("审批-通过");
                } else if (dealWithDiaryListItemBean.feed_approval_result.equals("no")) {
                    viewHolder.dealWithCommandState.setText("审批-未批复");
                }
            }

            if (dealWithDiaryListItemBean.is_digg == 0) {
                viewHolder.examine_praise.setImageResource(R.drawable.zan);
            } else {
                viewHolder.examine_praise.setImageResource(R.drawable.zaned);
            }

            String sAgeFormat1 = context.getResources().getString(R.string.person_praise);
            String sFinal1 = String.format(sAgeFormat1, dealWithDiaryListItemBean.digg_count);
            viewHolder.bottomBarReply.setText(sFinal1);

            viewHolder.commentPerson.setText("已提交，待" + dealWithDiaryListItemBean.commentator_uname + "审批。");
            viewHolder.content.setText(dealWithDiaryListItemBean.content);

            viewHolder.noticeResultLayout.setTag(R.id.tag_bean, dealWithDiaryListItemBean);


            viewHolder.replyLayout.setTag(R.id.tag_bean, dealWithDiaryListItemBean);
        }


        viewHolder.personName.setText(dealWithDiaryListItemBean.uname);
//        String dateString = DateTools.formatDateWithSecondSince1970("MM月dd日 HH:mm", Long.parseLong(dealWithDiaryListItemBean.publish_time) * 1000L);
        String dateString = DateTools.friendlyTime(Long.parseLong(dealWithDiaryListItemBean.publish_time) * 1000L);
        viewHolder.personTime.setText(dateString);
        if (dealWithDiaryListItemBean.avatar_middle != null) {
            Picasso.with(context).load(dealWithDiaryListItemBean.avatar_middle).into(viewHolder.personPhoto);
        } else {
            Picasso.with(context).load(R.drawable.ic_launcher).into(viewHolder.personPhoto);
        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.noticeResultLayout:
                if (type == 1) {
                    WriteReplyDialog writeReplyDialog = new WriteReplyDialog(context, "1", view.getTag(R.id.tag_bean));
                    writeReplyDialog.show();
                } else if (type == 0) {
                    IntentTool.replyPage(context, 4, view.getTag(R.id.tag_bean));
                }
                break;
            case R.id.command_replyLayout:
                if (type == 0) {
                    IntentTool.replyPage(context, 0, view.getTag(R.id.tag_bean));
                } else if (type == 1) {
                    DealWithDiaryListItemBean dealWithDiaryListItemBean = (DealWithDiaryListItemBean) view.getTag(R.id.tag_bean);
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
                }
                break;
            case R.id.command_praise:
                ToastTool.show("details click");
                break;
            case R.id.commandPraiseLayout:
                if (type == 0) {
                    DealWithDiaryListItemBean dealWithDiaryListItemBean = (DealWithDiaryListItemBean) view.getTag(R.id.tag_bean);
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
                }
                break;
            case R.id.dialog_agree:
                ToastTool.show("praise click!");
                break;
            case R.id.imageShow:
                try {
                    IntentTool.imageViewShowPage(context, view.getTag().toString());
                } catch (Exception e) {

                }

                break;
            case R.id.fileImage:
                try {
                    IntentTool.imageViewShowPage(context, view.getTag().toString());
                } catch (Exception e) {

                }

                break;
            case R.id.fileShow:
                FileListsBean beans = new FileListsBean();

                Object o = view.getTag();
                if (o != null) {
                    int position = Integer.parseInt(o.toString());
                    beans.setList(dealWithCommandListItemBeans.get(position).getAttach());
                    IntentTool.startFilesShowActivity((Activity) context, beans);
                }

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
        public ImageView personPhoto; //头像
        public TextView commentPerson; //點評人
        public TextView content;
        public TextView dealWithCommandState; //日志状态

        public TextView replyResult;
        public TextView bottomBarReply; //底部回复
        public TextView bottomBarSave; //收藏
        public TextView bottomPraise; //底部赞

        public TextView command_timeout;

        public ImageView command_zanPic;

        public LinearLayout noticeResultLayout; //汇报结果
        public LinearLayout replyLayout; //回复
        public LinearLayout commandPraiseLayout;


        public ImageView examine_praise;

        public ImageView commandView;


        public LinearLayout imageShow;
        public ImageView fileImage;
        public TextView fileShow;


    }
}
