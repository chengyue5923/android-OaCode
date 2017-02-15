package com.idxk.mobileoa.android.ui.activity.im;



import com.idxk.mobileoa.utils.cache.db.achieve.UserPreference;
import com.idxk.mobileoa.utils.im.Constant;

import java.io.Serializable;

/**
 * @author : yingmu on 14-12-31.
 * @email : yingmu@mogujie.com.
 */
public class ImageMessage extends MessageEntity implements Serializable {

    /**
     * 本地保存的path
     */
    private String path = "";
    /**
     * 图片的网络地址
     */
    private String url = "";

    public ImageMessage() {
    }

    /**
     * 消息拆分的时候需要
     */
    public ImageMessage(MessageEntity entity) {
        /**父类的id*/
        if(entity.getUser_id()==null){
            userId = entity.getFromId();
        }else{
            userId = Integer.parseInt(entity.getUser_id());
        }
        sessionId = entity.getSessionKey();
        content = entity.getContent();
        to_user_id=entity.getTo_user_id();
        messageType = entity.getDisplayType();
        statu = entity.getStatus();
        sendTime = entity.getCreated();
        locId =entity.getLocId();
    }

    public static ImageMessage parseFromNet(ImageMessage text, MessageEntity entity) {
        text.setContent(entity.getContent());
        text.setStatus(Constant.MSG_SUCCESS);
        text.setDisplayType(Constant.SHOW_IMAGE_TYPE);
        text.setId(entity.getId());
        text.setCreated(entity.getCreated());
        text.setFromId(entity.getFromId());
        text.setSession_id(entity.getSession_id());
        text.setSessionKey(entity.getSessionKey());
        return text;
    }

    public static ImageMessage parseFromSend(String text, String sessionId) {
        ImageMessage textMessage = new ImageMessage();
        textMessage.setContent(text);
        textMessage.setRead(1);
        textMessage.setFromId(UserPreference.getUid());
        textMessage.setDisplayType(Constant.SHOW_IMAGE_TYPE);
        textMessage.setStatus(Constant.MSG_SENDING);
//        textMessage.setFromId(UserPreference.getUid());
        textMessage.setCreated((int) (System.currentTimeMillis() / 1000));
        textMessage.setSessionKey(sessionId);
//        long id = new MessageDao().insertRaw(textMessage);
//        textMessage.setLocId((int) id);
        return textMessage;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "path='" + path + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    /**
     * -----------------------set/get------------------------
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
