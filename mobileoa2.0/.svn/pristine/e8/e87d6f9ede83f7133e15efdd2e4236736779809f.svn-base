package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.android.UITool;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StringTools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/4/8.
 */
public class ShowImageGvAdapter extends BaseAdapter{


    List<String> paths;
    Context context;

    HashMap<String,Bitmap> hashMap;


    public ShowImageGvAdapter(Context context) {
        this.context = context;
        paths  = new ArrayList<>();
        hashMap = new HashMap<>();

    }



    public void appendData(String path){
        Logger.e("---path-"+path);
        Logger.e("00size=" + paths.size());
        if (paths.size()>10){
            ToastTool.show("选择的图片不能大于十张");
            return;
        }
        paths.add(path);
        File file = new File(path);
        if (file.length() > 1024 * 100) {
            try {
                hashMap.put(path, Common.getimage(path));

            }catch (Exception e){
                hashMap.put(path, BitmapFactory.decodeFile(path));
            }
        }else{
            hashMap.put(path, BitmapFactory.decodeFile(path));
        }
        ListUtil.removeDuplicate(paths);
        paths.remove("");
        hashMap.remove("");
        hashMap.put("", null);
        paths.add("");
        notifyDataSetChanged();
    }

    public void appendData(String path,Bitmap bm){
        Logger.e("---path-"+path);
        if (StringTools.isNullOrEmpty(path)){
            path= UUID.randomUUID().toString();
        }
        if (paths.size()>10){
            ToastTool.show("选择的图片不能大于十张");
            return;
        }
        paths.add(path);

        Logger.e("00path=" + path);
        Logger.e("00bm=" + bm);
        Logger.e("00size=" + paths.size());

        hashMap.put(path, bm);
        ListUtil.removeDuplicate(paths);
        paths.remove("");
        hashMap.remove("");
        hashMap.put("", null);
        paths.add("");

        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public Object getItem(int position) {
        return paths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView  = UITool.getView(context,R.layout.adapter_image_show,null);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(R.id.tag_showAdapter_hold,viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag(R.id.tag_showAdapter_hold);
        }
        Logger.e("paths="+paths.get(position));
        if (hashMap.get(paths.get(position))!=null){
            viewHolder.imageView .setImageBitmap(hashMap.get(paths.get(position)));


            convertView.setTag(true);
        }else{
            convertView.setTag(false);
            viewHolder.imageView .setImageResource(R.drawable.extend_click);
        }
        return convertView;
    }

    /**
     * 存放列表项控件句柄
     */
    private class ViewHolder {
        public ImageView imageView;
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();


        Logger.e("回收了-");
    }
}
