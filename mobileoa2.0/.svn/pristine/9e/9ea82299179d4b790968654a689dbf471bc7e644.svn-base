package com.idxk.mobileoa.android.ui.views.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.dialog.factory.DialogFacory;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.PraiseResultModel;
import com.idxk.mobileoa.model.bean.WeiboCommentListModel;
import com.idxk.mobileoa.utils.common.android.FileIntent;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.DateTools;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StaticUtils;
import com.idxk.mobileoa.utils.net.callback.FileDownListener;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.file.ImageLoaderManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/11.
 */
public class WeiBoDetailsOperateAdapter extends BaseAdapter implements View.OnClickListener {
    private LayoutInflater mLayoutInflater;
    private List<WeiboCommentListModel> weiBoDetailsOperateListItemBeans;
    private ViewHolder viewHolder;
    private Context context;
    private Dialog dialog;

    public WeiBoDetailsOperateAdapter(Context context, List<WeiboCommentListModel> weiBoDetailsOperateListItemBeans) {
        mLayoutInflater = LayoutInflater.from(context);
        this.weiBoDetailsOperateListItemBeans = weiBoDetailsOperateListItemBeans;
        this.context = context;
        dialog = DialogFacory.getInstance().createRunDialog(context);
    }

    public void setData(List<WeiboCommentListModel> weiboCommentListModels) {
        this.weiBoDetailsOperateListItemBeans = weiboCommentListModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (weiBoDetailsOperateListItemBeans != null)
            return weiBoDetailsOperateListItemBeans.size();

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
        final WeiboCommentListModel bean = weiBoDetailsOperateListItemBeans.get(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_weibodetailsoperate, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.weiBoDetailsOperateName);
            viewHolder.personContent = (TextView) convertView.findViewById(R.id.weiBoDetailsOperateContent);
            viewHolder.personTime = (TextView) convertView.findViewById(R.id.weiBoDetailsOperateShowTime);
            viewHolder.personReply = (TextView) convertView.findViewById(R.id.weiBoDetailsOperateReply);
            viewHolder.personPraise = (TextView) convertView.findViewById(R.id.weiBoDetailsOperatePraise);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.weiBoDetailsOperatePic);
            viewHolder.weiBoDetailsOperatePraisePerson = (TextView) convertView.findViewById(R.id.weiBoDetailsOperatePraiseTimes);
            viewHolder.reply_attach = (ListView) convertView.findViewById(R.id.reply_attach_listView);
            viewHolder.personPraise.setOnClickListener(this);
            viewHolder.personReply.setOnClickListener(this);
            viewHolder.personPhoto.setOnClickListener(this);
            viewHolder.weiBoDetailsOperatePraisePerson.setOnClickListener(this);
            viewHolder.personPraise.setTag(R.id.tag_bean, weiBoDetailsOperateListItemBeans.get(position)); //赞
            viewHolder.personReply.setTag(R.id.tag_bean, weiBoDetailsOperateListItemBeans.get(position)); //回复
            viewHolder.personPhoto.setTag(R.id.tag_bean, weiBoDetailsOperateListItemBeans.get(position)); //回复

            viewHolder.weiBoDetailsOperatePraisePerson.setTag(R.id.tag_bean, weiBoDetailsOperateListItemBeans.get(position)); //被赞次数
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if (bean.getHas_attach() == 1) {
            try {
                Logger.e("--list--" + ListUtil.isNullOrEmpty(bean.getAttachBeanList()));
                Logger.e(bean.getAttachBeanList().toString());
            } catch (Exception e) {
                Logger.e(e.getLocalizedMessage(), e);
            }


            AttachAdapter attachAdapter = new AttachAdapter(bean.getAttachBeanList(), context);
            viewHolder.reply_attach.setAdapter(attachAdapter);

        }
        viewHolder.personName.setText(bean.getUser_info().getUname());

        if (bean.getUser_info().getAvatar_middle() != null) {
            ImageLoaderManager.getInstance().disPlayImage(bean.getUser_info().getAvatar_middle(), viewHolder.personPhoto);
//            Picasso.with(context).load(bean.getUser_info().getAvatar_middle()).into(viewHolder.personPhoto);
        } else {
//            ImageLoaderManager.getInstance().disPlayImage("",viewHolder.personPhoto);
            Picasso.with(context).load(R.drawable.ic_launcher).into(viewHolder.personPhoto);
        }

        viewHolder.personContent.setText(StaticUtils.dealWithString(bean.getContent(), context));
        viewHolder.personContent.setClickable(true);
        viewHolder.personContent.setMovementMethod(LinkMovementMethod.getInstance());
        viewHolder.personContent.setFocusable(false);

        try {
//            String dateString = DateTools.formatDateWithSecondSince1970("MM月dd日 HH:mm", Long.parseLong(bean.getUser_info().getCtime()) * 1000L);
//            String dateString = DateTools.getNewDate(bean.getCtime());
            String dateString = DateTools.getNewDateFormat(bean.getCtime());
            viewHolder.personTime.setText(dateString);
        } catch (Exception e) {

        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weiBoDetailsOperatePraiseTimes:
                WeiboCommentListModel operateListItemBean = (WeiboCommentListModel) view.getTag(R.id.tag_bean);
                IntentTool.startPraiseReplyPerson(context, operateListItemBean.getRow_id());
                break;
            case R.id.weiBoDetailsOperatePraise:
                WeiboCommentListModel weiboCommentListModel = (WeiboCommentListModel) view.getTag(R.id.tag_bean);
                UserController.getInstance().praiseWeiBo(weiboCommentListModel.getRow_id(), new PraiseResult());
                break;
            case R.id.weiBoDetailsOperateReply:
                WeiboCommentListModel weiboCommentListModelReply = (WeiboCommentListModel) view.getTag(R.id.tag_bean);
                IntentTool.replyPage(context, 5, weiboCommentListModelReply);
                break;

            case R.id.weiBoDetailsOperatePic:
                WeiboCommentListModel w = (WeiboCommentListModel) view.getTag(R.id.tag_bean);
                IntentTool.startContactDetailActivityByName((Activity) context, w.getUser_info().getUname());
                break;
        }
    }

    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public TextView personContent; //显示相关内容
        public TextView personTime; //显示时间
        public TextView personReply; //回复
        public TextView personPraise; //赞
        public ImageView personPhoto;
        public TextView weiBoDetailsOperatePraisePerson;
        public ListView reply_attach;
    }

    public class LoadLener implements FileDownListener {
        String ex;

        public LoadLener(String ex) {
            this.ex = ex;
        }

        @Override
        public void starDownLoad() {
            dialog.show();
        }

        @Override
        public void endDownLoad(File file, boolean isSuess) {
            dialog.dismiss();
            if (isSuess) {
                Logger.e("下载完成");
                try {
                    context.startActivity(FileIntent.getOfficeIntent(file.getPath(), ex));
                } catch (Exception e) {
                    ToastTool.show("没有程序来支持附件的打开！请到相关市场下载相关office 程序！");
                }

            }
        }


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
