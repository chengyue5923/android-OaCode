package com.idxk.mobileoa.android.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.RecentAdapter;
import com.idxk.mobileoa.logic.manager.RecenteManager;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.RecentModel;
import com.idxk.mobileoa.utils.common.android.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/16.
 */
public class RecentFragment extends BaseV4ListFragment implements AdapterView.OnItemClickListener {

    private ListView recentListView;
    private RecentAdapter recentAdapter;
    private List<RecentModel> recentModels;
    private ColleagueFragment.ISelectedColleague selectedColleague;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        recentListView = (ListView) view.findViewById(R.id.recentListView);
        recentListView.setOnItemClickListener(this);
        recentAdapter = new RecentAdapter(getActivity());
        recentModels = new ArrayList<RecentModel>();
        recentAdapter.setData(recentModels);
        recentListView.setAdapter(recentAdapter);
        selectedColleague = (ColleagueFragment.ISelectedColleague) getActivity();
        loadData();
        return view;
    }

    private void loadData(){
        new LoadThread().execute();
    }
    private class LoadThread extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            Bundle bundle = RecentFragment.this.getArguments();
            boolean sendRange = bundle.getBoolean("sendRange",false);
            int sendType = bundle.getInt("type",0);
            Logger.e("woca-sendRange-"+sendRange);
            Logger.e("woca-sendType-"+sendType);
            if (sendRange){
                recentModels = RecenteManager.getInstance().quaryRes(sendType);
            }else{
                recentModels = RecenteManager.getInstance().quaryRes();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            recentAdapter.setData(recentModels);



            for (int i=0;i<recentModels.size();i++){
                RecentModel recentModel =recentModels.get(i);
                if (recentModel.isChecked()){
                    ContactBean contactBean = new ContactBean();
                    contactBean.setUid(recentModel.getUid());
                    contactBean.setUname(recentModel.getName());
                    contactBean.isChecked = recentModel.isChecked();
                    contactBean.isDepartment = false;
                    selectedColleague.freshSelectedColleague(contactBean, "recentFragment" + String.valueOf(i));
                }
            }


        }
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        RecentModel recentModel = (RecentModel) recentAdapter.getItem(i);
        if (recentModel.isChecked()) {
            recentModel.setChecked(false);
        } else {
            recentModel.setChecked(true);
        }
        recentAdapter.notifyDataSetChanged();
        ContactBean contactBean = new ContactBean();
        contactBean.setUid(recentModel.getUid());
        contactBean.setUname(recentModel.getName());
        contactBean.isChecked = recentModel.isChecked();
        contactBean.isDepartment = false;
        selectedColleague.freshSelectedColleague(contactBean, "recentFragment" + String.valueOf(i));
    }


    @Override
    public void tryAgin(View listView) {

    }
}
