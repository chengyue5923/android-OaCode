package com.idxk.mobileoa.android.ui.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.logic.controller.FeedBackController;
import com.idxk.mobileoa.model.bean.FeedBackModel;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.net.callback.ViewNetCallBack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2015/4/9.
 */
public class FeedBackActivity extends BaseActivity implements MainTitleView.OnTitleClick, View.OnClickListener, AdapterView.OnItemClickListener {

    private MainTitleView feedBack_mainTitleView;

    private EditText feedBackContent;

    private RelativeLayout adviseLayout;

    private ImageView feedBack_rotatePic;

    private boolean arrowDown = false;

    private SimpleAdapter simpleAdapter;

    private List<Map<String, Object>> mapList;

    private ListView feedBackTypeListView;

    private TextView improveAdvise;

    private int typeId = 1;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_feedback);
        feedBack_mainTitleView = (MainTitleView) id2v(R.id.feedBack_mainTitleView);
        feedBack_mainTitleView.setOnTitleClickLisener(this);

        feedBack_mainTitleView.setRightTv(getResources().getString(R.string.fresh_send));

        feedBackContent = (EditText) id2v(R.id.feedBack_content);
        adviseLayout = (RelativeLayout) id2v(R.id.adviseLayout);
        adviseLayout.setOnClickListener(this);

        feedBack_rotatePic = (ImageView) id2v(R.id.feedBack_rotatePic);

        feedBackTypeListView = (ListView) id2v(R.id.feedBackTypeListView);
        feedBackTypeListView.setOnItemClickListener(this);

        mapList = new ArrayList<Map<String, Object>>();
        simpleAdapter = new SimpleAdapter(this, mapList, R.layout.adapter_item_feedbacktype, new String[]{"title"}, new int[]{R.id.feedBack_item});
        feedBackTypeListView.setAdapter(simpleAdapter);

        improveAdvise = (TextView) id2v(R.id.improveAdvise);
    }

    @Override
    protected void initData() {
        FeedBackController.getInstance().getFeedBackType(new ViewCallBackGetFeedBackType());

    }

    @Override
    public void clickLeft() {
        this.finish();
    }

    @Override
    public void clickRight() {
        if (feedBackContent.getText() != null && feedBackContent.getText().length() > 0) {
            FeedBackController.getInstance().sendFeedBackContent(new ViewCallBackPostFeedBackContent(), typeId, feedBackContent.getText().toString());
        } else {
            ToastTool.show("输入内容不能为空");
        }

    }

    @Override
    public void clickCenterTitle() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adviseLayout:
                loadAnimation();
                break;
        }
    }

    private void loadAnimation() {
        if (arrowDown) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate_feedback_second);
            anim.setFillAfter(true);
            feedBack_rotatePic.startAnimation(anim);
            arrowDown = !arrowDown;
            feedBackTypeListView.setVisibility(View.GONE);
        } else {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate_feedback);
            anim.setFillAfter(true);
            feedBack_rotatePic.startAnimation(anim);
            arrowDown = !arrowDown;
            anim.cancel();
            feedBackTypeListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        improveAdvise.setText(mapList.get(position).get("title").toString());
        String type = mapList.get(position).get("id").toString();
        typeId = Integer.valueOf(type);
        loadAnimation();
    }


    private class ViewCallBackPostFeedBackContent implements ViewNetCallBack {

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
        public void onData(Serializable result,boolean b,Object o) {
            FeedBackModel feedBackModel = (FeedBackModel) result;
//            if (feedBackModel.getStatus() == 1) {
////                ToastTool.show("发送成功");
//                ToastTool.show("发送成功");
//            } else if (feedBackModel.getStatus() == 2) {
//                ToastTool.show("发送失败，沒有反馈内容");
//            } else if (feedBackModel.getStatus() == 3) {
//                ToastTool.show("失败");
//            }

            finish();
            Logger.e(feedBackModel.toString());

        }
    }

    private class ViewCallBackGetFeedBackType implements ViewNetCallBack {

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
        public void onData(Serializable result,boolean b,Object o) {
            List<FeedBackModel> feedBackModels = (List<FeedBackModel>) result;
            Logger.e(feedBackModels.toString());
            mapList.clear();
            if (feedBackModels.get(0).getType_name() != null) {
                for (FeedBackModel feedBackModel : feedBackModels) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", feedBackModel.getType_name());
                    map.put("id", feedBackModel.getType_id());
                    mapList.add(map);
                }
                simpleAdapter.notifyDataSetChanged();
            }
        }
    }
}
