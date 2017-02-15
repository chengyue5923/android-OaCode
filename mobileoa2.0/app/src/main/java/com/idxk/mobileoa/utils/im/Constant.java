package com.idxk.mobileoa.utils.im;

import android.os.Environment;

import com.idxk.mobileoa.android.application.MobileApplication;

import java.io.File;

/**
 */
public class Constant {
    /**
     * 打包使用
     */

    public static String LOGPATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "shenguiCrash";
    public static boolean DEBUG = true;
    /**
     * msgDisplayType
     * 保存在DB中，与服务端一致
     * 1. 最基础的文本信息
     * 2. 纯图片信息
     * 3. 语音
     */
    public static final String SHOW_ORIGIN_TEXT_TYPE = "text";
    public static final String SHOW_SHARE_TYPE = "share";
    public static final String SHOW_IMAGE_TYPE = "image";
    public static final String SHOW_AUDIO_TYPE = "audio";
    // 读取磁盘上文件， 分支判断其类型
    public static final int FILE_SAVE_TYPE_IMAGE = 0X00013;
    public static final int FILE_SAVE_TYPE_AUDIO = 0X00014;
    public static final long MAX_SOUND_RECORD_TIME = 6000;// 单位毫秒

    public static final int MESSSAGE_SYNCING_INTERVAL = 10; // in seconds
    /**
     * 基础消息状态，表示网络层收发成功
     */
    public static final int MSG_SENDING = 1;
    public static final int MSG_FAILURE = 2;
    public static final int MSG_SUCCESS = 3;

    public static final String DISPLAY_FOR_IMAGE = "[图片]";

    public static final String DISPLAY_FOR_AUDIO = "[语音]";
    public static final String DISPLAY_FOR_SHARE= "[分享]";


    /**
     * 语音状态，未读与已读
     */
    public static final int AUDIO_UNREAD = 1;
    public static final int AUDIO_READED = 2;


    public static final String AUDIO_TEMP_PATH = CacheUtil.getCacheDirectory(MobileApplication.getInstance().getApplicationContext()) + File.separator + "Audio" + File.separator + "temp";
    public static final String AUDIO_PATH = CacheUtil.getCacheDirectory(MobileApplication.getInstance().getApplicationContext()) + File.separator + "Audio";

}
