package com.idxk.mobileoa.config.enums;

/**
 * Created by Wesley on 2015/3/4.
 */
public enum HttpMethod {


    GET(0),
    POST(1);
    private int method;

    HttpMethod(int method) {
        this.method = method;
    }

    public int getMethod() {
        return method;
    }


    public static HttpMethod getMethodByTypt(int method){
        if (method==0){
            return GET;
        }
        if (method==1){
            return POST;
        }
        return GET;
    }




}
