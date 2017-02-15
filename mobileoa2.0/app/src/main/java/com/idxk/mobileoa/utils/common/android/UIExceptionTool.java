package com.idxk.mobileoa.utils.common.android;

import android.content.Context;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.exception.DefalutNetException;
import com.idxk.mobileoa.exception.NotConnectException;
import com.idxk.mobileoa.exception.TimeOutException;

/**
 * 根据 excetption 来搞显示的类型
 */
public class UIExceptionTool {




    public static  String getToastByException(Exception e){
        Context c = MobileApplication.getInstance().getApplicationContext();
        if (e instanceof TimeOutException){
           return c.getResources().getString(R.string.timeoutconnect);
        }
        if (e instanceof NotConnectException){
            return c.getResources().getString(R.string.noconnect);
        }
        if(e instanceof DefalutNetException){

            return c.getResources().getString(R.string.otherconnect);

        }
        return "";
    }



}
