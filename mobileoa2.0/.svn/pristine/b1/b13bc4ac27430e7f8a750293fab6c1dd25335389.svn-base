package com.idxk.mobileoa.android.ui.fragment;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.AppAdapter;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.logic.controller.AppListController;
import com.idxk.mobileoa.model.bean.AppListBea;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2015/3/4.
 */
public class AppCenterFragment extends Fragment implements ViewNetCallBack, AdapterView.OnItemClickListener {

    private static AppCenterFragment instance;
    GridView gv;
    AppAdapter adapter;
    View view;

    public AppCenterFragment() {
    }

    public static AppCenterFragment getInstance() {
        if (instance == null) {
            instance = new AppCenterFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_modify_app, null);

        return view;
    }


    private void initView(View view) {
        gv = (GridView) view.findViewById(R.id.gv);
        adapter = new AppAdapter(getActivity());
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(this);
        AppListController.getInstance().appList(this, new HashMap<String, Object>());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view);
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, boolean fromNet, Object o) {
        List<AppListBea> listBeas = (List<AppListBea>) result;
        Logger.e("size = " + listBeas.size());
        adapter.setRes(listBeas);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AppListBea bean = adapter.getItem(position);
        if (StringTools.isNullOrEmpty(bean.getApp_url())) {
            String url = bean.getUrl();
            Logger.e("getURL:" + url);
            if (bean.getUrl().contains("salary")) {
                String uname = PreferceManager.getInsance().getValueBYkey(getActivity(), "login_email");
                url = bean.getUrl() + "?uname=" + uname;

            }

            if (!url.startsWith("http")){
                url = UrlRes.getInstance().getUrlSever() + url;
            }

            String oauth_token = PreferceManager.getInsance().getValueBYkey(getActivity(), "oauth_token");
            String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(getActivity(), "oauth_token_secret");
            IntentTool.webViewPage(getActivity(),
                    url
                            + "&oauth_token=" + oauth_token + "&oauth_token_secret=" + oauth_token_secret, bean.getName());
        } else {
            if (!bean.getApp_url().contains("wiznote")) {
                return;
            }
            if (Common.installPackageCheck(getActivity(), "cn.wiz.zmt.note")) {
                ComponentName componentName = new ComponentName("cn.wiz.zmt.note", "cn.wiz.note.MainActivity");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
            }
            IntentTool.webViewPage(getActivity(),
                    getResources().getString(R.string.zhongMinTouUrl), "中民投笔记");

        }


    }
}
