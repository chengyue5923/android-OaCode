package com.idxk.mobileoa.utils.common.android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    public static boolean isEmail(String email) {
        if (email == null || email.equals(""))
            return false;

        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
