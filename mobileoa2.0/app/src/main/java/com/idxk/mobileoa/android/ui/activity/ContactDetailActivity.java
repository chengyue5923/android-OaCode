package com.idxk.mobileoa.android.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts.Data;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.UserController;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.model.bean.PersonBean;
import com.idxk.mobileoa.utils.cache.preferce.PreferceManager;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.IntentTool;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.ListUtil;
import com.idxk.mobileoa.utils.common.java.StringTools;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.mogujie.tt.TTIMManager;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 */
public class ContactDetailActivity extends BaseActivity implements MainTitleView.OnTitleClick {
    MainTitleView mainTitle;
    TextView nameTv, department, mobile, workMobile, fenMobile, nameTv1, suncompanyTv, locationAddress, signatureTv;
    ContactBean bean;
    ImageView icon;
    private TextView emailAddress;
    private byte[] photoArray;
    private TextView chat_btn;

    private PopupWindow popupWindow;
    private LinearLayout ll_popup;

    private View parentView;

    @Override
    protected void initView() {
        parentView = getLayoutInflater().inflate(R.layout.activity_contact_detail, null);
        setContentView(parentView);
        mainTitle = (MainTitleView) findViewById(R.id.mainTitle);
        nameTv = (TextView) findViewById(R.id.nameTv);
        locationAddress = (TextView) findViewById(R.id.locationAddress);
        signatureTv = (TextView) findViewById(R.id.signatureTv);
        suncompanyTv = (TextView) findViewById(R.id.suncompanyTv);
        department = (TextView) findViewById(R.id.department);
        nameTv1 = (TextView) findViewById(R.id.nameTv1);
        icon = (ImageView) findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean != null) {
                    IntentTool.startImageToolShowActivity(ContactDetailActivity.this, bean.getAvatar_original());
                }
            }
        });
        mobile = (TextView) findViewById(R.id.mobile);
        workMobile = (TextView) findViewById(R.id.workMobile);
        fenMobile = (TextView) findViewById(R.id.fenMobile);
        mainTitle.setOnTitleClickLisener(this);
        mainTitle.setRightTv("保存到手机");
        chat_btn = (TextView) findViewById(R.id.chat_btn);
        emailAddress = (TextView) id2v(R.id.emailAddress);
        chat_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {
//                    String mail = PreferceManager.getInsance().getValueBYkey(ContactDetailActivity.this, "mail");
//                    Logger.e(">>>>>> userName>>>"+mail);
//                    String[] strings=mail.split("@");
//                    mail=strings[0];
//                    String sessionId;
//                    if (mail.equals("dongjiming")){
//                        sessionId="user_2";
//                    }else{
//                        sessionId="user_4";
//                    }
//
//                    Logger.e(">>>>>> userName>>> sessionId"+sessionId);
////                    TTIMManager.getInstance().chatMessage(ContactDetailActivity.this, "1_" + bean.getUid());
//                    TTIMManager.getInstance().chatMessage(ContactDetailActivity.this, sessionId);
//
//                    ContactDetailActivity.this.finish();


                    Logger.e("beanuid-------------------"+Integer.parseInt(bean.getUid())+bean.getUname());
                    IntentTool.startChat(ContactDetailActivity.this,Integer.parseInt(bean.getUid()),bean.getUname());
                    ContactDetailActivity.this.finish();
                } catch (Exception e) {
                    Logger.e(e.getMessage());
                }

            }
        });

        popupWindow = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.popupwindow_details_item, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        Button item_popupwindows_Photo = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        item_popupwindows_Photo.setText("拨打电话");
        Button item_popupwindows_cancel = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        item_popupwindows_cancel.setText("发送短信");
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_transparent));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);

        item_popupwindows_Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                ll_popup.clearAnimation();
                IntentTool.startSelectMobileActivity(ContactDetailActivity.this, bean, 0);
            }
        });
        item_popupwindows_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                ll_popup.clearAnimation();
                IntentTool.startSelectMobileActivity(ContactDetailActivity.this, bean, 1);
            }
        });

    }

    @Override
    protected void initData() {
        String uid = "";
        String uname = getIntent().getStringExtra("uname");
        if (!StringTools.isNullOrEmpty(uname)) {
//            bean = ContactController.getInstance().getContactByName(uname);
//            uid = bean.getUid();
            Logger.e(uname + "uid=0" + uid);
        }
        Logger.e(uname + "uid=1" + uid);
        ContactBean tempBean = (ContactBean) getIntent().getSerializableExtra("bean");
        if (null != tempBean) {
            bean = tempBean;
            uid = bean.getUid();
        }
        Logger.e(uname + "uid=2" + uid);
        if (bean == null) {
            bean = new ContactBean();
        }
        Logger.e(uname + "uid=3" + uid);
        if (StringTools.isNullOrEmpty(uid)) {
            uid = getIntent().getStringExtra("uid");
        }
        Logger.e(uname + "uid=4" + uid);
        uid = StringTools.toTrim(uid);
        uname = StringTools.toTrim(uname);

        UserController.getInstance().getPersonInfor(new ViewNetCallBack() {
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
                PersonBean info = (PersonBean) result;
                bean.setAvatar_original(info.getAvatar_middle());
                bean.setUid(info.getUid());
                bean.setUname(info.getUname());
                bean.setLogin(info.getLogin());
                bean.setLocation(info.getLocation());
                bean.setDepartment(info.getUser_department());
                bean.setEmpID(info.getEmail());
                bean.setMobile(info.getMobile());
                bean.setWorkphone(info.getWorkphone());
                bean.setPhoneext(info.getPhoneext());
                bean.setCompany(StringTools.toTrim(info.getCompany()));
                bean.setLocation(StringTools.toTrim(info.getLocation()));
                bean.setSignature(info.getIntro());
                uiInit();
            }
        }, uid, uname);
//        uiInit();
        String myUid = PreferceManager.getInsance().getValueBYkey(this, "uid");
        if (myUid.equals(uid)) {
            chat_btn.setVisibility(View.GONE);
        }
    }

    private void uiInit() {
        try {
            mainTitle.setCenter(bean.getUname());
            nameTv1.setText(bean.getUname());
            nameTv.setText(bean.getUname());
            suncompanyTv.setText(bean.getCompany());
            locationAddress.setText(bean.getLocation());
            signatureTv.setText(bean.getSignature());
            Picasso.with(this).load(bean.getAvatar_original()).into(icon);

            if (bean.isShowUserDepartMent()) {
                department.setText(ListUtil.isNullOrEmpty(bean.getUser_department()) ? "" : bean.getUser_department().get(0));
            } else {
                department.setText(ListUtil.isNullOrEmpty(bean.getDepartment()) ? "" : bean.getDepartment().get(0));
            }
            mobile.setText(StringTools.toTrim(bean.getMobile()));
            workMobile.setText(StringTools.backNotNullString(bean.getWorkphone()));
            fenMobile.setText(StringTools.backNotNullString(bean.getPhoneext()));
            if (bean.getLogin() != null) {
                emailAddress.setText(bean.getLogin());
            }

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            if (bean.getAvatar_original() != null && bean.getAvatar_original().trim().length() > 0) {
                asyncHttpClient.get(bean.getAvatar_original(), new BinaryHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        photoArray = bytes;
                        try {
                            dealWithPic();
                        } catch (Exception e) {
                            Logger.e(e.getLocalizedMessage(), e);
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Logger.e(throwable.getMessage());
                    }
                });
            }

        } catch (Exception e) {

            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    private void dealWithPic() throws Exception {
        ByteArrayInputStream isBm = new ByteArrayInputStream(photoArray);
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 40) {
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        photoArray = baos.toByteArray();
    }


    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {
        String[] mobiles = mobile.getText().toString().trim().split(",");
        if (mobiles.length == 2) {
            toContacts(nameTv.getText().toString(), mobiles[0], mobiles[1], emailAddress.getText().toString());
        } else {
            toContacts(nameTv.getText().toString(), mobiles[0], null, emailAddress.getText().toString());
        }


    }


    private void toContacts(String given_name, String mobile_number, String mobileWorkNum, String emailAddress) {
        Intent it = new Intent(Intent.ACTION_INSERT, Uri.withAppendedPath(
                Uri.parse("content://com.android.contacts"), "contacts"));
        it.setType("vnd.android.cursor.dir/person");
        it.putExtra(android.provider.ContactsContract.Intents.Insert.NAME, given_name);
        it.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE,
                mobile_number);
        it.putExtra(
                android.provider.ContactsContract.Intents.Insert.SECONDARY_PHONE,
                mobileWorkNum);
        it.putExtra(android.provider.ContactsContract.Intents.Insert.EMAIL, emailAddress);
        if (photoArray != null && photoArray.length > 0) {
            ContentValues values = new ContentValues();
            values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.Contacts.Photo.PHOTO, photoArray);
            ArrayList<ContentValues> data = new ArrayList<ContentValues>();
            data.add(values);
            it.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, data);
        }
        startActivity(it);
    }


    @Override
    public void clickCenterTitle() {

    }


    public void phoneClick(View view) {
        switch (view.getId()) {
            case R.id.mobileLayout:
                if (!StringTools.isNullOrEmpty(bean.getMobile())) {
                    bean.setTypeCall(10);
                    ll_popup.startAnimation(AnimationUtils.loadAnimation(ContactDetailActivity.this, R.anim.translate_popupwindow));
                    popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 10);
//                    IntentTool.startSelectMobileActivity(this, bean, 0);
                }
                break;
            case R.id.phoneLayout:
                if (!StringTools.isNullOrEmpty(bean.getWorkphone())) {
                    bean.setTypeCall(20);
                    IntentTool.startSelectMobileActivity(this, bean, 0);
                }

                break;
            case R.id.extLayout:
                if (!StringTools.isNullOrEmpty(bean.getPhoneext())) {
                    bean.setTypeCall(30);
                    IntentTool.startSelectMobileActivity(this, bean, 0);
                }
                break;

            case R.id.emailLayout:
                if (emailAddress.getText() != null) {
                    Common.email(this, emailAddress.getText().toString());
                } else {
                    ToastTool.show("邮件地址不能为空！");
                    return;
                }
                break;

        }

    }
}
