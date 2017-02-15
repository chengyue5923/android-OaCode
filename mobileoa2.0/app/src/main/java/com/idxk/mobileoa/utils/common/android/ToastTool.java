package com.idxk.mobileoa.utils.common.android;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;

/**
 * 关于toast的工具类
 *
 * @author linxi
 */
public class ToastTool {
    /**
     * 测试环境下的 toas 提示
     *
     * @param toast 提示内容
     */

    public static void showDev(String toast) {
        if (MobileApplication.getInstance() == null) {
            return;
        }
        boolean show = Boolean.parseBoolean(MobileApplication.getInstance().toastShow);
        if (!show) {
            return;
        }

        showSystem(toast);

    }

    private static Toast getToast(String content) {
        Toast toast = Toast.makeText(MobileApplication.getInstance().getApplicationContext(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP,
                0, Common.screenHeight((Activity) StaticContext.getInstance().getContext()) * 114 / 1280);
        View view = UITool.getView(MobileApplication.getInstance().getApplicationContext(), R.layout.view_toast, null);
        TextView view1 = (TextView) view.findViewById(R.id.toastTv);
        view1.setText(content);
        toast.setView(view);
        return toast;

    }

    /**
     * 测试环境下的 toast 提示
     *
     * @param toastResId 提示内容id
     */
    public static void showDev(int toastResId) {
        boolean show = Boolean.parseBoolean(MobileApplication.getInstance().toastShow);
        if (!show) {
            return;
        }
        showSystem(toastResId);

    }

    /**
     * 提示内容
     *
     * @param toast 内容
     */
    public static void show(String toast) {

        showSystem(toast);

    }

    /**
     * 提示内容
     *
     * @param resId 内容id
     */

    public static void show(int resId) {
        showSystem(resId);
    }


    private static void showSystem(String toast) {
//        Toast.makeText(MobileApplication.getInstance().getApplicationContext(), toast, Toast.LENGTH_LONG).show();
        getToast(toast).show();
    }

    private static void showSystem(int toastId) {
        getToast(MobileApplication.getInstance().getApplicationContext().getResources().getString(toastId)).show();

//        Toast.makeText(MobileApplication.getInstance().getApplicationContext(), toastId, Toast.LENGTH_LONG).show();
    }

}
