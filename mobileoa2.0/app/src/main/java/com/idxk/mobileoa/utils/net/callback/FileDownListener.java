package com.idxk.mobileoa.utils.net.callback;

import java.io.File;

/**
 * 文件的离线下载
 */
public interface FileDownListener {
    void starDownLoad();
    void endDownLoad(File file, boolean isSuess);
}
