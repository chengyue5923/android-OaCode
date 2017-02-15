package com.idxk.mobileoa.android.ui.activity;

import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.FileListAdapter;
import com.idxk.mobileoa.android.ui.views.dialog.factory.DialogFacory;
import com.idxk.mobileoa.android.ui.views.widget.HomeCommonTitle;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.model.bean.FileListsBean;
import com.idxk.mobileoa.utils.common.android.FileIntent;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.net.callback.FileDownListener;
import com.idxk.mobileoa.utils.net.connect.http.file.LoadFileThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/4/1.
 */
public class FileShowListActivity extends BaseActivity implements AdapterView.OnItemClickListener,
        HomeCommonTitle.OnTitleClick{

    ListView listView;
    FileListAdapter dadpter;
    Dialog dialog ;
    HomeCommonTitle title;
    @Override
    protected void initView() {

        setContentView(R.layout.activity_fileshow);
        listView = (ListView)findViewById(R.id.items);
        listView.setOnItemClickListener(this);
        dadpter = new FileListAdapter(this);
        listView.setAdapter(dadpter);
        title =(HomeCommonTitle) id2v(R.id.appAlarm_mainTitle);
        title.setOnTitleClickLisener(this);
        dialog=DialogFacory.getInstance().createRunDialog(this);

    }
    List<AttachBean> listres;
    @Override
    protected void initData() {
        listres= new ArrayList<>();
        FileListsBean bean =  (FileListsBean)getIntent().getSerializableExtra("files");
        if (bean==null|| ListUtil.isNullOrEmpty(bean.getList())){
            return;
        }

        listres = bean.getList();

        dadpter.setRes(listres);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AttachBean bean = listres.get(position);
        new LoadFileThread(new LoadLener(bean.getExtension())).execute(bean.getAttach_url(),FileListAdapter.getInnerSd() + File.separator + "zmt"+ File.separator+bean.getAttach_name());

    }

    public class LoadLener implements FileDownListener{
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
            if (isSuess){
                Logger.e("下载完成");
                try {
                    startActivity(FileIntent.getOfficeIntent(file.getPath(), ex));
                }catch (Exception e){
                    ToastTool.show("没有程序来支持附件的打开！请到相关市场下载相关office 程序！");
                }

            }
        }


    }

    @Override
    public void clickLeft() {
        finish();
    }


}
