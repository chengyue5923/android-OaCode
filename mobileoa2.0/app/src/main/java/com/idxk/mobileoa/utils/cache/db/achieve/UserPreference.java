
package com.idxk.mobileoa.utils.cache.db.achieve;


import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;

public final class UserPreference {
    private static final String PREF_ID = "id";
    private static final String PREF_TOKEN = "token";
    private static final String PREF_LOCATION= "location";

    private static final String PREF_KEY_UID = "uid";
    private static final String PREF_KEY_SEX = "sex";
    private static final String PREF_KEY_LEVEL = "level";
    private static final String PREF_KEY_AVATAR = "avatar";
    private static final String PREF_KEY_NAME = "name";
    private static final String PREF_KEY_ROLE = "loginRole";
    private static final String PREF_KEY_PHONE = "phone";
    private static final String PREF_KEY_LOGINNAME = "loginName";
    private static final String PREF_KEY_CERTIFICATES = "CertificatesNum";
    private static final String PREF_KEY_PASSPORT = "passportCode";

    private static final String PREF_KEY_CB_ID = "cb_id";
    private static final String PREF_KEY_LASTLOGIN = "lastLogin";
    private static final String PREF_KEY_ROLEID = "roleId";
    private static final String PRIVATE_LAT="lat";  //纬度
    private static final String PRIVATE_LNG="lng"; //经度

    private static final String FSENVSS="FSENVss";

    public static void setLng(double  uid) {
        PreferceManager.getInsance().saveValueBYkey(PRIVATE_LNG, String.valueOf(uid));
    }

    public static double getLng() {
        return Double.parseDouble(PreferceManager.getInsance().getValueBYkey(PRIVATE_LNG));
    }
    public static void setLat(double  uid) {
        PreferceManager.getInsance().saveValueBYkey(PRIVATE_LAT, String.valueOf(uid));
    }

    public static double getLat() {
        return Double.parseDouble(PreferceManager.getInsance().getValueBYkey(PRIVATE_LAT));
    }




    public static void cleanUser() {
        setSex(1);
        setID(0);
        setUid(0);
        setLevel("");
        setName("");
        setAvatar("");
        setTOKEN("");
        setLoginName("");
        setCertificates("");
        setPREF_LOCATION("");
        setphone("");
        setPassport("");
    }


    public static void setFenvs(String uid) {
        PreferceManager.getInsance().saveValueBYkey(FSENVSS, String.valueOf(uid));
    }

    public static String getFENVS() {
        return PreferceManager.getInsance().getValueBYkey(FSENVSS);
    }


    public static void setID(int  uid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_ID, String.valueOf(uid));
    }

    public static int getID() {
        return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_ID));
    }

    public static void setTOKEN (String uid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_TOKEN, String.valueOf(uid));
    }

    public static String getTOKEN () {
        return PreferceManager.getInsance().getValueBYkey(PREF_TOKEN);
    }
    public static void setPREF_LOCATION (String uid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_LOCATION, String.valueOf(uid));
    }

    public static String getPREF_LOCATION () {
        return PreferceManager.getInsance().getValueBYkey(PREF_LOCATION);
    }


//    public static void setFSENV(List<Map<String, String>> fs) {
//        PreferceManager.getInsance().saveInfo("FSENV", fs);
//    }
//    public static List<Map<String, String>>  getFSENV(){
//        return PreferceManager.getInsance().getInfo("FSENV");
//    }

    public static void setCBid(String cbid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_CB_ID, String.valueOf(cbid));
    }

    public static String getCBUid() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_CB_ID);
    }

    public static void setUid(int uid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_UID, String.valueOf(uid));
    }

    public static int getUid() {
        return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_KEY_UID));
    }

    public static void setRoleId(int uid) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_ROLEID, String.valueOf(uid));
    }

    public static int getRoleId() {
        try {
            return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_KEY_ROLEID));
        } catch (Exception e) {
            return 0;
        }
    }


    public static void setSex(int sex) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_SEX, String.valueOf(sex));
    }

    public static String getPassport() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_PASSPORT);
    }

    public static void setPassport(String passport) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PASSPORT, passport);
    }

    public static int getSex() {
        return Integer.parseInt(PreferceManager.getInsance().getValueBYkey(PREF_KEY_SEX));
    }

    public static void setRole(String role) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_ROLE, role);
    }

    public static String getROLE() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_ROLE);
    }

    public static void setphone(String phone) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_PHONE, phone);
    }

    public static String getPhone() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_PHONE);
    }

    public static void setCertificates(String certificates) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_CERTIFICATES, certificates);
    }

    public static String getCertificates() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_CERTIFICATES);
    }

    public static void setLoginName(String loginName) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_LOGINNAME, loginName);
    }

    public static String getLoginName() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_LOGINNAME);
    }


    public static void setName(String name) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_NAME, name);
    }

    public static String getName() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_NAME);
    }

    public static void setAvatar(String avatar) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_AVATAR, avatar);
    }

    public static String getAvatar() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_AVATAR);
    }

    public static void setLevel(String level) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_LEVEL, level);
    }

    public static String getLevel() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_LEVEL);
    }


    public static boolean isCustomer() {
        return getROLE() != null && getROLE().equals("customer");
    }

    public static void saveLastLogin(String lastLogin) {
        PreferceManager.getInsance().saveValueBYkey(PREF_KEY_LASTLOGIN, lastLogin);
    }

    public static String getLastLogin() {
        return PreferceManager.getInsance().getValueBYkey(PREF_KEY_LASTLOGIN);
    }


}
