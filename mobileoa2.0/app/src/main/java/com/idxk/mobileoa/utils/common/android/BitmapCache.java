package com.idxk.mobileoa.utils.common.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;

import com.mogujie.tt.ui.adapter.album.ImageItem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;

public class BitmapCache {
    public static int num = 9;
    public static LinkedList<ImageItem> tempSelectBitmap = new LinkedList<>();   //选择的图片的临时列表
    public Handler h = new Handler();
    private HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<>();


    public void put(String path, Bitmap bmp) {
        if (!TextUtils.isEmpty(path) && bmp != null) {
            imageCache.put(path, new SoftReference<>(bmp));
        }
    }

    public void displayBmp(final ImageView iv, final String thumbPath,
                           final String sourcePath, final ImageCallback callback) {
        if (TextUtils.isEmpty(thumbPath) && TextUtils.isEmpty(sourcePath)) {
            Logger.e("no paths pass in");
            return;
        }

        final String path;
        final boolean isThumbPath;
        if (!TextUtils.isEmpty(thumbPath)) {
            path = thumbPath;
            isThumbPath = true;
        } else if (!TextUtils.isEmpty(sourcePath)) {
            path = sourcePath;
            isThumbPath = false;
        } else {
            // iv.setImageBitmap(null);
            return;
        }

        if (imageCache.containsKey(path)) {
            SoftReference<Bitmap> reference = imageCache.get(path);
            Bitmap bmp = reference.get();
            if (bmp != null) {
                if (callback != null) {
                    callback.imageLoad(iv, bmp, sourcePath);
                }
                iv.setImageBitmap(bmp);
                Logger.e("hit cache");
                return;
            }
        }
        iv.setImageBitmap(null);

        new Thread() {
            Bitmap thumb;

            public void run() {

                try {
                    if (isThumbPath) {
                        thumb = BitmapFactory.decodeFile(thumbPath);
                        if (thumb == null) {
                            thumb = revitionImageSize(sourcePath);
                        }
                    } else {
                        thumb = revitionImageSize(sourcePath);
                    }
                } catch (Exception e) {

                }
                Logger.e("-------thumb------" + thumb);
                put(path, thumb);

                if (callback != null) {
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.imageLoad(iv, thumb, sourcePath);
                        }
                    });
                }
            }
        }.start();

    }

    public Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 256)
                    && (options.outHeight >> i <= 256)) {
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }

    public interface ImageCallback {
        void imageLoad(ImageView imageView, Bitmap bitmap,
                       Object... params);
    }
}
