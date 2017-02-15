package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.fragment.CommandFragment;
import com.idxk.mobileoa.android.ui.fragment.DealWithCommandFragment;
import com.idxk.mobileoa.android.ui.fragment.DealWithDiaryFragment;

/**
 * Created by lenovo on 2015/3/9.
 */
public class TabPageIndicatorAdapter extends FragmentPagerAdapter {

    private Context context;
    private String[] items = null;
    private int type; //取分是待處理頁面還是我發出的工作 0=待處理頁面  1=我發出的工作

    public TabPageIndicatorAdapter(FragmentManager fm, Context context, int type) {
        super(fm);
        this.context = context;
        this.items = context.getResources().getStringArray(R.array.worksend_item);
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (type == 0) {
            switch (position) {
//                case 0:
//                    fragment = new DealWithDiaryFragment(); //页面显示相同用同一个页面
//                    break;
//                case 1:
//                    fragment = new DealWithCommandFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type", 0);
//                    fragment.setArguments(bundle);
//                    break;
//                case 2:
//                    fragment = new DealWithCommandFragment();
//                    Bundle mBundle = new Bundle();
//                    mBundle.putInt("type", 1);
//                    fragment.setArguments(mBundle);
//                    break;

                case 0:
                    fragment = new DealWithCommandFragment();
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("type", 1);
                    fragment.setArguments(mBundle);
                    break;
                case 1:
                    fragment = new DealWithDiaryFragment(); //页面显示相同用同一个页面
                    break;
                case 2:
                    fragment = new DealWithCommandFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);
                    fragment.setArguments(bundle);
                    break;
            }

        } else if (type == 1) {
            switch (position) {
                case 0:
                    fragment = new DealWithDiaryFragment(); //页面显示相同用同一个页面
                    break;
                case 1:
                    fragment = new CommandFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);
                    fragment.setArguments(bundle);
                    break;
                case 2:
                    fragment = new CommandFragment();
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("type", 1);
                    fragment.setArguments(mBundle);
                    break;
            }
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
