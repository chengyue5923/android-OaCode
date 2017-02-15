package com.idxk.mobileoa.android.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.FreshListAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.pullllistview.XListView;
import com.idxk.mobileoa.config.constant.Constant;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.exception.NoDataException;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class FreshFragment extends BaseListFragment implements MainTitleView.OnTitleClick,
        AdapterView.OnItemClickListener, View.OnClickListener, XListView.IXListViewListener {

    private static FreshFragment instance;
    int page = 1;
    String currentType = "";
    private XListView freshListView;
    private List<FreshListModel> freshLists;
    private FreshListAdapter freshListAdapter;
    private PopupWindow popupWindow;
    private MainTitleView title;

    public static FreshFragment getInstance() {
        if (instance == null) {
            instance = new FreshFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh, container, false);
        title = ((MainTitleView) view.findViewById(R.id.fresh_title_textview));
        title.setOnTitleClickLisener(this);
        freshListView = (XListView) view.findViewById(R.id.fresh_listview);
        freshListView.setPullLoadEnable(true);
        freshListView.setOnItemClickListener(this);
        freshListView.setXListViewListener(this);
        freshLists = new ArrayList<>();
        if (freshLists.size() < 1) {
            freshListView.setVisibility(View.GONE);
        }
        freshListAdapter = new FreshListAdapter(getActivity(), freshLists);
        freshListView.setAdapter(freshListAdapter);
//        loadData(true,"");

        loadData(true, currentType);
        return view;
    }

    private void initData() {

    }

    @Override
    public void onRefresh() {
        loadData(true, currentType);
//        UserController.getInstance().getFreshList("", new ViewCallBack());

    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if ("true".equals(PreferceManager.getInsance().getValueBYkey(getActivity(), "isRefresh"))) {
            PreferceManager.getInsance().saveValueBYkey("isRefresh", "false", getActivity());
            loadData(true, currentType);
        } else {
            loadData(false, currentType);
        }

//        UserController.getInstance().getFreshList("", new ViewCallBack());
    }

    @Override
    public void onLoadMore() {
        page++;
        loadData(false, currentType);
    }

    private void loadData(boolean refresh, String classify) {
        if (refresh) {
            setFreshStatues();
        }
        if (classify.equals("mywork")) {
            UserController.getInstance().getMySendWorkList(new ViewCallBack(true), page);
            return;
        }
        UserController.getInstance().getFreshList(page, classify, new ViewCallBack(refresh));
    }

    @Override
    public void clickLeft() {
        LayoutInflater mLayoutInflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        ViewGroup menuView = (ViewGroup) mLayoutInflater.inflate(
                R.layout.popupwindow_fresh_layout, null, true);
        menuView.findViewById(R.id.fresh_sendShare).setOnClickListener(this);
        menuView.findViewById(R.id.fresh_sendCommand).setOnClickListener(this);
        menuView.findViewById(R.id.fresh_sendDiary).setOnClickListener(this);
        menuView.findViewById(R.id.fresh_sendExamine).setOnClickListener(this);
        popupWindow = new PopupWindow(menuView, Common.screenWidth(getActivity()) / 4,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(false);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int xDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
        int yDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, dm);
//        popupWindow.showAtLocation(title.getLeftView(), Gravity.TOP | Gravity.LEFT, xDistance, yDistance);
        popupWindow.showAsDropDown(title.getLeftView(), 5, 25);
    }

    @Override
    public void clickRight() {
        IntentTool.searchPage(getActivity(), IConstant.SEARCHCONTENT);
    }

    @Override
    public void clickCenterTitle() {
        LayoutInflater mLayoutInflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        ViewGroup menuView = (ViewGroup) mLayoutInflater.inflate(
                R.layout.popupwindow_freshtitle_layout, null, true);

        menuView.findViewById(R.id.freshTitleShare).setOnClickListener(this);
        menuView.findViewById(R.id.freshTitleCommand).setOnClickListener(this);
        menuView.findViewById(R.id.freshTitleDiary).setOnClickListener(this);
        menuView.findViewById(R.id.freshTitleExamine).setOnClickListener(this);
        menuView.findViewById(R.id.freshTitleAll).setOnClickListener(this);
        menuView.findViewById(R.id.freshTitleiSend).setOnClickListener(this);

        popupWindow = new PopupWindow(menuView, Common.screenWidth(getActivity()) / 3, WindowManager.LayoutParams.WRAP_CONTENT,
                true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(false);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int yDistance = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, dm);
        popupWindow.showAtLocation(title, Gravity.TOP | Gravity.CENTER, 0, yDistance);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        try {
            FreshListModel freshListModel = (FreshListModel) freshListAdapter.getItem(position - 1);
            IntentTool.weiboDetailsPage(getActivity(), freshListModel, freshListModel.getChannel_id());
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    private void setFreshStatues() {
        page = 1;
        freshListView.setPullLoadEnable(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fresh_sendShare:
                popupWindow.dismiss();
                IntentTool.sendSharePage(getActivity());
                break;
            case R.id.fresh_sendCommand:
                popupWindow.dismiss();
                IntentTool.startSendCommandActivity(getActivity());
                break;
            case R.id.fresh_sendDiary:
                popupWindow.dismiss();
                IntentTool.sendDiaryPage(getActivity());
                break;
            case R.id.fresh_sendExamine:


                IntentTool.startApprovalTypesActivity(getActivity());
                popupWindow.dismiss();
//                IntentTool.startSendExamineActivity(getActivity());
                break;
            case R.id.freshTitleAll:
                setFreshStatues();
                popupWindow.dismiss();
                title.setCenter("全部");
                loadData(true, "");
                refreshPostion();
                currentType = "";
//                UserController.getInstance().getFreshList("", new ViewCallBack());
                break;
            case R.id.freshTitleShare:
                setFreshStatues();
                popupWindow.dismiss();
                title.setCenter("分享");
                loadData(true, "share");
                currentType = "share";
                refreshPostion();

//                UserController.getInstance().getFreshList("share", new ViewCallBack());
                break;
            case R.id.freshTitleDiary:
                popupWindow.dismiss();
                setFreshStatues();
                title.setCenter("日志");
                loadData(true, "worklog");
                currentType = "worklog";
                refreshPostion();

//                UserController.getInstance().getFreshList("worklog", new ViewCallBack());
                break;
            case R.id.freshTitleCommand:
                popupWindow.dismiss();
                title.setCenter("指令");
                loadData(true, "order");
                currentType = "order";
                refreshPostion();

//                UserController.getInstance().getFreshList("order", new ViewCallBack());
                break;
            case R.id.freshTitleExamine:
                popupWindow.dismiss();
                setFreshStatues();
                title.setCenter("审批");
                loadData(true, "approval");
                currentType = "approval";
                refreshPostion();

//                UserController.getInstance().getFreshList("approval", new ViewCallBack());
                break;
            case R.id.freshTitleiSend:
                popupWindow.dismiss();
                title.setCenter("我发出的");
                loadData(true, "mywork");
                currentType = "mywork";
                setFreshStatues();
                refreshPostion();

                break;

        }
    }


    @Override
    public void tryAgin(View listView) {
        loadData(false, currentType);
    }

    private void refreshPostion() {
        try {
            freshListAdapter.notifyDataSetChanged();
            freshListView.setSelection(0);
        } catch (Exception e) {

        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public class ViewCallBack implements ViewNetCallBack {
        boolean fresh;

        public ViewCallBack(boolean fresh) {
            this.fresh = fresh;
        }

        @Override
        public void onConnectStart() {

        }

        @Override
        public void onConnectEnd() {
            freshListView.stopRefresh();
            freshListView.stopLoadMore();
        }

        @Override
        public void onFail(Exception e) {
//            ToastTool.show(getResources().getString(R.string.loginFaile));
            dealException(e, freshListAdapter, freshListView);
        }

        @Override
        public void onData(Serializable result, boolean b, Object o) {
            Logger.e("Page fresh" + Thread.currentThread().getName());
            freshLists = (List<FreshListModel>) result;
            if (freshLists.size() < Constant.PAGE_LIMITE) {
                freshListView.setPullLoadEnable(false);
            }
            freshListView.setVisibility(View.VISIBLE);
            if (fresh) {
                freshListAdapter.setData(freshLists);
            } else {
                freshListAdapter.appendData(freshLists);
            }
            if (lvNull(freshListAdapter) && b) {
                onFail(new NoDataException());
            }

        }
    }
}
