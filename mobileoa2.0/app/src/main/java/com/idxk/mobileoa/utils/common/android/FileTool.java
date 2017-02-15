package com.idxk.mobileoa.utils.common.android;

import android.content.res.AssetManager;
import android.os.Environment;
import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.config.enums.FileType;
import com.idxk.mobileoa.utils.common.java.FileTools;
import com.idxk.mobileoa.utils.common.java.StringTools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *关于file的工具类
 */
public class FileTool {

    public static FileTool getInstance() {
        return SingleClearCach.instance;
    }


    static class SingleClearCach {
        static FileTool instance = new FileTool();
    }



    String systemDir = "zmt";
    String systemDBDir = systemDir + File.separator + "database";
    String officeDir = systemDir + File.separator + "office";
    String errorDir = systemDir + File.separator + "log";
    String systemImageDir = systemDir + File.separator + ".image";

    public  void createSystemDirs() {
        FileTools.creatDir(getSystemDir(systemDBDir));
        FileTools.creatDir(getSystemDir(officeDir));
        FileTools.creatDir(getSystemDir(systemImageDir));
        FileTools.creatDir(getSystemDir(errorDir));
    }

    public   String getSystemDir(String dir) {
        if (StringTools.isNullOrEmpty(getInnerSd())) {
            return "";
        }
        return getInnerSd() + File.separator + dir;
    }
    public   String getZMTSystemDir(String dir) {
        if (StringTools.isNullOrEmpty(getInnerSd())) {
            return "";
        }
        return getInnerSd()+File.separator+systemDir + File.separator  + dir;
    }
    public  String getInnerSd() {
        if ((Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory().canWrite()) && (Environment.getExternalStorageDirectory().canRead()))
            return Environment.getExternalStorageDirectory().getPath();
        return null;
    }


    /**
     * 根据 type　和　文件名称　获取　这个文件的路径
     * @param type　　文件的类型　如　数据库等
     * @return　
     */
    public String getPathByType(String name,FileType type){

      switch (type){
          case db:
              return  getSystemDir(systemDBDir)+File.separator+name;
          case image:
              return  getSystemDir(systemImageDir)+File.separator+name;
          case office:
              return  getSystemDir(officeDir)+File.separator+name;
      }
      return "";

    }


    /**
     * @param outFilePath
     * @param inFileName
     */
    public  void copyDBfromAssets2SD(String outFilePath, String inFileName) {
        String filePath = outFilePath;
        File f = new File(filePath);
        boolean isCopyed = false;
        isCopyed = f.exists();
        if (!isCopyed) {
            try {
                //得到资源
                AssetManager am = MobileApplication.getInstance().getAssets();
                //得到数据库的输入流
                InputStream is = am.open(inFileName);
                //用输出流写到SDcard上面
                FileOutputStream fos = new FileOutputStream(new File(filePath));
                //创建byte数组  用于1KB写一次
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                //最后关闭就可以了
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                Logger.e(e.getLocalizedMessage(), e);
            }
        }

    }








}
