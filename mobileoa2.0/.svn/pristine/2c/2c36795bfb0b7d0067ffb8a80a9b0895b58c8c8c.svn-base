package com.idxk.mobileoa.android.ui.views.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.idxk.mobileoa.R;
import com.idxk.mobileoa.model.bean.AttachBean;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import org.apache.http.Header;

import java.util.List;

/**
 * Created by lenovo on 2015/4/7.
 */
public class ViewPagerAdapter extends PagerAdapter {


    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    private List<AttachBean> attachBeans;

    private Context context;

    private RotateAnimation refreshingAnimation;


    public ViewPagerAdapter(List<AttachBean> attachBeans, Context context) {
        this.attachBeans = attachBeans;
        this.context = context;
        this.refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.rotating);
    }


    @Override
    public int getCount() {
        return attachBeans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.zoom_image_layout, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.zoom_image_view);
        final LinearLayout progressLayout = (LinearLayout) view.findViewById(R.id.progressLayout);
        final ImageView progressBar = (ImageView) view.findViewById(R.id.progressBar);
        String url = attachBeans.get(position).getAttach_middle();
        asyncHttpClient.get(url, new BinaryHttpResponseHandler() {


            @Override
            public void onStart() {
                super.onStart();
                progressLayout.setVisibility(View.VISIBLE);
                progressBar.startAnimation(refreshingAnimation);
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] data) {
                progressLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                imageView.setImageBitmap(bitmap);
                refreshingAnimation.cancel();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

        Logger.e(attachBeans.get(position).getAttach_middle());
//        Picasso.with(context).load(attachBeans.get(position).getAttach_middle()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
