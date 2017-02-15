package com.idxk.mobileoa.config.enums;

/**
 * 关于文件名的类型的枚举
 */
public enum  FileType {

    db(0),
    office(1),
    image(2)

    ;
    int type;
    FileType(int type) {
        this.type = type;
    }
}
