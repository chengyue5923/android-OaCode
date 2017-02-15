package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.SearchUserInfoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2015/3/23.
 */
public class SearchUserAdapter extends BaseAdapter implements View.OnClickListener {
    private LayoutInflater mLayoutInflater;
    private List<SearchUserInfoModel> searchUserInfoModels;
    private ViewHolder viewHolder;
    private Context context;


    public SearchUserAdapter(Context context, List<SearchUserInfoModel> searchUserInfoModels) {
        mLayoutInflater = LayoutInflater.from(context);
        this.searchUserInfoModels = searchUserInfoModels;
        this.context = context;
    }

    public void setData(List<SearchUserInfoModel> searchUserInfoModels) {
        this.searchUserInfoModels = searchUserInfoModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (searchUserInfoModels != null)
            return searchUserInfoModels.size();

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return searchUserInfoModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_item_searchuser, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.searchUser_userPic = (ImageView) convertView.findViewById(R.id.searchUser_userPic);
            viewHolder.userDepartment = (TextView) convertView.findViewById(R.id.searchUser_department);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.searchUser_name);
            viewHolder.msgIcon = (ImageView) convertView.findViewById(R.id.msgIcon);
            viewHolder.phoneIcon = (ImageView) convertView.findViewById(R.id.callPhone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SearchUserInfoModel bean = searchUserInfoModels.get(position);
        viewHolder.userName.setText(bean.uname);
        if (bean.avatar_middle != null) {
            Picasso.with(context).load(bean.avatar_middle).into(viewHolder.searchUser_userPic);
        } else {
            Picasso.with(context).load(R.drawable.app).into(viewHolder.searchUser_userPic);
        }


        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phoneIcon:

                break;
        }
    }

    public static final class ViewHolder {
        public ImageView searchUser_userPic;
        public TextView userName;
        public TextView userDepartment;
        public ImageView phoneIcon;
        public ImageView msgIcon;
    }

}
