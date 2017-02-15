package com.idxk.mobileoa.android.ui.activity;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.MobileAdapter;
import com.idxk.mobileoa.model.bean.ContactBean;
import com.idxk.mobileoa.utils.common.android.Common;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.ToastTool;
import com.idxk.mobileoa.utils.common.java.ListUtil;

import java.util.List;


/**
 * Created by Administrator on 2015/3/26.
 */
public class SelectMobileActivity extends BaseListViewActivity implements AdapterView.OnItemClickListener{

     int type;
    ListView listView;
    List<ContactBean.MoileNum> values;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_select_mobile);

        TextView title = (TextView)findViewById(R.id.titleTv);
        listView = (ListView)findViewById(R.id.lv);
        type = getIntent().getIntExtra("type",0);
        if (type==0){
            title.setText("选择号码进行拨打电话");
        }else {
            title.setText("选择号码进行发短信");
        }

    }

    @Override
    protected void initData() {
        ContactBean bean = (ContactBean)getIntent().getSerializableExtra("value");
        Logger.e("=bean=="+bean);
        if (type==0){
            values = bean.getPNByType(bean.getTypeCall());
        }else{
            values =bean.getmobiles();
        }
        if (ListUtil.isNullOrEmpty(values)){
            return;
        }
        if (values.size()==1){
            if (type==0){
                Common.call(this, values.get(0).getNum());
                finish();
                return;
            }

            Common.sendSMS("",this,values.get(0).getNum());
            finish();
            return;
        }


        MobileAdapter mobileAdapter = new MobileAdapter(this,values);
        listView.setAdapter(mobileAdapter);
        listView.setOnItemClickListener(this);

    }


    public void cancleClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContactBean.MoileNum bean = values.get(position);
        if (bean.getTyle()== ContactBean.MobileTyle.EXT){
            ToastTool.show("请用内线电话拨打");
            return;
        }

        if (type==0){
            Common.call(this, bean.getNum());
            finish();
            return;
        }

        Common.sendSMS("",this,bean.getNum());
        finish();




    }

    @Override
    public void tryAgin(View view) {

    }
}
