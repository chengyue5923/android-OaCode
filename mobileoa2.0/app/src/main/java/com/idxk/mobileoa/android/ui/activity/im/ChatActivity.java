package com.idxk.mobileoa.android.ui.activity.im;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.activity.BaseActivity;
import com.idxk.mobileoa.config.constant.IConstant;
import com.idxk.mobileoa.config.enums.HttpConfig;
import com.idxk.mobileoa.dao.MessageDao;
import com.idxk.mobileoa.dao.SessionDao;
import com.idxk.mobileoa.logic.controller.EventManager;
import com.idxk.mobileoa.logic.controller.IMController;
import com.idxk.mobileoa.model.bean.ImageUploadModel;
import com.idxk.mobileoa.model.bean.SessionModel;
import com.idxk.mobileoa.model.bean.UploadFileModel;
import com.idxk.mobileoa.utils.cache.db.achieve.UserPreference;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.im.CacheUtil;
import com.idxk.mobileoa.utils.im.CommonToolBar;
import com.idxk.mobileoa.utils.im.CommonUtil;
import com.idxk.mobileoa.utils.im.CommonUtils;
import com.idxk.mobileoa.utils.im.Constant;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class ChatActivity extends BaseActivity implements View.OnClickListener,
        MessageSendViewNew.MessageSendViewDelegate, ViewNetCallBack {


    @Bind(R.id.chatToolBar)
    CommonToolBar chatToolBar;
    @Bind(R.id.message_list)
    ListView messageList;
    @Bind(R.id.swipe_Refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.message_send_view)
    MessageSendViewNew messageSendView;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    private MessageAdapter mAdapter;
    private String audioFileName;
    private float y1, y2;
    private Dialog soundVolumeDialog = null;
    private ImageView soundVolumeImg = null;
    private LinearLayout soundVolumeLayout = null;
    private Uri mImageCaptureUri;
    private long mAudioRecordStartTimeMS;
    private MediaRecorder recorder;
    private String sessionId = "";
    private int fromUserId;
    private static final int SELECT_IMAGE_RESULT_CODE = 999;
    private static final int CAPTURE_IMAGE_RESULT_CODE = 1000;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(IMEvent event) {
        Logger.e("oevent" + event.count+sessionId);
        if(event.count!=0){
            List<MessageEntity> list = new MessageDao().getAllUnReadMessagesBySessionId(sessionId);
            for (MessageEntity entity : list) {
                Logger.e("enter"+entity.getContent());
                entity.setRead(1);
                new MessageDao().upadte(entity);
                mAdapter.addItem(entity);
            }
            if (list.size() > 0)
                scrollToBottomListItem();
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_chat_activity);
        ButterKnife.bind(this);
        messageSendView.setDelegate(this);
        initSoundVolumeDlg();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
            }
        });
        initEvent();
    }

    protected void initEvent() {
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
    }

    @Override
    protected void initData() {
        initRecord();
        mAdapter = new MessageAdapter(this);
        messageList.setAdapter(mAdapter);
        fromUserId = (int) getIntent().getSerializableExtra("fromUserId");

        String name = (String) getIntent().getSerializableExtra("username");

        chatToolBar.setTitle(name);

        SessionDao sessionDao = new SessionDao();

        Logger.e("loggerfromUserId" + fromUserId+name);
        try {
            List<SessionModel> lidtt = sessionDao.getAllSession();
            List<SessionModel> ss = sessionDao.getSendAllSession();

            sessionId = sessionDao.getSessionIdByUid(fromUserId);
            Logger.e("datat"+sessionId+sessionId.equals(""));
            if(sessionId.equals("")){
                sessionId = sessionDao.getSessionIdBySendUid(fromUserId);
            }
            Logger.e("allsessionId" + sessionId);
        } catch (Exception e) {
            Logger.e("allsesExceptionsionId" + sessionId + "123");
        }
        MessageDao dao = new MessageDao();

        dao.updateMessageToReadedBySessionId(sessionId);
        if (sessionId != null && !sessionId.equals("")) {
            List<MessageEntity> list = dao.getAllMessagesBySessionId(sessionId);
//        List<MessageEntity> list = dao.getAllMessages();
            for (int i = 0; i < list.size(); i++) {
                Logger.e("all" + list.get(i).toString());
            }
            Collections.sort(list, new MessageTimeComparator());
            for (MessageEntity entity : list) {
                mAdapter.addItem(entity);
            }
            scrollToBottomListItem();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public static class MessageTimeComparator implements Comparator<MessageEntity> {
        @Override
        public int compare(MessageEntity lhs, MessageEntity rhs) {
            if (lhs.getCreated() == rhs.getCreated()) {
                return lhs.getId() - rhs.getId();
            }
            return lhs.getCreated() - rhs.getCreated();
        }
    }


    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, boolean fromNet, Object o) {

//        if(flag== HttpConfig.UploadImage.getType()){
        try {

            JSONObject object = new JSONObject(o.toString());
            if (!object.getBoolean("status")) {
                ToastTool.show(object.getString("message"));
            } else {
                List<ImageUploadModel> modelist = (List<ImageUploadModel>) result;

                Log.e("timage----", modelist.get(0).getImg_id());
                ToastTool.show("图片上传成功");
                publishConversationPhotoMessage(modelist.get(0).getThumb());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
    }

    private void start(String path) {
        try {
            recorder.setOutputFile(path);
            recorder.prepare();
            recorder.start();
        } catch (Exception e) {
        }
    }

    private void initRecord() {

        try {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            recorder.setAudioChannels(2);
            recorder.setAudioSamplingRate(8000);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e(AudioRecorder.class.getName(), e.getMessage());
            } else {
                Log.e(AudioRecorder.class.getName(),
                        "Unknown error occured while initializing recording");
            }
        }
    }

    /**
     * @Description 初始化音量对话框
     */
    private void initSoundVolumeDlg() {
        soundVolumeDialog = new Dialog(this, R.style.SoundVolumeStyle);
        soundVolumeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        soundVolumeDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        soundVolumeDialog.setContentView(R.layout.im_sound_volume_dialog);
        soundVolumeDialog.setCanceledOnTouchOutside(true);
        soundVolumeImg = (ImageView) soundVolumeDialog.findViewById(R.id.sound_volume_img);
        soundVolumeLayout = (LinearLayout) soundVolumeDialog.findViewById(R.id.sound_volume_bk);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }

    /**
     * @Description 滑动到列表底部
     */
    private void scrollToBottomListItem() {
        if (messageList != null) {
            messageList.setSelection(mAdapter.getCount() + 1);
        }
    }

    @Override
    public void touchSendTextMessageButton(String text) {
        final TextMessage messageEntity = TextMessage.parseFromSend(text, sessionId);
        Log.e("test---------", messageEntity.getFromId() + "");
        IMController.getInstance().PushMessage(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {
                mAdapter.addItem(messageEntity);
            }

            @Override
            public void onConnectEnd() {

            }

            @Override
            public void onFail(Exception e) {
                messageEntity.setStatus(Constant.MSG_FAILURE);
                new MessageDao().updateStatus(messageEntity);
                mAdapter.updateItemState(messageEntity, false);
            }

            @Override
            public void onData(Serializable result, boolean fromNet, Object o) {

                try {

                    Logger.e("logger-------------" + result.toString());
                    MessageEntity entity = (MessageEntity) result;
                    sessionId = entity.getSession_id();
                    Logger.e("logger------" +sessionId);
                    Logger.e("logger---messageEntity---" +messageEntity);
                    Logger.e("logger---entity---" +entity);
                    TextMessage message = TextMessage.parseFromNet(messageEntity, entity);
                    mAdapter.updateItemState(message, true);

                    new MessageDao().upadte(message);

                    List<SessionModel> lidtt = new SessionDao().getAllSession();
                    boolean isNohave = true;
                    for (SessionModel see : lidtt) {
                        Logger.e("wwwqqqq" + see.getFromId() + see.getUserId() + see.getSessionId());
                        Logger.e("falge" + (see.getUserId() == Integer.parseInt(entity.getUser_id())));
                        Logger.e("falgeeee" + (see.getFromId() == Integer.parseInt(entity.getTo_user_id())));
                        if (see.getUserId() == Integer.parseInt(entity.getUser_id()) && see.getFromId() == Integer.parseInt(entity.getTo_user_id())) {
                            Logger.e("wwwqqqqwwwwww" + see.getFromId() + see.getUserId() + see.getSessionId());
                            isNohave = false;
                        }
                        if (see.getFromId() == Integer.parseInt(entity.getUser_id()) && see.getUserId() == Integer.parseInt(entity.getTo_user_id())) {
                            Logger.e("wqwwwwww" + see.getFromId() + see.getUserId() + see.getSessionId());
                            isNohave = false;
                        }
                    }
                    if (isNohave) {
                        SessionModel sessionModel = new SessionModel();
                        sessionModel.setUserId(UserPreference.getUid());
                        sessionModel.setFromId(Integer.parseInt(entity.getTo_user_id()));
                        sessionModel.setSessionId(entity.getSession_id());
                        sessionModel.setUnReadCount(0);
                        new SessionDao().update(sessionModel);
                    }

                } catch (Exception e) {
                    Log.e("loggergetMessages", e.getMessage());

                }
            }
        }, text, UserPreference.getTOKEN(), fromUserId, Constant.SHOW_ORIGIN_TEXT_TYPE, sessionId,0);
    }


    @Override
    public void touchSwitchButton() {

    }

    @Override
    public void touchDownAudioRecordButton(MotionEvent event) {
        if (soundVolumeDialog.isShowing()) {
            return;
        }
        audioFileName = Constant.AUDIO_TEMP_PATH + File.separator + new Date().getTime() + ".amr";
        //stop video and audio player
        mAudioRecordStartTimeMS = System.currentTimeMillis();
        start(audioFileName);
        y1 = event.getY();
        soundVolumeImg.setImageResource(R.drawable.im_sound_volume_01);
        soundVolumeImg.setVisibility(View.VISIBLE);
        soundVolumeLayout.setBackgroundResource(R.drawable.i_sound_volume_default_bk);
        soundVolumeDialog.show();
    }

    @Override
    public void touchMoveAudioRecordButton(MotionEvent event) {
        y2 = event.getY();
        if (y1 - y2 > 180) {
            soundVolumeImg.setVisibility(View.GONE);
            soundVolumeLayout.setBackgroundResource(R.drawable.im_sound_volume_cancel_bk);
        } else {
            soundVolumeImg.setVisibility(View.VISIBLE);
            soundVolumeLayout.setBackgroundResource(R.drawable.i_sound_volume_default_bk);
        }
    }


    @Override
    public void touchUpAudioRecordButton(MotionEvent event) {
//        y2 = event.getY();
        if (soundVolumeDialog.isShowing()) {
            soundVolumeDialog.dismiss();
        }
        if (Math.abs(y1 - y2) <= 180) {
            final long msDuration = System.currentTimeMillis() - mAudioRecordStartTimeMS;
            if (msDuration >= 1000) {
                if (msDuration < Constant.MAX_SOUND_RECORD_TIME) {
                    //// TODO: 2016/7/13 发送
//                    recorder.release();
                    new UploadAudioTask(audioFileName, (int) msDuration / 1000).execute();
                }
            } else {
                soundVolumeImg.setVisibility(View.GONE);
                soundVolumeLayout
                        .setBackgroundResource(R.drawable.im_sound_volume_short_tip_bk);
                soundVolumeDialog.show();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        if (soundVolumeDialog.isShowing())
                            soundVolumeDialog.dismiss();
                        this.cancel();
                    }
                }, 500);
            }
        }
    }

    @Override
    public void touchPickCameraPhotoButton() {
        selectTakeCameraIndex();
    }

    @Override
    public void touchLibraryPhotoButton() {
        selectFileFromLocal();
    }

    private class UploadAudioTask extends AsyncTask<String[], Boolean, String> {

        String path;
        int msDuration;

        public UploadAudioTask(String path, int msDuration) {
            Logger.e("test----UploadAudioTask-----" + path);
            this.path = path;
            this.msDuration = msDuration;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[]... params) {
            try {
                Logger.e("fff"+CommonUtil.encodeBase64File(path));
                return CommonUtil.encodeBase64File(path);

            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            Logger.e("osionc"+o.toString());
            final AudioMessage messageEntity = AudioMessage.parseFromSend(msDuration, sessionId, path);
            try {
                IMController.getInstance().PushMessage(new ViewNetCallBack() {
                    @Override
                    public void onConnectStart() {
                        mAdapter.addItem(messageEntity);
                    }

                    @Override
                    public void onConnectEnd() {

                    }

                    @Override
                    public void onFail(Exception e) {
                        messageEntity.setStatus(Constant.MSG_FAILURE);
                        new MessageDao().updateStatus(messageEntity);
                        mAdapter.updateItemState(messageEntity, false);
                    }

                    @Override
                    public void onData(Serializable result, boolean fromNet, Object o) {
                        Logger.e("logger-------------"+result.toString());

                        MessageEntity entity = (MessageEntity) result;
                        sessionId = entity.getSession_id();
                        mAdapter.updateItemState(messageEntity, true);
                        Logger.e("logger--------messageEntity-----"+ messageEntity.toString());
                        Logger.e("logger-------entity------"+ entity.toString());
                        AudioMessage message = AudioMessage.parseFromNet(messageEntity, entity);
                        new MessageDao().upadte(message);

                        List<SessionModel> lidtt = new SessionDao().getAllSession();
                        boolean isNohave = true;
                        for (SessionModel see : lidtt) {
                            if (see.getUserId() == Integer.parseInt(entity.getUser_id()) && see.getFromId() == Integer.parseInt(entity.getTo_user_id())) {
                                isNohave = false;
                            }
                            if (see.getFromId() == Integer.parseInt(entity.getUser_id()) && see.getUserId() == Integer.parseInt(entity.getTo_user_id())) {
                                isNohave = false;
                            }
                        }
                        if (isNohave) {
                            SessionModel sessionModel = new SessionModel();
                            sessionModel.setUserId(UserPreference.getUid());
                            sessionModel.setFromId(Integer.parseInt(entity.getTo_user_id()));
                            sessionModel.setSessionId(entity.getSession_id());
                            sessionModel.setUnReadCount(0);
                            new SessionDao().update(sessionModel);
                        }

                    }
                }, CommonUtil.encodeBase64File(path), UserPreference.getTOKEN(), fromUserId, Constant.SHOW_AUDIO_TYPE, sessionId,msDuration);
            } catch (Exception e) {
                Logger.e("editmessage" + e.getMessage());
            }

        }
    }

    /**
     * 选择文件
     */
    private void selectFileFromLocal() {

//        if (PackageManager.PERMISSION_GRANTED != ContextCompat.
//                checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//            //提示用户开户权限
//            String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE"};
//            ActivityCompat.requestPermissions(this, perms, 10002);
//            return;
//        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, SELECT_IMAGE_RESULT_CODE);
    }

    private void selectTakeCameraIndex() {

//        if (PackageManager.PERMISSION_GRANTED !=
//                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
//            //提示用户开户权限
//            String[] perms = {"android.permission.CAMERA"};
//            ActivityCompat.requestPermissions(this, perms, 10001);
//            return;
//        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mImageCaptureUri = Uri.fromFile(new File(CacheUtil.getCacheDirectory(this),
                "pick_photo_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, CAPTURE_IMAGE_RESULT_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = "";
        try{
            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_IMAGE_RESULT_CODE) {
                    path = getPath(data.getData());
                    Log.e("data.getData()---", data.getData() + "----" + path);
                }
                if (requestCode == CAPTURE_IMAGE_RESULT_CODE) {
                    Logger.e("data.getData()--path-"+ mImageCaptureUri.getPath());
                    path = mImageCaptureUri.getPath();
                    Logger.e("data.getData()--CAPTURE_IMAGE_RESULT_CODE----"+path);
                }
                Logger.e("loffer---path"+path);
                if (!new File(path).exists()) {
                    return;
                }
                UploadImage(path);
            }

        }catch (Exception e){
            Logger.e("exception"+e.getMessage());
        }

    }

    public void UploadImage(String path) {
        if (!new File(path).exists())
            return;
        Logger.e("photnooa------------"+ path);
        File[] fillist;
        fillist = new File[1];
        fillist[0] = new File(path);
        IMController.getInstance().upLoadFile(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {

            }

            @Override
            public void onConnectEnd() {

            }

            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onData(Serializable result, boolean fromNet, Object o) {

                Logger.e("上传成功"+result);

                UploadFileModel model=(UploadFileModel)result;
                Logger.e("上传成功路径--------"+model.getData().getSrc());
                publishConversationPhotoMessage(model.getData().getSrc());

            }
        }, fillist, HttpConfig.UploadImage, this);
    }

    public void publishConversationPhotoMessage(String photoFile) {
        try {
            Log.e("photno----------------", photoFile);
            final ImageMessage message = ImageMessage.parseFromSend(photoFile, sessionId);
            IMController.getInstance().PushImageMessage(new ViewNetCallBack() {
                @Override
                public void onConnectStart() {
                    mAdapter.addItem(message);
//                    mAdapter.updateItemState(message, true);
                }

                @Override
                public void onConnectEnd() {

                }

                @Override
                public void onFail(Exception e) {
                    message.setStatus(Constant.MSG_FAILURE);
                    new MessageDao().updateStatus(message);
                    mAdapter.updateItemState(message, true);
                }

                @Override
                public void onData(Serializable result, boolean fromNet, Object o) {
                    Logger.e("photno----result------" + o.toString());

                    Logger.e("photno----result------"+result.toString());
                    List<ImageUploadModel> model=( List<ImageUploadModel>)result;
                    Logger.e("photno--model----"+model.get(0).getImg_id());
                     MessageEntity models = (MessageEntity)result;
                    ImageMessage imageMessage = ImageMessage.parseFromNet(message, models);
                    new MessageDao().upadte(imageMessage);
                    mAdapter.updateItemState(imageMessage, true);
//                    SessionModel sessionModel = new SessionModel();
//                    sessionModel.setUserId(UserPreference.getUid());
//                    sessionModel.setFromId(Integer.parseInt(models.getTo_user_id()));
//                    sessionModel.setSessionId(models.getSession_id());
//                    sessionModel.setUnReadCount(0);
//                    new SessionDao().update(sessionModel);
                }
            }, photoFile, UserPreference.getTOKEN(), fromUserId, Constant.SHOW_IMAGE_TYPE, sessionId);
        } catch (Exception e) {
        }
    }

    public String getPath(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}
