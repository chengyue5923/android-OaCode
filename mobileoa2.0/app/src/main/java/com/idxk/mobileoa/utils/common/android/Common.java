package com.idxk.mobileoa.utils.common.android;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.android.ui.activity.SelectSendScopeActivity;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.utils.common.java.MD5Util;
import com.idxk.mobileoa.utils.common.java.StringTools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 公用工具
 */
public class Common {

    public static View.OnFocusChangeListener onFocusAutoClearHintListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText textView = (EditText) v;
            String hint;
            if (hasFocus && !StringTools.isNullOrEmpty(((EditText) v).getText().toString())) {
                hint = textView.getHint().toString();
                textView.setTag(hint);
                textView.setHint("");
            } else {
                hint = textView.getTag().toString();
                textView.setHint(hint);
            }
        }
    };
    public static View.OnTouchListener onTouchClearHintListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            EditText textView = (EditText) v;
            textView.setHint("");
            return false;
        }


    };

    public static void call(Context context, String num) {
        Intent intent = new Intent();
//系统默认的action，用来打开默认的电话界面
        intent.setAction(Intent.ACTION_CALL);
//需要拨打的号码
        intent.setData(Uri.parse("tel:" + num));
        context.startActivity(intent);

    }

    public static void sendSMS(String smsBody, Context context, String num)

    {

        Uri smsToUri = Uri.parse("smsto:" + num);

        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

        intent.putExtra("sms_body", smsBody);

        context.startActivity(intent);

    }

    public static void email(Context context, String emailAdress) {
        Intent email = new Intent(android.content.Intent.ACTION_SEND);
        email.setType("plain/text");
        String[] emailReciver = new String[]{emailAdress};


//设置邮件默认地址
        email.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);
//设置邮件默认标题
        email.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
//设置要默认发送的内容
        email.putExtra(android.content.Intent.EXTRA_TEXT, "");
//调用系统的邮件系统
        email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(email, "请选择邮件发送软件"));

    }

    public static String takePhoto1(Context context, int requestCode) {
        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE, null);
//        intent.putExtra("camerasensortype", 2); //调用前置摄像头
        //下面这句指定调用相机拍照后的照片存储的路径  //--图片的名称设定  用户的uuid和当前时间的拼出来的字符串
        String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "zmtCach"
                + File.separator + MD5Util.string2MD5(String.valueOf(System.currentTimeMillis())) + "camera" + ".png";
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
//                .fromFile(new File(filePath)));
        intent.putExtra("android.intent.extra.screenOrientation", 5);
        ((Activity) context).startActivityForResult(intent, requestCode);
        return filePath;
    }

    public static String getCameraPath() {
        String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "zmtCach"
                + File.separator + "camera" + ".png";
        return filePath;
    }

    public static String ambluePhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(
                "android.intent.action.PICK");
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        //下面这句指定调用相机拍照后的照片存储的路径  //--图片的名称设定  用户的uuid和当前时间的拼出来的字符串
        String filePath = Environment.getExternalStorageDirectory().getPath()
                + File.separator + "zmtCach" + File.separator + System.currentTimeMillis() + ".jpg";
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                .fromFile(new File(filePath)));
        activity.startActivityForResult(intent, requestCode);
        return filePath;
    }

    /**
     * 获取 屏幕的尺寸
     *
     * @param c 相应的activity
     * @return 屏幕的尺寸
     */
    public static DisplayMetrics screenSize(Activity c) {
        DisplayMetrics dm = new DisplayMetrics();
        c.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * @param c
     * @return 屏幕的宽
     */
    public static int screenWidth(Activity c) {
        return screenSize(c).widthPixels;
    }

    /**
     * @param c
     * @return 屏幕的高
     */
    public static int screenHeight(Activity c) {
        return screenSize(c).heightPixels;
    }

    /**
     * @param context
     * @param packageName 包名
     * @return 是否安装这个包名的 应用
     */
    public static boolean installPackageCheck(Context context,
                                              String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    packageName, 0);
            return info.versionCode >= 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public static void startPhotoZoom(Context context, Uri uri, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    public static String saveBm(Intent picdata) {
        Bundle extras = picdata.getExtras();
        String filePath = "";
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            filePath = FileTool.getInstance().getInnerSd() + File.separator + "zmt" + File.separator + System.currentTimeMillis() + ".jpg";
            ImageUtil.saveBitmap(photo, filePath);
        }
        return filePath;
    }

    public static void noty(String content, int id) {
        NotificationManager nm = (NotificationManager) MobileApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification n = new Notification(R.drawable.ic_launcher, content, System.currentTimeMillis());
        n.flags = Notification.FLAG_AUTO_CANCEL;
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(
                MobileApplication.getInstance(),
                R.string.app_name,
                i,
                PendingIntent.FLAG_UPDATE_CURRENT);

        n.setLatestEventInfo(
                MobileApplication.getInstance(),
                content,
                "",
                contentIntent);

        nm.notify(id, n);

    }

    public static void cancleNoticeByid(int id) {
        NotificationManager nm = (NotificationManager) MobileApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(id);
    }

    public static void cancleNoticeByIdAfterTime(final int id) {
        final NotificationManager nm = (NotificationManager) MobileApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nm.cancel(id);
            }
        }, 1000);
    }

    public static String getManifestVisionCode(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
//            int versionCode = context.getPackageManager()
//                    .getPackageInfo(pkName, 0).versionCode;
            return versionName + "";
        } catch (Exception e) {
        }
        return "1.0.0";
    }

    /**
     * 检查当前网络是否可用
     *
     * @param
     * @return
     */

    public static boolean isNetworkAvailable() {
        Context context = MobileApplication.getInstance().getApplicationContext().getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void setOnAtLisener(EditText editText, Activity activity, String decrip) {
        editText.addTextChangedListener(new WatchLisener(activity, decrip, editText));

    }

    public static void showSoftinput(View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


//        imm.toggleSoftInputFromWindow(view.getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hidenSoftinput(View view, Context context) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String dealWithPic(String path) throws Exception {
        InputStream isBm = new FileInputStream(new File(path));
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        String outPath = new File(path).getParent() + File.separator + "temp_" + new File(path).getName();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            if (options > 100) {
                options = 100;
            }
            if (options < 30) {
                options = 30;
            }

            bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
            options -= 10;
        }
        byte[] outBytes = baos.toByteArray();
        ByteArrayInputStream b = new ByteArrayInputStream(outBytes);//把压缩后的数据baos存放到ByteArrayInputStream中
        ImageUtil.saveBitmap(BitmapFactory.decodeStream(b, null, null), outPath);
        return outPath;
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    public static Bitmap dealWithPicWithBm(String path) throws Exception {
        InputStream isBm = new FileInputStream(new File(path));
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            if (options > 100) {
                options = 100;
            }
            if (options < 30) {
                options = 30;
            }

            bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
            options -= 10;
        }
        byte[] outBytes = baos.toByteArray();

        return BitmapFactory.decodeByteArray(outBytes, 0, outBytes.length);
    }

    public static String transImage(String fromFile) {
        String outPath = "";
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(fromFile);
            outPath = new File(fromFile).getParent() + File.separator + "temp_" + new File(fromFile).getName();
            File myCaptureFile = new File(outPath);
            FileOutputStream out = new FileOutputStream(myCaptureFile);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 30, out)) {
                out.flush();
                out.close();
            }
            if (!bitmap.isRecycled()) {
                bitmap.recycle();//记得释放资源，否则会内存溢出
            }

        } catch (Exception e) {
            return fromFile;
        }
        return outPath;
    }


//    public static String getStringFromMata(String key){
//        try {
//            ApplicationInfo appInfo = MobileApplication.getInstance().getPackageManager()
//                    .getApplicationInfo( MobileApplication.getInstance().getPackageName(),
//                            PackageManager.GET_META_DATA);
//            String msg=appInfo.metaData.getString(key);
//            Toast.makeText(MobileApplication.getInstance(),key+" "+msg,Toast.LENGTH_LONG).show();
//
//            return msg;
//        }catch (Exception e){
//            Toast.makeText(MobileApplication.getInstance(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
//
//        }
//
//       return "";
//    }

    public static class onFocusAutoClearHintListenerView implements
            View.OnFocusChangeListener {
        View view;

        public onFocusAutoClearHintListenerView(View view) {
            this.view = view;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText textView = (EditText) v;
            String hint;
            if (hasFocus && !StringTools.isNullOrEmpty(((EditText) v).getText().toString())) {
                hint = textView.getHint().toString();
                textView.setTag(hint);
                textView.setHint("");
                view.setVisibility(View.GONE);
            } else {
                hint = textView.getTag().toString();
//                textView.setHint(hint);
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public static class WatchLisener implements TextWatcher {

        Activity activity;
        String des;
        EditText editText;

        public WatchLisener(Activity activity, String des, EditText editText) {
            this.des = des;
            this.activity = activity;
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (StringTools.isNullOrEmpty(s.toString())) {
                return;
            }
            String res = s.toString();
            String string = res.substring(res.length() - 1, res.length());
            if (string.equals("@")) {

                Intent atIntent = new Intent(activity, SelectSendScopeActivity.class);
                atIntent.putExtra("atScope", des);
                activity.startActivityForResult(atIntent, IConstant.ATCOLLEAGUE);
            }


        }

        @Override
        public void afterTextChanged(Editable s) {
            String res = s.toString();
            if (res.contains("@@")) {
                String string = res.replace("@@", "@");
                editText.setText(string);
            }
        }
    }
}
