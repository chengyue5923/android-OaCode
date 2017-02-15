package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.DepartmentAdapter;
import com.idxk.mobileoa.android.ui.views.adapter.DepartmentSunAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.DepartMentBean;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.threads.ContactDbThread;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 按照部门 选择的 activiy
 */
public class DepartmentSunSelectionActivity extends BaseListViewActivity implements MainTitleView.OnTitleClick,
        AdapterView.OnItemClickListener {
    MainTitleView titleView;
    ListView listView;
    DepartmentSunAdapter adapter;
//    Dialog loadDialog;
    List<String> beans;
    HashMap hashMap ;
    @Override
    protected void initView() {

        setContentView(R.layout.activity_depart_select);
        titleView = (MainTitleView) findViewById(R.id.mainTitle);
        listView = (ListView) findViewById(R.id.list);
        titleView.setOnTitleClickLisener(this);
        adapter = new DepartmentSunAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        beans= new ArrayList<>();
        hashMap = new HashMap();

    }

    @Override
    public void tryAgin(View listView) {

    }
    JSONObject jsonObject ;
    private void loadFromLocal(){
        try {
             jsonObject =  new JSONObject(getIntent().getStringExtra("json"));
            HashMap hashMap =  GsonTool.jsonToHas(jsonObject.toString());

            Iterator iterator = hashMap.keySet().iterator();
            while(iterator.hasNext()) {

                beans.add(iterator.next().toString());
            }



        }catch (Exception e){

        }

        setAdapter(beans);
    }

    @Override
    protected void initData() {
        loadFromLocal();
    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickCenterTitle() {

    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            String string =jsonObject.get(beans.get(position)).toString();
            Logger.e("reslut = "+string);
            IntentTool.startDepartContectActivity(this,-1,beans.get(position),string);

        }catch (Exception e){
            Logger.e(e.getLocalizedMessage(),e);

        }

    }

    private void setAdapter(List<String> beans) {
        adapter.setResult(beans);
    }
}
