package com.idxk.mobileoa.android.ui.views.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.dialog.factory.DialogFacory;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.utils.common.android.FileIntent;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.net.callback.FileDownListener;
import com.idxk.mobileoa.utils.net.connect.http.file.LoadFileThread;

import java.io.File;
import java.util.List;

/**
 * Created by lenovo on 2015/6/11.
 */
public class AttachAdapter extends BaseAdapter {
    Context context;
    private List<AttachBean> attachBeanList;
    private LayoutInflater inflater;
    private Dialog dialog;

    public AttachAdapter(List<AttachBean> attachBeanList, Context context) {
        this.attachBeanList = attachBeanList;
        this.inflater = LayoutInflater.from(context);
        dialog = DialogFacory.getInstance().createRunDialog(context);
        this.context = context;
    }


    public void setRes(List<AttachBean> attachBeanList) {
        this.attachBeanList = attachBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (attachBeanList != null) {
            return attachBeanList.size();
        }
        return 0;
    }

    @Override
    public AttachBean getItem(int position) {
        return attachBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_item_attach, null);
            holder = new ViewHolder();
            holder.attach_name = (TextView) convertView.findViewById(R.id.attach_name);
            holder.attach_size = (TextView) convertView.findViewById(R.id.attach_size);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttachBean attachBean = attachBeanList.get(position);
                new LoadFileThread(new LoadLener(attachBean.getExtension())).execute(attachBean.getAttach_url(), FileListAdapter.getInnerSd() + File.separator + "zmt" + File.separator + attachBean.getAttach_name());

            }
        });
        holder.attach_size.setText(attachBeanList.get(position).getSize());
        holder.attach_name.setText(attachBeanList.get(position).getAttach_name());
        return convertView;
    }

    private class ViewHolder {
        TextView attach_size;
        TextView attach_name;
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
}
