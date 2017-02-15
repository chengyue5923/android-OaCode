package com.idxk.mobileoa.android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.DepartmentAdapter;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.exception.NoDataException;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.DepartMentBean;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/3/16.
 */
public class DepartMentFragment extends BaseV4ListFragment implements AdapterView.OnItemClickListener, ViewNetCallBack {
//    Dialog loadDialog;
    List<DepartMentBean> departMentBeans;
    private ListView listView;
    private DepartmentAdapter departmentAdapter;

    private ColleagueFragment.ISelectedColleague selectedColleague;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedColleague = (ColleagueFragment.ISelectedColleague) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scopedepartment, container, false);
        listView = (ListView) view.findViewById(R.id.scopeDepartment);
        listView.setOnItemClickListener(this);
        departmentAdapter = new DepartmentAdapter(getActivity(), IConstant.SCOPEDEPARTMENT);
        listView.setAdapter(departmentAdapter);
//        loadDialog = DialogFacory.getInstance().createRunDialog(getActivity());
        initData();
        return view;
    }


    protected void initData() {
        ContactController.getInstance().getDepartMentList(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ImageView imageView = (ImageView) view.findViewById(R.id.department_ischecked);
        DepartMentBean departMentBean = (DepartMentBean) departmentAdapter.getItem(position);
        departMentBean.isChecked = !departMentBean.isChecked;
        if (departMentBean.isChecked) {
            imageView.setImageResource(R.drawable.btn_check_sel);
        } else {
            imageView.setImageResource(R.drawable.btn_check_nor);
        }
        departmentAdapter.notifyDataSetChanged();
        ContactBean contactBean = new ContactBean();
        contactBean.setUid("d_" + departMentBean.getDepartment_id());
        contactBean.setUname(departMentBean.getName());
        contactBean.isChecked = departMentBean.isChecked;
        contactBean.isDepartment = true;
        selectedColleague.freshSelectedColleague(contactBean, String.valueOf(position));
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
        dealException(e,departmentAdapter,listView);

    }

    @Override
    public void onData(Serializable result,boolean b,Object o) {
        departMentBeans = (List<DepartMentBean>) result;
        departmentAdapter.setResult(departMentBeans);
        if (lvNull(departmentAdapter)&&b){
            onFail(new NoDataException());
        }
    }

    @Override
    public void tryAgin(View listView) {
        ContactController.getInstance().getDepartMentList(this);
    }
}
