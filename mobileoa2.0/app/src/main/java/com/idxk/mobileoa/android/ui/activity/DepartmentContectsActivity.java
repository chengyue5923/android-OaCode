package com.idxk.mobileoa.android.ui.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.activity.im.IMEvent;
import com.idxk.mobileoa.android.ui.activity.im.MessageEntity;
import com.idxk.mobileoa.android.ui.views.adapter.ContactListAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.android.ui.views.widget.MyLetterListView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.dao.MessageDao;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.logic.controller.EventManager;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.utils.cache.db.factory.DBFactory;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.StringTools;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Wesley on 2015/3/12.
 */
public class DepartmentContectsActivity extends BaseActivity implements MainTitleView.OnTitleClick
        , ContactListAdapter.ISelectedColleague, TextView.OnEditorActionListener {

    MainTitleView titleView;
    //    Dialog loadDialog;
    ListView list;
    ContactListAdapter adapter;
    MyLetterListView indexBar;
    String title;
    private EditText searchEt;
    private TextView overlay;
    private Handler overLayouHandler;
    private OverlayThread overlayThread;
    private WindowManager windowManager;
    private HashMap<String, Integer> alphaIndexer;//存放存在的汉语拼音首字母和与之对应的列表位置
    private List<ContactBean> contactBeanList;
    private Handler contactHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                adapter.setRes(contactBeanList);
            } catch (Exception E) {

            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(IMEvent event) {
        Logger.e("oevent" +event.getCount());

        adapter.notifyDataSetChanged();
    }
    protected void initEvent() {
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activitity_department_contacts);
        initEvent();
        titleView = (MainTitleView) findViewById(R.id.mainTitle);
        titleView.setOnTitleClickLisener(this);

        searchEt = (EditText) id2v(R.id.searchEt);
//        searchEt.setFocusable(false);
//        searchEt.setFocusableInTouchMode(false);
        searchEt.setOnFocusChangeListener(Common.onFocusAutoClearHintListener);
        searchEt.setOnTouchListener(Common.onTouchClearHintListener);

        searchEt.setOnEditorActionListener(this);
        searchEt.addTextChangedListener(new Wather());


        list = (ListView) findViewById(R.id.myListView);
        indexBar = (MyLetterListView) findViewById(R.id.sideBar);
//        loadDialog = DialogFacory.getInstance().createRunDialog(this);
        alphaIndexer = new HashMap<String, Integer>();
        adapter = new ContactListAdapter(this, alphaIndexer, IConstant.CONTACT);
        list.setAdapter(adapter);
        indexBar.setOnTouchingLetterChangedListener(new LetterListViewListener());

        initOverlay();
        overLayouHandler = new Handler();
        overlayThread = new OverlayThread();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentTool.startContactDetailActivity(DepartmentContectsActivity.this, contactBeanList.get(position).getUid());
            }
        });
    }

    private void loadData(final String name) {


        Runnable task = new Runnable() {
            @Override
            public void run() {
                contactBeanList = ContactController.getInstance().getContactsByDName(title, name);
                contactHandler.sendEmptyMessage(0);
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        titleView.setCenter(title);


        contactBeanList = new ArrayList<>();
        String json = getIntent().getStringExtra("json");

        if (!StringTools.isNullOrEmpty(json)){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){
                    String re=jsonArray.get(i).toString();
                    contactBeanList.add( GsonTool.jsonToEntity(re,ContactBean.class));
                }
                adapter.setRes(contactBeanList);

            }
            catch (Exception e){

            }
        }else{
            loadData("");
        }

    }

    private void initOverlay() {
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.view_overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
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
    public void freshSelectedColleague(String uname, String uid, boolean isChecked, int position) {

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        loadData(textView.getText().toString());
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        DBFactory.getInstance().close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        windowManager.removeView(overlay);
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }

    private class LetterListViewListener implements MyLetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                list.setSelection(position);
                overlay.setText(s);
                overlay.setVisibility(View.VISIBLE);
                overLayouHandler.removeCallbacks(overlayThread);
                overLayouHandler.postDelayed(overlayThread, 1500);
            }
        }

    }

    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }
    }

    public class Wather implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String string = s.toString();
            loadData(string);

        }
    }

}
