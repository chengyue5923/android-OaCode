package com.idxk.mobileoa.android.ui.fragment;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ContactListAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MyLetterListView;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.utils.common.android.Common;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2015/3/16.
 */
public class ColleagueFragment extends Fragment implements MyLetterListView.OnTouchingLetterChangedListener, AdapterView.OnItemClickListener, TextView.OnEditorActionListener {
    public static ContactListAdapter adapter;
    private ListView list;
    private MyLetterListView indexBar;
    private List<ContactBean> beans;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.setRes(beans);
        }
    };
    private TextView overlay;
    private ISelectedColleague selectedColleague;
    /**
     * 昵称
     */
    private HashMap<String, Integer> alphaIndexer;//存放存在的汉语拼音首字母和与之对应的列表位置
    private Handler overLayouHandler;
    private OverlayThread overlayThread;
    private WindowManager windowManager;

    private EditText searchEditText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedColleague = (ISelectedColleague) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colleague, container, false);
        list = (ListView) view.findViewById(R.id.myListView);
        list.setOnItemClickListener(this);
        searchEditText = (EditText) view.findViewById(R.id.searchBar);
        Common.hidenSoftinput(searchEditText,getActivity());
        searchEditText.setOnEditorActionListener(this);
        searchEditText.addTextChangedListener(new Wather());
        alphaIndexer = new HashMap<String, Integer>();
        adapter = new ContactListAdapter(getActivity(), alphaIndexer, IConstant.SCOPE);
        list.setAdapter(adapter);
        indexBar = (MyLetterListView) view.findViewById(R.id.sideBar);
        indexBar.setOnTouchingLetterChangedListener(this);
        overLayouHandler = new Handler();
        overlayThread = new OverlayThread();
        initOverlay();
//        DBFactory.getInstance().setContext(getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData("");
    }

    private void loadData(final String name) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                beans = ContactController.getInstance().getConstacts(name);
                handler.sendEmptyMessage(0);
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void initOverlay() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        overlay = (TextView) inflater.inflate(R.layout.view_overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
    }

    @Override
    public void onStop() {
        super.onStop();
//        DBFactory.getInstance().close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeView(overlay);
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        if (alphaIndexer.get(s) != null) {
            int position = alphaIndexer.get(s);
            list.setSelection(position);
            overlay.setText(s);
            overlay.setVisibility(View.VISIBLE);
            overLayouHandler.removeCallbacks(overlayThread);
            overLayouHandler.postDelayed(overlayThread, 1500);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ContactBean contactBean = (ContactBean) adapter.getItem(position);
        contactBean.isChecked = !contactBean.isChecked;
        adapter.notifyDataSetChanged();
        selectedColleague.freshSelectedColleague(contactBean, String.valueOf(position));
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        loadData(textView.getText().toString());
        return false;
    }

    public interface ISelectedColleague {
        void freshSelectedColleague(ContactBean contactBean, String position);
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

    private class OverlayThread implements Runnable {

        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }

    }
}
