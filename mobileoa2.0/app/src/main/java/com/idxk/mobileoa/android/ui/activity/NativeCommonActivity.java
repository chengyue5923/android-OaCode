package com.idxk.mobileoa.android.ui.activity;

import android.content.ContentResolver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.idxk.mobileoa.R;
import com.idxk.mobileoa.android.ui.views.adapter.AlbumGridViewAdapter;
import com.idxk.mobileoa.android.ui.views.adapter.MusicAdapter;
import com.idxk.mobileoa.android.ui.views.adapter.PhoneMemoryAdapter;
import com.idxk.mobileoa.android.ui.views.widget.MainTitleView;
import com.idxk.mobileoa.model.bean.FileInfo;
import com.idxk.mobileoa.model.bean.MusicInfo;
import com.idxk.mobileoa.utils.common.android.BitmapCache;
import com.idxk.mobileoa.utils.common.android.Logger;
import com.idxk.mobileoa.utils.common.android.MusicHelper;
import com.mogujie.tt.ui.adapter.album.AlbumHelper;
import com.mogujie.tt.ui.adapter.album.ImageBucket;
import com.mogujie.tt.ui.adapter.album.ImageItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by lenovo on 2015/6/10.
 */
public class NativeCommonActivity extends BaseActivity implements MainTitleView.OnTitleClick, AlbumGridViewAdapter.OnItemClickListener {
    private static final String[] proj_music = new String[]{
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.SIZE};
    private MainTitleView mainTitleView;
    private AlbumHelper helper;
    private List<ImageBucket> contentList;
    private ArrayList<ImageItem> dataList;
    private AlbumGridViewAdapter gridImageAdapter;
    private GridView gridView;
    private Button okButton;
    private int PAGE = 0;
    private String title = "";
    private ListView musicListView;
    private MusicAdapter musicAdapter;
    private List<MusicInfo> musicInfoList;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ListView memoryListView;
    private PhoneMemoryAdapter phoneMemoryAdapter;
    private List<FileInfo> fileInfoList;

    private boolean useKeyDown = false;

    private String mSdcardRootPath;

    private String mLastFilePath;    //当前显示的路径
    private TextView filePath;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            phoneMemoryAdapter.notifyDataSetChanged();
            filePath.setText(mLastFilePath);
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_album);
        mainTitleView = (MainTitleView) id2v(R.id.ablum_titleHome);
        mainTitleView.setOnTitleClickLisener(this);
        if (getIntent() != null) {
            PAGE = getIntent().getIntExtra("page", 0);
            title = getIntent().getStringExtra("title");
            mainTitleView.setCenterTitle(title);
            if (PAGE == 2) {
                mSdcardRootPath = "/";
            } else if (PAGE == 3) {
                mSdcardRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            }
        }
        switch (PAGE) {
            case 0:
                gridView = (GridView) id2v(R.id.ablum_gridView);
                okButton = (Button) findViewById(R.id.ok_button);
                gridView.setVisibility(View.VISIBLE);
                break;
            case 1:
                musicListView = (ListView) id2v(R.id.musicListView);
                musicListView.setVisibility(View.VISIBLE);
                musicInfoList = new ArrayList<>();
                musicAdapter = new MusicAdapter(musicInfoList, this);
                musicListView.setAdapter(musicAdapter);
                break;
            case 2:
            case 3:
                filePath = (TextView) id2v(R.id.filePath);
                filePath.setVisibility(View.VISIBLE);
                memoryListView = (ListView) id2v(R.id.memoryListView);
                memoryListView.setVisibility(View.VISIBLE);
                fileInfoList = new ArrayList<>();
                phoneMemoryAdapter = new PhoneMemoryAdapter(fileInfoList, this);
                memoryListView.setAdapter(phoneMemoryAdapter);
                memoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final FileInfo fileInfo = fileInfoList.get(position);
                        if (fileInfo.isDirectory) {
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {
                                    scanFiles(fileInfo.filePath);
                                }
                            });
                        } else {
                            Toast.makeText(NativeCommonActivity.this, fileInfo.filePath, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PAGE == 2 || PAGE == 3) {
            useKeyDown = true;
        }
        if (BitmapCache.tempSelectBitmap.size() > 0) {
            isShowOkBt();
        }
    }

    @Override
    protected void initData() {
        switch (PAGE) {
            case 0:
                helper = AlbumHelper.getHelper(this);
                contentList = helper.getImagesBucketList(false);
                dataList = new ArrayList<>();
                for (int i = 0; i < contentList.size(); i++) {
                    dataList.addAll(contentList.get(i).imageList);
                }
                gridImageAdapter = new AlbumGridViewAdapter(this, dataList, BitmapCache.tempSelectBitmap);
                gridImageAdapter.setOnItemClickListener(this);
                gridView.setAdapter(gridImageAdapter);
                break;
            case 1:
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        ContentResolver cr = NativeCommonActivity.this.getContentResolver();
                        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        List<MusicInfo> list = MusicHelper.getMusicList(cr.query(uri, proj_music,
                                "", null,
                                MediaStore.Audio.Media.ARTIST_KEY));
                        for (MusicInfo musicInfo : list) {
                            Logger.e(musicInfo.toString());
                        }
                        musicAdapter.setData(list);
                        Logger.e("===total size:" + list.size());
                    }
                });
                break;
            case 2:
            case 3:
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        scanFiles(mSdcardRootPath);
                    }
                });
                break;
        }

    }


    private synchronized void scanFiles(String mSdcardRootPath) {
        mLastFilePath = mSdcardRootPath;
        File[] files = folderScan(mSdcardRootPath);
        if (files != null && files.length > 0) {
            if (!fileInfoList.isEmpty())
                fileInfoList.clear();
            for (File file : files) {
                String fileAbsolutePath = file.getAbsolutePath();
                String fileName = file.getName();
                boolean isDirectory = file.isDirectory();
                FileInfo fileInfo = new FileInfo(fileAbsolutePath, fileName, isDirectory);
                fileInfoList.add(fileInfo);
            }
            handler.sendEmptyMessage(0);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode()
                == KeyEvent.KEYCODE_BACK) {
            if (useKeyDown) {
                backProcess();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backProcess() {
        if (!mLastFilePath.equals(mSdcardRootPath)) {
            File thisFile = new File(mLastFilePath);
            String parentFilePath = thisFile.getParent();
            scanFiles(parentFilePath);
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private File[] folderScan(String path) {
        File file = new File(path);
        if (file.canRead()) {
            File[] files = file.listFiles();
            return files;
        }
        return null;
    }

    @Override
    public void clickLeft() {
        this.finish();
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickCenterTitle() {

    }

    @Override
    public void onItemClick(ToggleButton toggleButton, int position, boolean isChecked, Button chooseBt) {
        if (BitmapCache.tempSelectBitmap.size() >= BitmapCache.num) {
            toggleButton.setChecked(false);
            chooseBt.setVisibility(View.GONE);
            if (!removeOneData(dataList.get(position))) {
                Toast.makeText(NativeCommonActivity.this, getResources().getString(R.string.only_choose_num),
                        Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (isChecked) {
            chooseBt.setVisibility(View.VISIBLE);
            BitmapCache.tempSelectBitmap.add(dataList.get(position));
            okButton.setText(getResources().getString(R.string.sure) + "(" + BitmapCache.tempSelectBitmap.size()
                    + "/" + BitmapCache.num + ")");
        } else {
            BitmapCache.tempSelectBitmap.remove(dataList.get(position));
            chooseBt.setVisibility(View.GONE);
            okButton.setText(getResources().getString(R.string.sure) + "(" + BitmapCache.tempSelectBitmap.size() + "/" + BitmapCache.num + ")");
        }

        isShowOkBt();
    }

    public void isShowOkBt() {
        if (BitmapCache.tempSelectBitmap.size() > 0) {
            okButton.setText(getResources().getString(R.string.sure) + "(" + BitmapCache.tempSelectBitmap.size() + "/" + BitmapCache.num + ")");
            okButton.setPressed(true);
            okButton.setClickable(true);
            okButton.setTextColor(Color.WHITE);
        } else {
            okButton.setText(getResources().getString(R.string.sure) + "(" + BitmapCache.tempSelectBitmap.size() + "/" + BitmapCache.num + ")");
            okButton.setPressed(false);
            okButton.setClickable(false);
            okButton.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }


    private boolean removeOneData(ImageItem imageItem) {
        if (BitmapCache.tempSelectBitmap.contains(imageItem)) {
            BitmapCache.tempSelectBitmap.remove(imageItem);
            okButton.setText(getResources().getString(R.string.sure) + "(" + BitmapCache.tempSelectBitmap.size() + "/" + BitmapCache.num + ")");
            return true;
        }
        return false;
    }

}
