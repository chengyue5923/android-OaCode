package com.idxk.mobileoa.android.ui.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.dialog.WriteReplyDialog;
import com.idxk.mobileoa.config.constant.Constant;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.exception.ResolveException;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.model.bean.DealWithDiaryListItemBean;
import com.idxk.mobileoa.model.bean.FileListsBean;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.file.ImageLoaderManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 15-3-4.
 */
public class FreshListAdapter extends BaseAdapter implements View.OnClickListener, ViewNetCallBack {
    private LayoutInflater mLayoutInflater;
    private List<FreshListModel> freshListItemBeanList;
    private ViewHolder viewHolder;
    private Context context;

    public FreshListAdapter(Context context, List<FreshListModel> freshListItemBeanList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.freshListItemBeanList = freshListItemBeanList;
        this.freshListItemBeanList = new LinkedList<>();
        this.context = context;
    }

    public void setData(List<FreshListModel> lists) {
        this.freshListItemBeanList = lists;
        notifyDataSetChanged();
    }

    public void appendData(List<FreshListModel> lists) {
        if (ListUtil.isNullOrEmpty(lists)) {
            return;
        }


        if (freshListItemBeanList.containsAll(lists)) {
            freshListItemBeanList.removeAll(lists);
            freshListItemBeanList.addAll(lists);
        } else {
            freshListItemBeanList.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (freshListItemBeanList != null)
            return freshListItemBeanList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return freshListItemBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FreshListModel bean = freshListItemBeanList.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_fresh, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.personName);
            viewHolder.personContent = (TextView) convertView.findViewById(R.id.personContent);
            viewHolder.fileShow = (ImageView) convertView.findViewById(R.id.fileShow);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.personTime);
            viewHolder.personReply = (TextView) convertView.findViewById(R.id.personReply);
            viewHolder.personParise = (TextView) convertView.findViewById(R.id.personPraise);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.personPhoto);
            viewHolder.fileImage = (ImageView) convertView.findViewById(R.id.fileImage);
            viewHolder.picture_number = (TextView) convertView.findViewById(R.id.picture_number);
            viewHolder.frameLayout = (FrameLayout) convertView.findViewById(R.id.picture_frameLayout);
            viewHolder.attach_frameLayout = (FrameLayout) convertView.findViewById(R.id.attach_frameLayout);
            viewHolder.attach_number = (TextView) convertView.findViewById(R.id.attach_number);
            viewHolder.imageShow = (LinearLayout) convertView.findViewById(R.id.imageShow);

            viewHolder.work_approval_image = (ImageView) convertView.findViewById(R.id.work_approval_image);


            //日志内容显示
            viewHolder.diaryContentLayout = (LinearLayout) convertView.findViewById(R.id.diaryContentLayout);
            viewHolder.diary_todayContent = (TextView) convertView.findViewById(R.id.diaryContent_todayWorkSummaryContent);
            viewHolder.diary_tomorrowContent = (TextView) convertView.findViewById(R.id.diaryContent_tomorrowWorkPlanContent);
            viewHolder.diary_workSummary = (TextView) convertView.findViewById(R.id.diaryContent_workSummaryContent);

            viewHolder.workState = (TextView) convertView.findViewById(R.id.fresh_state);
            viewHolder.freshStateIcon = (ImageView) convertView.findViewById(R.id.fresh_state_icon);
            viewHolder.examine_state = (TextView) convertView.findViewById(R.id.examine_state);

            viewHolder.work_scope = (ImageView) convertView.findViewById(R.id.work_scope);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.personParise.setOnClickListener(this);
        viewHolder.personReply.setOnClickListener(this);
        viewHolder.personPhoto.setOnClickListener(this);
        viewHolder.fileImage.setOnClickListener(this);
        viewHolder.fileShow.setOnClickListener(this);

        viewHolder.work_scope.setOnClickListener(this);
        viewHolder.work_scope.setTag(R.id.tag_bean, bean);

        viewHolder.personPhoto.setTag(R.id.tag_bean, bean);


        if (bean.getHas_attach() == 0) {
            viewHolder.fileImage.setVisibility(View.GONE);
            viewHolder.fileShow.setVisibility(View.GONE);
            viewHolder.frameLayout.setVisibility(View.GONE);
            viewHolder.attach_frameLayout.setVisibility(View.GONE);
        } else {

            if (bean.isFile()) {
                viewHolder.frameLayout.setVisibility(View.GONE);
                viewHolder.fileImage.setVisibility(View.GONE);
                viewHolder.fileShow.setVisibility(View.VISIBLE);
                viewHolder.attach_frameLayout.setVisibility(View.VISIBLE);
                viewHolder.fileShow.setOnClickListener(this);
                viewHolder.attach_number.setText(bean.getAttach().size() + "个");
                viewHolder.fileShow.setTag(position);
            } else {
                viewHolder.fileImage.setOnClickListener(this);
                viewHolder.fileImage.setVisibility(View.VISIBLE);
                viewHolder.frameLayout.setVisibility(View.VISIBLE);
                viewHolder.fileShow.setVisibility(View.GONE);
                viewHolder.attach_frameLayout.setVisibility(View.GONE);
                viewHolder.picture_number.setText(bean.getAttach().size() + "张");
                try {
                    ImageLoaderManager.getInstance().disPlayImage(bean.getAttach().get(0).getAttach_small(), viewHolder.fileImage);
//                    Picasso.with(context).load(bean.getAttach().get(0).getAttach_small()).into(viewHolder.fileImage);
                    viewHolder.fileImage.setTag(bean.getAttach());
                } catch (Exception e) {
                    viewHolder.imageShow.setVisibility(View.GONE);
                }
            }


        }

        if (StringTools.isNullOrEmpty(bean.getApproval_form_id())||StringTools.toTrim(bean.getApproval_form_id()).equals("0")) {
            viewHolder.work_approval_image.setVisibility(View.GONE);
        } else {
            viewHolder.work_approval_image.setVisibility(View.VISIBLE);
            viewHolder.work_approval_image.setTag(bean.getApproval_form_id());
            viewHolder.work_approval_image.setOnClickListener(this);
        }


        if (!"2".equals(bean.getChannel_id())) {
            viewHolder.diaryContentLayout.setVisibility(View.GONE);
            viewHolder.personContent.setText(bean.getContent());
            viewHolder.personContent.setVisibility(View.VISIBLE);
        } else {
            viewHolder.diaryContentLayout.setVisibility(View.VISIBLE);
            viewHolder.personContent.setText("该日志由" + bean.getUname() + "点评");
            viewHolder.personContent.setVisibility(View.GONE);
            //日志的工作心得框（第三个）
            viewHolder.diary_workSummary.setText(bean.getContent());
        }

        if ("1".equals(bean.getChannel_id())) {
//            viewHolder.workState.setText(context.getResources().getString(R.string.share));
            viewHolder.freshStateIcon.setImageResource(R.drawable.workshare);
            viewHolder.workState.setVisibility(View.GONE);
            viewHolder.examine_state.setVisibility(View.GONE);
        }

        if ("2".equals(bean.getChannel_id())) {
//            if (bean.getFeed_work_log_result() != null) {
//                String diaryState = "";
//                if ("unknow".equals(bean.getFeed_work_log_result())) {
//                    diaryState = "待点评";
//                } else if ("done".equals(bean.getFeed_work_log_result())) {
//                    diaryState = "已点评";
//                }
//                viewHolder.workState.setVisibility(View.VISIBLE);
//                viewHolder.workState.setText(context.getResources().getString(R.string.diary) + "-" + diaryState);
//                viewHolder.examine_state.setVisibility(View.GONE);
//            } else {
//                viewHolder.workState.setText(context.getResources().getString(R.string.diary));
//                viewHolder.examine_state.setVisibility(View.GONE);
//            }
            viewHolder.freshStateIcon.setImageResource(R.drawable.workdiary);
            viewHolder.workState.setVisibility(View.GONE);
            viewHolder.examine_state.setVisibility(View.GONE);
        }

        if ("3".equals(bean.getChannel_id())) {
            viewHolder.examine_state.setVisibility(View.GONE);
            viewHolder.workState.setVisibility(View.VISIBLE);
            String commandState = "";
            if (bean.getFeed_order_result() != null) {
                if ("wait".equals(bean.getFeed_order_result())) {
                    commandState = "待" + bean.getCommentator_uname() + "执行";
                    viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_waitcommand);
                } else if ("doing".equals(bean.getFeed_order_result())) {
                    commandState = "处理中";
                    viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_waitcommand);
                } else if ("done".equals(bean.getFeed_order_result())) {
                    commandState = "已执行";
                    viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_rancommand);
                } else if ("cancel".equals(bean.getFeed_order_result())) {
                    commandState = "已取消";
                    viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_cancel);
                }
                Logger.e(bean.getOrder_end_time() + "getTime");
                String time = DateTools.getNewDate(bean.getOrder_end_time());
                viewHolder.examine_state.setText("该指令由" + bean.getCommentator_uname() + "执行，应于" + time + "前完成");
                viewHolder.examine_state.setVisibility(View.VISIBLE);
            }

            if (commandState.length() > 1) {
                viewHolder.workState.setText(commandState);
            } else {
                viewHolder.workState.setText("待" + bean.getCommentator_uname() + "执行");
                viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_waitcommand);
            }


        }

        if ("4".equals(bean.getChannel_id())) {
            Logger.e("====================get state============================" + bean.getFeed_status());
            if (bean.getFeed_status() != null) {
                viewHolder.workState.setVisibility(View.VISIBLE);
                if ("wait".equals(bean.getFeed_status())) {
                    viewHolder.examine_state.setText("已提交，待" + bean.getApproval_user_info().getUname() + "审批");
                    viewHolder.examine_state.setVisibility(View.GONE);
//                    viewHolder.workState.setText(context.getResources().getString(R.string.examine) + "-" + "待审批");
                    viewHolder.workState.setText("待" + bean.getApproval_user_info().getUname() + "审批");
                    viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_waitapprovel);
                } else if ("done".equals(bean.getFeed_status())) {
                    String state = null;
                    if (bean.getFeed_approval_result() != null) {
                        if ("unknow".equals(bean.getFeed_approval_result())) {
                            viewHolder.examine_state.setText("审批未批复");
                            viewHolder.examine_state.setVisibility(View.GONE);
                            viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_waitapprovel);
                            state = "未批复";
                        } else if ("yes".equals(bean.getFeed_approval_result())) {
                            viewHolder.examine_state.setText("审批已通过");
                            viewHolder.examine_state.setVisibility(View.GONE);
                            state = "审批已通过";
                            viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_passapprovel);

                        } else if ("no".equals(bean.getFeed_approval_result())) {
                            viewHolder.examine_state.setText("审批未通过");
                            viewHolder.examine_state.setVisibility(View.GONE);
                            state = "审批未通过";
                            viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_nopass);
                        }
                    }
                    if (state == null) {
                        state = "待" + bean.getApproval_user_info().getUname() + "审批";
                        viewHolder.freshStateIcon.setImageResource(R.drawable.workstate_waitapprovel);
                    }
                    viewHolder.workState.setText(state);
                }
            }


//            if (state != null) {
//                viewHolder.workState.setText(context.getResources().getString(R.string.examine) + "-" + state);
//            } else {
//                viewHolder.workState.setText(context.getResources().getString(R.string.examine));
//            }

        }


        ImageLoaderManager.getInstance().disPlayImage(bean.getAvatar_middle(), viewHolder.personPhoto);
//        Picasso.with(context).load(bean.getAvatar_middle()).placeholder(R.drawable.defaulticon).error(R.drawable.defaulticon).into(viewHolder.personPhoto);
        StringBuilder stringBuilder = new StringBuilder();
        if (!StringTools.isNullOrEmpty(bean.getUname())) {
            stringBuilder.append(bean.getUname());
        }


        // 日志的工作总结（第一个框）
        if (!StringTools.isNullOrEmpty(bean.getContent_summary())) {
            viewHolder.diary_todayContent.setText(bean.getContent_summary());
        }

        //日志的工作计划（第二个框）
        if (!StringTools.isNullOrEmpty(bean.getContent_plan())) {
            viewHolder.diary_tomorrowContent.setText(bean.getContent_plan());
        }


        viewHolder.personName.setText(stringBuilder.toString());


        if (bean.getIs_digg() == 0) {
            Drawable drawableNoDigg = context.getResources().getDrawable(R.drawable.zan);
            if (drawableNoDigg != null) {
                drawableNoDigg.setBounds(0, 0, drawableNoDigg.getMinimumWidth(), drawableNoDigg.getMinimumHeight());
                viewHolder.personParise.setCompoundDrawables(drawableNoDigg, null, null, null);
            }

        } else if (bean.getIs_digg() == 1) {
            Drawable drawableZaned = context.getResources().getDrawable(R.drawable.zaned);
            if (drawableZaned != null) {
                drawableZaned.setBounds(0, 0, drawableZaned.getMinimumWidth(), drawableZaned.getMinimumHeight());
                viewHolder.personParise.setCompoundDrawables(drawableZaned, null, null, null);
            }
        }

        //赞的处理
//        String sAgeFormat1 = context.getResources().getString(R.string.person_praise);
//        String sFinal1 = String.format(sAgeFormat1, bean.getDigg_count());


        viewHolder.personParise.setText(bean.getDigg_count() + "");
        viewHolder.personParise.setTag(R.id.tag_position, position);


        //回复
        Logger.e(bean.getFeed_status() + "=============");
        String uid = PreferceManager.getInsance().getValueBYkey(context, "uid");
        if (bean.getFeed_status() != null && "4".equals(bean.getChannel_id()) && String.valueOf(bean.getApproval_user_info().getUid()).equals(uid)) {
            viewHolder.personReply.setTag(R.id.tag_state, bean.getFeed_status());
            if ("wait".equals(bean.getFeed_status())) {
                viewHolder.personReply.setText("批复");
                viewHolder.personReply.setTag(R.id.tag_position, position);
                viewHolder.personReply.setTag(R.id.tag_type, bean.getChannel_id());
//                viewHolder.replyLayout.setTag(R.id.tag_position, position);
//                viewHolder.replyLayout.setTag(R.id.tag_type, bean.getChannel_id());
            } else {
                viewHolder.personReply.setText(bean.getComment_count());
                viewHolder.personReply.setTag(R.id.tag_position, position);
                viewHolder.personReply.setTag(R.id.tag_type, bean.getChannel_id());
//                viewHolder.replyLayout.setTag(R.id.tag_position, position);
//                viewHolder.replyLayout.setTag(R.id.tag_type, bean.getChannel_id());
            }

        } else {
            viewHolder.personReply.setText(bean.getComment_count());
            viewHolder.personReply.setTag(R.id.tag_position, position);
            viewHolder.personReply.setTag(R.id.tag_type, bean.getChannel_id());
//            viewHolder.replyLayout.setTag(R.id.tag_position, position);
//            viewHolder.replyLayout.setTag(R.id.tag_type, bean.getChannel_id());
        }


        if (null != bean.getPublish_time()) {
//            String dateString = DateTools.formatDateWithSecondSince1970("MM月dd日 HH:mm", Long.parseLong(bean.getPublish_time()) * 1000L);
//            Logger.e(bean.getPublish_time() + "BaseTime");
            String dateString = DateTools.friendlyTime(Long.parseLong(bean.getPublish_time()) * 1000L);
            viewHolder.personTime.setText(dateString);
        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personPraise:
                int position = Integer.valueOf(view.getTag(R.id.tag_position).toString());
                FreshListModel freshListModel = freshListItemBeanList.get(position);
                if (freshListModel.getIs_digg() == 0) {
                    freshListModel.setDigg_count(freshListModel.getDigg_count() + 1);
                    freshListModel.setIs_digg(1);
                    UserController.getInstance().praiseWeiBo(freshListModel.getFeed_id(), this);
                } else {
                    freshListModel.setDigg_count(freshListModel.getDigg_count() - 1);
                    freshListModel.setIs_digg(0);
                    UserController.getInstance().cancelPraiseWeiBo(freshListModel.getFeed_id(), this);
                }
                notifyDataSetChanged();
                break;

            case R.id.personPhoto:
                FreshListModel freshListModel2 = (FreshListModel) view.getTag(R.id.tag_bean);
                IntentTool.startContactDetailActivityByName((Activity) context, freshListModel2.getUname());
                break;

            case R.id.personReply:
                try {
                    int replyPosition1 = Integer.valueOf(view.getTag(R.id.tag_position).toString());
                    if (Integer.valueOf(view.getTag(R.id.tag_type).toString()) == 4) {
                        if (view.getTag(R.id.tag_state) != null && ("wait").equals(view.getTag(R.id.tag_state).toString())) {
                            DealWithDiaryListItemBean dealWithDiaryListItemBean = new DealWithDiaryListItemBean();
                            dealWithDiaryListItemBean.feed_id = freshListItemBeanList.get(replyPosition1).getFeed_id();
                            WriteReplyDialog writeReplyDialog = new WriteReplyDialog(context, "1", dealWithDiaryListItemBean);
                            writeReplyDialog.show();
                        } else {
                            IntentTool.replyPage(context, 0, freshListItemBeanList.get(replyPosition1));
                        }
                    } else {
                        IntentTool.replyPage(context, 0, freshListItemBeanList.get(replyPosition1));
                    }
                } catch (Exception e) {
                    Logger.e(e.getCause().toString());
                }
                break;

            case R.id.imageShow:
                try {
                    IntentTool.imageViewShowPage(context, view.getTag().toString());
                } catch (Exception ignored) {

                }

                break;
            case R.id.fileImage:
                try {
                    IntentTool.scanPicture(context, (List<AttachBean>) view.getTag());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

//            case R.id.prasieLayout:
//                int position1 = Integer.valueOf(view.findViewById(R.id.personPraise).getTag(R.id.tag_position).toString());
//                FreshListModel freshListModel1 = freshListItemBeanList.get(position1);
//                if (freshListModel1.getIs_digg() == 0) {
//                    freshListModel1.setDigg_count(freshListModel1.getDigg_count() + 1);
//                    freshListModel1.setIs_digg(1);
//                    UserController.getInstance().praiseWeiBo(freshListModel1.getFeed_id(), this);
//                } else {
//                    freshListModel1.setDigg_count(freshListModel1.getDigg_count() - 1);
//                    freshListModel1.setIs_digg(0);
//                    UserController.getInstance().cancelPraiseWeiBo(freshListModel1.getFeed_id(), this);
//                }
//                notifyDataSetChanged();
//                break;
            case R.id.fileShow:
                FileListsBean beans = new FileListsBean();
                Object o = view.getTag();
                if (o != null) {
                    position = Integer.parseInt(o.toString());
                    beans.setList(freshListItemBeanList.get(position).getAttach());
                    IntentTool.startFilesShowActivity((Activity) context, beans);
                }
                break;

            case R.id.work_scope:
                FreshListModel freshListModel1 = (FreshListModel) view.getTag(R.id.tag_bean);
                Object jsonObject = freshListModel1.getScope_info().getData();
                Logger.e("result-" + jsonObject.toString());

                try {
                    HashMap<String, String> hashMap1 = GsonTool.jsonToHas(jsonObject.toString());
                    List<HashMap<String, String>> hashMapList = new ArrayList<>();
                    Logger.e("GetSize" + hashMap1.size());
                    for (Map.Entry<String, String> entry : hashMap1.entrySet()) {
                        Object key = entry.getKey();
                        Object val = entry.getValue();
                        Logger.e(key.toString() + val.toString());
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put(key.toString(), val.toString());
                        hashMapList.add(hashMap);
                    }
                    IntentTool.startSendRangeListActivity((Activity) context, ContactController.getInstance().getMixList(hashMapList));
                } catch (ResolveException e) {
                    Logger.e(e.getLocalizedMessage());
                }
                break;
            case R.id.work_approval_image:
                String oauth_token = PreferceManager.getInsance().getValueBYkey(context, "oauth_token");
                String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(context, "oauth_token_secret");
                String url = UrlRes.getInstance().getUrlSever() + Constant.APPROVAL_URL + "&oauth_token=" + oauth_token
                        + "&oauth_token_secret=" + oauth_token_secret + "&form_id=" + view.getTag().toString();
                Logger.e("click=" + url);
                IntentTool.startApprovalShow1Activity(context, url);
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
        PraiseResultModel resultModel = (PraiseResultModel) result;
        if (resultModel.getSuccess() == 1) {
//            String sAgeFormat1 = context.getResources().getString(R.string.person_praise);
//            String sFinal1 = String.format(sAgeFormat1, 1);
//            viewHolder.personParise.setText(sFinal1);
//            notifyDataSetChanged();
        }

    }

    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public TextView personContent; //显示相关内容
        public TextView personTime; //显示时间
        public TextView personReply; //回复
        public TextView personParise; //赞
        public ImageView personPhoto;
        public ImageView freshStateIcon;
        public ImageView fileImage;
        public LinearLayout diaryContentLayout;

        public TextView diary_todayContent; //今日显示内容
        public TextView diary_tomorrowContent; //明日
        public TextView diary_workSummary;
        public ImageView fileShow;

        public TextView workState;

        public LinearLayout imageShow;

        public ImageView work_scope;

        public TextView examine_state;

        public TextView picture_number;

        public FrameLayout frameLayout;

        public FrameLayout attach_frameLayout;

        public TextView attach_number;

        public ImageView work_approval_image;

    }

}
