package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.DepartmentAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.DepartMentBean;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.threads.ContactDbThread;

import java.io.Serializable;
import java.util.List;

/**
 * 按照部门 选择的 activiy
 */
public class DepartmentSelectionActivity extends BaseListViewActivity implements MainTitleView.OnTitleClick,
        ViewNetCallBack, AdapterView.OnItemClickListener {
    MainTitleView titleView;
    ListView listView;
    DepartmentAdapter adapter;
//    Dialog loadDialog;
    List<DepartMentBean> beans;

    @Override
    protected void initView() {

        setContentView(R.layout.activity_depart_select);
        titleView = (MainTitleView) findViewById(R.id.mainTitle);
        listView = (ListView) findViewById(R.id.list);
        titleView.setOnTitleClickLisener(this);
        adapter = new DepartmentAdapter(this, IConstant.CONTACTDEPARTMENT);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    private void loadFromLocal(){
        beans = ContactController.getInstance().getDpartment();
        setAdapter(beans);
    }

    @Override
    protected void initData() {
//        loadFromLocal();
        ContactController.getInstance().getDepartMentList(this);
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
    public void onConnectStart() {
//        loadDialog.show();
    }

    @Override
    public void onConnectEnd() {
//        loadDialog.dismiss();
    }

    @Override
    public void onFail(Exception e) {
        loadFromLocal();
    }

    @Override
    public void onData(Serializable result,boolean b,Object o) {
        new ContactDbThread(result,1){
            @Override
            public void onView() {
                loadFromLocal();
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IntentTool.startDepartContectActivity(this, beans.get(position).getId(), beans.get(position).getName());
    }

    @Override
    public void tryAgin(View view) {

    }

    private void setAdapter(List<DepartMentBean> beans) {
        adapter.setResult(beans);
    }
}
