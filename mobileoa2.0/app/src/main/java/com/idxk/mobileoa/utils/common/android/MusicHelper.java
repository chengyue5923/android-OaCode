package com.idxk.mobileoa.utils.common.android;

import android.database.Cursor;
import android.provider.MediaStore;

import com.idxk.mobileoa.model.bean.MusicInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lenovo on 2015/6/9.
 */
public class MusicHelper {
    public static ArrayList<MusicInfo> getMusicList(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ArrayList<MusicInfo> musicList = new ArrayList<MusicInfo>();
        while (cursor.moveToNext()) {
            MusicInfo music = new MusicInfo();
            music.songId = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));
            music.albumId = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            music.duration = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));
            music.musicName = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE));
            music.artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));

            String filePath = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));
            music.data = filePath;
            String folderPath = filePath.substring(0,
                    filePath.lastIndexOf(File.separator));
            music.folder = folderPath;
            music.musicNameKey = music.musicName;
            music.artistKey = music.artist;
            music.size = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            musicList.add(music);
        }
        cursor.close();
        return musicList;
    }
}
