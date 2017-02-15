package com.idxk.mobileoa.utils.net.connect.http.file;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.utils.common.android.FileTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.net.callback.FileDownListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class DownFileWithNoticeBarThread  extends AsyncTask<String, Integer, File> {

    Context context;
    NotificationManager downloadNM;
    Notification downloadNotification;
    int max;
    String localPath="";
    int count;
    int num = 0;
    int times = 0;

    FileDownListener downListener;

    public void setDownListener(FileDownListener downListener) {
        this.downListener = downListener;
    }

    public DownFileWithNoticeBarThread(Context context) {
        this.context = context;
        downloadNM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        downloadNotification = new Notification(R.drawable.ic_launcher, "文件下载到 " + FileTool.getInstance().getZMTSystemDir("office"), System.currentTimeMillis());
        downloadNotification.contentView = new RemoteViews(context.getPackageName(), R.layout.view_notiy_download);
        downloadNotification.contentIntent = PendingIntent.getActivity(context, 3, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
        downloadNM.notify(1, downloadNotification);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (downListener!=null){
            downListener.starDownLoad();
        }
    }

    @Override
    protected File doInBackground(String... params) {
        File file = new File(params[1]);
        try {
            localPath= params[1];
            URL httpUrl = new URL(params[0]);
            Logger.e("=====url========" + params[0]);
            HttpURLConnection conn;
            conn = (HttpURLConnection) httpUrl.openConnection();
            max = conn.getContentLength();
            downloadNotification.contentView.setProgressBar(R.id.progressBar1, conn.getContentLength(), 0, false);
            InputStream is = conn.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024 * 8];
            conn.connect();
            if (conn.getResponseCode() >= 400) {
                file = null;
            } else {
                while (true) {
                    if (is != null) {
                        int numRead = is.read(buf);
                        publishProgress(numRead);
                        if (numRead <= 0) {
                            break;
                        } else {
                            num += numRead;
                            fos.write(buf, 0, numRead);
                        }
                    } else {
                        break;
                    }
                }
            }
            conn.disconnect();
            if (fos != null) {
                fos.close();
            }

            if (is != null) {
                is.close();
            }


        } catch (Exception e) {
            file = null;
            Logger.e("------e---" + e);

        }
        return file;
    }

    @Override
    protected void onPostExecute(File result) {
        super.onPostExecute(result);
//        downloadNM.cancel(1);
        if (result != null && result.exists()) {
            downloadNotification.contentView.setProgressBar(R.id.progressBar1, max, max, false);
            downloadNM.cancel(1);
            if (downListener!=null){
                downListener.endDownLoad(result,true);
            }
        } else {
            ToastTool.show("下载失败");
            downListener.endDownLoad(result,false);
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        count += values[0];
        Logger.e("----count--" + count);
        Logger.e("---max---" + max);
        downloadNotification.contentView.setProgressBar(R.id.progressBar1, max, count, false);
        if (times == 512 || count == max) {

            if (count == max) {
                downloadNotification.contentView.setTextViewText(R.id.content, "下载完成！在" + localPath + "查看。");
            }
            downloadNM.notify(1, downloadNotification);
            times = 0;
        }
        times++;
        super.onProgressUpdate(values);
    }
}
