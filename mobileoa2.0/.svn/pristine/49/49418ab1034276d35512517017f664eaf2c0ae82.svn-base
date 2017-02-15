package com.idxk.mobileoa.android.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.ViewPagerAdapter;
import com.idxk.mobileoa.model.bean.AttachBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/28.
 */
public class ShowImageActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {


    /**
     * 用于管理图片的滑动
     */
    private ViewPager viewPager;

    /**
     * 显示当前图片的页数
     */
    private TextView pageText;

    private ViewPagerAdapter viewPagerAdapter;

    private ArrayList<AttachBean> attachBeanList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_image_show);
        viewPager = (ViewPager) id2v(R.id.view_pager);
        pageText = (TextView) id2v(R.id.page_text);
        pageText.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        attachBeanList = (ArrayList<AttachBean>) getIntent().getSerializableExtra("bean");
        viewPagerAdapter = new ViewPagerAdapter(attachBeanList, this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        int current = getIntent().getIntExtra("current", 0);
        pageText.setText(1 + "/" + attachBeanList.size());
        viewPager.setCurrentItem(current);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int currentPage) {
        pageText.setText(currentPage + 1 + "/" + attachBeanList.size());
    }

    @Override
    public void onPageScrollStateChanged(int currentPage) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.page_text:
                this.finish();
                break;
        }
    }
}
