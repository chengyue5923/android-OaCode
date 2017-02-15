package com.idxk.mobileoa.android.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ContactListAdapter;
import com.idxk.mobileoa.android.ui.views.adapter.FreshListAdapter;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.FreshListModel;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2015/3/8.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, ViewNetCallBack,
        TextView.OnEditorActionListener {
    private TextView search_cancel;
    private EditText searchContent;

    private ListView freshListView;

    private ListView colleagueListView;
    private FreshListAdapter freshListAdapter; //搜索内容

    private ContactListAdapter searchUserAdapter; //搜索同事

    private int searchType;

    private List resultList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_search);
        search_cancel = (TextView) id2v(R.id.search_cancel);
        search_cancel.setOnClickListener(this);
        searchContent = (EditText) id2v(R.id.searchContent);
        freshListView = (ListView) id2v(R.id.search_listView);
        colleagueListView = (ListView) id2v(R.id.search_colleagueListView);
        searchContent.setOnEditorActionListener(this);
        colleagueListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                IntentTool.startContactDetailActivityByBean(SearchActivity.this, (ContactBean) resultList.get(position));
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        searchType = intent.getIntExtra("searchType", 0);
        if (searchType == IConstant.SEARCHCOLLEAGUE) {
            searchContent.setHint("搜索同事");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_cancel:
                this.finish();
                break;
        }
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
    public void onData(Serializable result, boolean b, Object o) {

        if (result != null) {
            resultList = (List) result;
            if (resultList.get(0) instanceof FreshListModel) {
                colleagueListView.setVisibility(View.GONE);
                freshListView.setVisibility(View.VISIBLE);
                freshListAdapter = new FreshListAdapter(this, resultList);
                freshListView.setAdapter(freshListAdapter);
            } else if (resultList.get(0) instanceof ContactBean) {
                Logger.e(resultList.get(0).toString());
                colleagueListView.setVisibility(View.VISIBLE);
                freshListView.setVisibility(View.GONE);
                HashMap<String, Integer> alphaIndexer = new HashMap<>();
                searchUserAdapter = new ContactListAdapter(this, alphaIndexer, IConstant.CONTACT);
                searchUserAdapter.setHideTitle(false);
                colleagueListView.setAdapter(searchUserAdapter);
                searchUserAdapter.setRes(resultList);
            }
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (searchType == IConstant.SEARCHCONTENT) {
            UserController.getInstance().searchWeiBoContent(searchContent.getText().toString(), SearchActivity.this, 1);
        } else if (searchType == IConstant.SEARCHCOLLEAGUE) {
            UserController.getInstance().searchColleague(searchContent.getText().toString(), SearchActivity.this, 0);
        }
        return false;
    }
}
