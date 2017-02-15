package com.idxk.mobileoa.utils.net.connect.http.file;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.idxk.mobileoa.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * imageLoader
 */
public class ImageLoaderManager {

    static ImageLoaderManager instance;
    DisplayImageOptions options;

    DisplayImageOptions ttChatOptions;

    public ImageLoaderManager() {
    }

    /**
     * 获取 图片下载的单例
     *
     * @return
     */
    public static ImageLoaderManager getInstance() {
        if (instance == null)
            instance = new ImageLoaderManager();
        return instance;
    }


    /**
     * 实例化 loader
     *
     * @param subImage  下载见 需要的图片
     * @param nullImage 没有图片
     * @param failImage 失败
     * @param round     圆角
     */
    public void init(int subImage, int nullImage, int failImage, int round) {
        options = new DisplayImageOptions.Builder()
                .showStubImage(subImage)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(nullImage)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(failImage)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(round))  // 设置成圆角图片
                .build();
    }

    /**
     * 实例化 loader  下载前的 imageid 下载失败的imageid   方脚
     *
     * @param subImage  下载见 需要的图片
     * @param failImage 失败
     */
    public void init(int subImage, int failImage) {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(subImage)          // 设置图片下载期间显示的图片
                .showImageOnFail(failImage)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)                          // 设置下载的图片是否缓存在SD卡中
                .build();

        ttChatOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.tt_default_user_portrait_corner)          // 设置图片下载期间显示的图片
                .showImageOnFail(R.drawable.tt_default_user_portrait_corner)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)                          // 设置下载的图片是否缓存在SD卡中
                .build();
    }

    /**
     * @param url
     * @param imageView
     */
    public void disPlayImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, options, new ImageLisener());
    }


    public void disPlayImage(ImageView imageView, String url) {
        ImageLoader.getInstance().displayImage(url, imageView, ttChatOptions, new ImageLisener());
    }


    private class ImageLisener implements ImageLoadingListener {

        private ImageLisener() {

        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            FadeInBitmapDisplayer.animate(view, 500);

        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {

        }
    }


}
