package com.idxk.mobileoa.utils.net.connect.http.file;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.idxk.mobileoa.android.application.MobileApplication;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.config.enums.HttpManager;
import com.idxk.mobileoa.config.parse.UrlRes;
import com.idxk.mobileoa.model.bean.AvarAllBean;
import com.idxk.mobileoa.model.bean.HttpConfigBean;
import com.idxk.mobileoa.model.bean.UploadFileModel;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.GsonTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.idxk.mobileoa.utils.net.connect.http.HttpRequest;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.SoftReference;


public class UpdateTask extends AsyncTask<Object, Object, Object> {

    Context con;
    HttpConfig configA;
    ViewNetCallBack listener;
    String path = "";
    private ProgressDialog mDialog;
    private SoftReference<ProgressDialog> softReference;
    //	private IUpdateData callBack;
    private boolean completeFlag;
    private String tipStr;
    private boolean isShowProgress;
    private FileBody fb;


    public UpdateTask(Context con,
                      boolean isShowProgress, HttpConfig config) {
        this.con = con;
        mDialog = new ProgressDialog(con);
        softReference = new SoftReference<>(mDialog);
        tipStr = "正在上传";
        this.isShowProgress = isShowProgress;
        this.configA = config;

    }

    public void setListener(ViewNetCallBack listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        completeFlag = false;
        if (isShowProgress) {
            softReference.get().setMessage(tipStr);
            softReference.get().setProgressStyle(ProgressDialog.STYLE_SPINNER);
            softReference.get().setCancelable(false);
            softReference.get().show();
            softReference.get().setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (!UpdateTask.this.isCancelled() && !completeFlag) {
                        UpdateTask.this.cancel(true);
                    }
                }
            });
        }
        listener.onConnectStart();
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        completeFlag = true;
        listener.onConnectEnd();
        if (listener != null) {
            try {

                if (configA == HttpConfig.upLoadPicture) {
                    UploadFileModel model = GsonTool.jsonToEntity(result.toString(), UploadFileModel.class);
                    listener.onData(model, true, null);
                }

                if (configA == HttpConfig.upLoadUserPicture) {
                    AvarAllBean model1 = GsonTool.jsonToEntity(result.toString(), AvarAllBean.class);
                    listener.onData(model1, true, null);
                }

            } catch (Exception e) {
                listener.onFail(new Exception());
            }
        }
        if (isShowProgress) {
            softReference.get().dismiss();
        }
    }

    private HttpPost getHttpPost(String info) {
        path = info;
        if (!StringTools.isNullOrEmpty(info)) {


//            if (configA==HttpConfig.upLoadPicture){
            Logger.e("123 info before=" + info);
            try {
                info = Common.transImage(info);
            } catch (Exception e) {
                Logger.e(e.getLocalizedMessage(), e);
            }
//            }
            File file = new File(info);
            fb = new FileBody(file);

            Logger.e("123 info=" + info);
        }
        HttpConfigBean config = HttpManager.getInstance().getConifgById(configA);
        String url = UrlRes.getInstance().getUrl() + config.getActioin();

        Logger.e("123 url=" + url);
        HttpPost post;
        post = new HttpPost(url);
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (fb != null) {
            reqEntity.addPart("file", fb);
        }


        if (config.isNeedLogin()) {
            Context context = MobileApplication.getInstance();
            String oauth_token = PreferceManager.getInsance().getValueBYkey(context, "oauth_token");
            String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(context, "oauth_token_secret");
//            String oauth_token = "00f0e276340d20a16a2529d9b70e670c";
//            String oauth_token_secret = "c7f77b3a1c121f3c17ca5d478d37cd02";

            try {
                StringBody token = new StringBody(oauth_token);
                StringBody oauth_token_secret1 = new StringBody(oauth_token_secret);
                reqEntity.addPart("oauth_token", token);
                reqEntity.addPart("oauth_token_secret", oauth_token_secret1);
//                reqEntity.addPart("oauth_token","00f0e276340d20a16a2529d9b70e670c");
//                reqEntity.addPart("oauth_token_secret","c7f77b3a1c121f3c17ca5d478d37cd02");

            } catch (Exception e) {

            }

            post.setEntity(reqEntity);
//            admin
//            param.put("oauth_token", oauth_token);
//            param.put("oauth_token_secret", oauth_token_secret);
        }


        return post;
    }

    ByteArrayBody byteArrayBody;
    private HttpPost getHttpPost(Bitmap bm) {
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (bm!=null) {

            Logger.e("123 info before=" + bm);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] data = bos.toByteArray();
            byteArrayBody = new ByteArrayBody(data,System.currentTimeMillis()+".jpg");
            reqEntity.addPart("file", byteArrayBody);
        }
        HttpConfigBean config = HttpManager.getInstance().getConifgById(configA);
        String url = UrlRes.getInstance().getUrl() + config.getActioin();

        Logger.e("123 url=" + url);
        HttpPost post;
        post = new HttpPost(url);

        if (config.isNeedLogin()) {
            Context context = MobileApplication.getInstance();
            String oauth_token = PreferceManager.getInsance().getValueBYkey(context, "oauth_token");
            String oauth_token_secret = PreferceManager.getInsance().getValueBYkey(context, "oauth_token_secret");
//            String oauth_token = "00f0e276340d20a16a2529d9b70e670c";
//            String oauth_token_secret = "c7f77b3a1c121f3c17ca5d478d37cd02";

            try {
                StringBody token = new StringBody(oauth_token);
                StringBody oauth_token_secret1 = new StringBody(oauth_token_secret);
                reqEntity.addPart("oauth_token", token);
                reqEntity.addPart("oauth_token_secret", oauth_token_secret1);
//                reqEntity.addPart("oauth_token","00f0e276340d20a16a2529d9b70e670c");
//                reqEntity.addPart("oauth_token_secret","c7f77b3a1c121f3c17ca5d478d37cd02");

            } catch (Exception e) {

            }

            post.setEntity(reqEntity);
//            admin
//            param.put("oauth_token", oauth_token);
//            param.put("oauth_token_secret", oauth_token_secret);
        }


        return post;
    }

    @Override
    protected String doInBackground(Object... params) {
        HttpPost hp ;

        if (params.length > 1) {
            Bitmap bm = (Bitmap)params[1];
            hp = getHttpPost(bm);
        }else{
            hp = getHttpPost(params[0].toString());
        }
        String result = "";
        try {
            result = HttpRequest.httpRequest(hp, 10 * 1000);
            Logger.e("-result--" + result);
        } catch (Exception e) {
            Logger.e(e.getMessage(), e);
        }
        return result;
    }


}
