package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.connect.http.file.ImageLoaderManager;
import com.mogujie.tt.DB.entity.GroupEntity;
import com.mogujie.tt.config.DBConstant;
import com.mogujie.tt.imservice.entity.RecentInfo;
import com.mogujie.tt.utils.DateUtil;

import java.util.List;

/**
 * Created by lenovo on 2015/5/29.
 */
public class ChatPersonAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<RecentInfo> chatPersonModels;
    private Context context;
    private ViewHolder viewHolder;

    public ChatPersonAdapter(Context context, List<RecentInfo> chatPersonModels) {
        mLayoutInflater = LayoutInflater.from(context);
        this.chatPersonModels = chatPersonModels;
        this.context = context;
    }

    public void setData(List<RecentInfo> lists) {
        this.chatPersonModels.clear();
        this.chatPersonModels = lists;
        Logger.e(String.valueOf(lists.size()));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (chatPersonModels != null)
            return chatPersonModels.size();
        return 0;
    }

    @Override
    public RecentInfo getItem(int position) {
//        RecentInfo info = chatPersonModels.get(position);
//        Logger.e("--getEntityId--"+info.getEntityId());
        return chatPersonModels.get(position);
    }

    /**
     * 更新单个RecentInfo 屏蔽群组信息
     */
    public void updateRecentInfoByShield(GroupEntity entity) {
        String sessionKey = entity.getSessionKey();
        for (RecentInfo recentInfo : chatPersonModels) {
            if (recentInfo.getSessionKey().equals(sessionKey)) {
                int status = entity.getStatus();
                boolean isFor = status == DBConstant.GROUP_STATUS_SHIELD;
                recentInfo.setForbidden(isFor);
                notifyDataSetChanged();
                break;
            }
        }
    }

    /**
     * 置顶状态的更新  not use now
     */
    public void updateRecentInfoByTop(String sessionKey, boolean isTop) {
        for (RecentInfo recentInfo : chatPersonModels) {
            if (recentInfo.getSessionKey().equals(sessionKey)) {
                recentInfo.setTop(isTop);
                notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View itemView, ViewGroup parent) {
        RecentInfo recentInfo = chatPersonModels.get(i);
        if (itemView == null) {
            itemView = mLayoutInflater.inflate(R.layout.adapter_item_chatperson, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personPhoto = (ImageView) itemView.findViewById(R.id.personPhoto);
            viewHolder.personName = (TextView) itemView.findViewById(R.id.personName);
            viewHolder.content = (TextView) itemView.findViewById(R.id.content);
            viewHolder.upDateTime = (TextView) itemView.findViewById(R.id.chatTime);
            viewHolder.msgCount = (TextView) itemView.findViewById(R.id.unReadMessage);
            itemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) itemView.getTag();
        }

        int unReadCount;

        viewHolder.personName.setText(recentInfo.getName());
        viewHolder.content.setText(recentInfo.getLatestMsgData());
        viewHolder.upDateTime.setText(DateUtil.getSessionTime(recentInfo.getUpdateTime()));
        unReadCount = recentInfo.getUnReadCnt();
        if (unReadCount > 0) {
            String strCountString = String.valueOf(unReadCount);
            if (unReadCount > 99) {
                strCountString = "99+";
            }
            viewHolder.msgCount.setVisibility(View.VISIBLE);
            viewHolder.msgCount.setText(strCountString);
        } else {
            viewHolder.msgCount.setVisibility(View.GONE);
        }
        //图片地址有问题暂时用默认图片

        if (DBConstant.SESSION_TYPE_SINGLE == recentInfo.getSessionType()) {
            String avatarUrl = null;
            if (null != recentInfo.getAvatar() && recentInfo.getAvatar().size() > 0) {
                avatarUrl = recentInfo.getAvatar().get(0);

            }
            if (!StringTools.isNullOrEmpty(avatarUrl) && avatarUrl.startsWith("http")) {
                ImageLoaderManager.getInstance().disPlayImage(viewHolder.personPhoto, avatarUrl);
            } else {
                viewHolder.personPhoto.setImageResource(R.drawable.tt_default_user_portrait_corner);
            }
        } else {
            viewHolder.personPhoto.setImageResource(R.drawable.group_default);
        }

        return itemView;

    }


    public static final class ViewHolder {
        public ImageView personPhoto;
        public TextView personName;
        public TextView content;
        public TextView upDateTime;
        public TextView msgCount;
    }
}
