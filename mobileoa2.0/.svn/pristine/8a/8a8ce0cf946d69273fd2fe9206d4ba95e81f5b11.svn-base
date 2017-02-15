package com.idxk.mobileoa.utils.common.java;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.idxk.mobileoa.logic.controller.ContactController;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;

/**
 * Created by lenovo on 2015/5/25.
 */
public class Clickable extends ClickableSpan implements View.OnClickListener {
    private String spannableString;
    private boolean showUnderLine;
    private Context context;

    public Clickable(String string, boolean showUnderLine, Context context) {
        this.spannableString = string;
        this.showUnderLine = showUnderLine;
        this.context = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(showUnderLine);
        if (showUnderLine) {
            ds.setColor(0xff3434f1);
        } else {
            ds.setColor(0xff2292d2);
        }

    }

    @Override
    public void onClick(View v) {
        Logger.e(spannableString);
        if (spannableString.startsWith("http")) {
            Uri uri = Uri.parse(spannableString);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
        } else {

            String string = spannableString.substring(spannableString.indexOf("@") + 1);
            Logger.e(string);
            int count = ContactController.getInstance().getContactNumByName(string);
            Logger.e(String.valueOf(count));
            if (count > 0) {
                IntentTool.startContactDetailActivityByName((Activity) context, string);
            } else {
                count = ContactController.getInstance().getContactNumByDepart(string);
                if (count != 0) {
                    IntentTool.startDepartContectActivity(context, 0, string);
                }
            }
        }
    }
}
