package com.idxk.mobileoa.utils.common.java;

import android.app.Fragment;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;

import com.idxk.mobileoa.android.ui.fragment.AppCenterFragment;
import com.idxk.mobileoa.android.ui.fragment.ContactFragment;
import com.idxk.mobileoa.android.ui.fragment.FreshFragment;
import com.idxk.mobileoa.android.ui.fragment.HomeNewSunFragment;
import com.idxk.mobileoa.android.ui.fragment.MyFragment;
import com.idxk.mobileoa.exception.NoDataException;
import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.utils.common.android.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/3/17.
 */
public class StaticUtils {
    public static Map<Integer, ContactBean> map = new HashMap<>();
    public static Fragment fragmentArray[];
    private static Exception exception;

    public static Exception getExcepiton() {
        if (exception == null) {
            exception = new NoDataException();
        }
        return exception;
    }


    public static Fragment[] getFragmentArray() {
        fragmentArray = new Fragment[]{HomeNewSunFragment.getInstance(), FreshFragment.getInstance(), ContactFragment.getInstance(), AppCenterFragment.getInstance(), MyFragment.getInstance()};
        return fragmentArray;
    }

    public static SpannableString dealWithString(String original, Context context) {
        String todayString = StringTools.toTrim(original);
        SpannableString spanableInfo = new SpannableString(todayString);

        if (todayString.contains("@")) {
            String[] names = todayString.replace("\n", " ").split(" ");
            for (String name : names) {
                if (name.contains("@") && !name.contains(".com")) {
                    if (!name.startsWith("@")) {
                        name = name.substring(name.indexOf("@"));
                    }
                    int count = ContactController.getInstance().getContactNumByName(name.substring(name.indexOf("@") + 1));
                    if (count <= 0) {
                        count = ContactController.getInstance().getContactNumByDepart(name.substring(name.indexOf("@") + 1));
                    }
                    if (count > 0) {
                        spanableInfo.setSpan(new Clickable(name, false, context), spanableInfo.toString().indexOf(name), spanableInfo.toString().indexOf(name) + name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        todayString = todayString.replace(name.substring(name.indexOf("@")), "<font color=\"#2292d2\">" + name.substring(name.indexOf("@")) + "</font>");
                    }

                }
            }
        }

        if (todayString.contains("http://") || todayString.contains("https://")) {
            String[] urls = todayString.replace("\n", " ").split(" ");
            for (String url : urls) {
                if (url.contains("http://")) {
                    if (!url.startsWith("http")) {
                        int position = url.indexOf("http");
                        url = url.substring(position);
                    }
                    spanableInfo.setSpan(new Clickable(url, true, context), spanableInfo.toString().indexOf(url), spanableInfo.toString().indexOf(url) + url.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    todayString = todayString.replace(url.substring(url.indexOf("http://")), "<font color=\"#3434f1\">" + url.substring(url.indexOf("http://")) + "</font>");
                }

                if (url.contains("https://")) {
                    if (!url.startsWith("https")) {
                        int position = url.indexOf("https");
                        url = url.substring(position);
                    }
                    spanableInfo.setSpan(new Clickable(url, true, context), spanableInfo.toString().indexOf(url), spanableInfo.toString().indexOf(url) + url.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    todayString = todayString.replace(url.substring(url.indexOf("https://")), "<font color=\"#3434f1\">" + url.substring(url.indexOf("https://")) + "</font>");
                }
            }

        }
        Logger.e(spanableInfo + ">>>>>====");
        return spanableInfo;
    }
}
