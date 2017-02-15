package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.fragment.ColleagueFragment;
import com.idxk.mobileoa.android.ui.fragment.DepartMentFragment;
import com.idxk.mobileoa.android.ui.fragment.RecentFragment;

/**
 * Created by lenovo on 2015/3/16.
 */
public class TabPageIndicatorSelectAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] items = null;
    private int type;

    public TabPageIndicatorSelectAdapter(FragmentManager fm, Context context, int type) {
        super(fm);
        this.context = context;
        this.items = context.getResources().getStringArray(R.array.selectSendScope_tabItem);
        this.type = type;
    }
    private boolean sendRange;
    private int sendType;

    public boolean isSendRange() {
        return sendRange;
    }

    public void setSendRange(boolean sendRange) {
        this.sendRange = sendRange;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RecentFragment(); //页面显示相同用同一个页面
                Bundle bundle = new Bundle();
                bundle.putBoolean("sendRange",sendRange);
                bundle.putInt("type", sendType);
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new ColleagueFragment();
                break;
            case 2:
                fragment = new DepartMentFragment();
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items[position];
    }

    @Override
    public int getCount() {
        return items.length;
    }
}
