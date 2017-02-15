package com.idxk.mobileoa.android.ui.views.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.dialog.factory.DialogFacory;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.FileDownListener;
import com.idxk.mobileoa.utils.net.connect.http.file.LoadFileThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/1.
 */
public class FileListAdapter extends BaseAdapter implements View.OnClickListener{

    private LayoutInflater inflater;

    List<AttachBean>  names;
    public FileListAdapter(Context context) {
        names= new ArrayList<>();
        DialogFacory.getInstance().createRunDialog(context);
        this.inflater = LayoutInflater.from(context);

    }
    public void setRes(List<AttachBean>  list){
        names = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_filelist_item, null);
            holder = new ViewHolder();
            holder.content = (TextView)convertView.findViewById(R.id.item);
            convertView.setTag(holder);

        }
        holder = (ViewHolder)convertView.getTag();
        holder.content.setText(names.get(position).getAttach_name());
//        holder.content.setOnClickListener(this);
        holder.content.setTag(position);
        return convertView;
    }

    private class ViewHolder {
        TextView content;
    }


    @Override
    public void onClick(View v) {
        try {
            int position = Integer.parseInt(v.getTag().toString());
            AttachBean bean = names.get(position);
            new LoadFileThread(new LoadLinsener()).execute(bean.getAttach_url(),getInnerSd() + File.separator + "zmt"+bean.getAttach_name());

        }catch (Exception e){

        }




    }

    Dialog dialog ;
    public class  LoadLinsener implements FileDownListener {

        public LoadLinsener() {


        }

        @Override
        public void starDownLoad() {
            dialog.show();
        }

        @Override
        public void endDownLoad(File file, boolean isSuess) {
            dialog.dismiss();
            if (isSuess){
                Logger.e("下载完成");
            }

        }
    }
    public static String getInnerSd() {
        if (("mounted".equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory().canWrite()) && (Environment.getExternalStorageDirectory().canRead()))
            return Environment.getExternalStorageDirectory().getPath();
        return null;
    }
}
