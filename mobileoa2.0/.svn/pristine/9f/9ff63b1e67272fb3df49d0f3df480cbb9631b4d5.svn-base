package com.idxk.mobileoa.android.ui.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.PraiseListModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.connect.http.file.ImageLoaderManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2015/3/12.
 */
public class ShareScopeAdapter extends BaseAdapter implements View.OnClickListener {
    private LayoutInflater mLayoutInflater;
    private List<PraiseListModel> shareScopeListItemBeans;
    private Context context;
    private ViewHolder viewHolder;

    public ShareScopeAdapter(Context context, List<PraiseListModel> shareScopeListItemBeans) {
        mLayoutInflater = LayoutInflater.from(context);
        this.shareScopeListItemBeans = shareScopeListItemBeans;
        this.context = context;
    }


    public void setData(List<PraiseListModel> praiseListModels) {
        this.shareScopeListItemBeans = praiseListModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (shareScopeListItemBeans != null)
            return shareScopeListItemBeans.size();

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return shareScopeListItemBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_sharescope, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) convertView.findViewById(R.id.shareScopePersonName);
            viewHolder.personPhoto = (ImageView) convertView.findViewById(R.id.shareScopePic);
            viewHolder.personPhoto.setOnClickListener(this);
            viewHolder.personPhoto.setTag(R.id.tag_bean, shareScopeListItemBeans.get(position)); //赞

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PraiseListModel bean = shareScopeListItemBeans.get(position);
        if (bean.getUserinfo().getAvatar_middle() != null) {
            ImageLoaderManager.getInstance().disPlayImage(bean.getUserinfo().getAvatar_middle(), viewHolder.personPhoto);
//            Picasso.with(context).load(bean.getUserinfo().getAvatar_middle()).into(viewHolder.personPhoto);
        } else {
            Picasso.with(context).load(R.drawable.ic_launcher).into(viewHolder.personPhoto);
        }
        viewHolder.personName.setText(bean.getUserinfo().getUname());
        return convertView;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.shareScopePic) {
            PraiseListModel w = (PraiseListModel) v.getTag(R.id.tag_bean);
            IntentTool.startContactDetailActivityByName((Activity) context, w.getUserinfo().getUname());
        }

    }

    public static final class ViewHolder {
        public TextView personName; //显示名字相关信息
        public ImageView personPhoto; //头像
        public TextView departMent; //部门
    }
}
