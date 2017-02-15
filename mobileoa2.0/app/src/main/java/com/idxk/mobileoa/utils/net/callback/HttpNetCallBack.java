package com.idxk.mobileoa.utils.net.callback;

import com.idxk.mobileoa.exception.NetException;

import java.util.Map;

/**
 * http 接口的总纲
 */
public interface HttpNetCallBack {

    /**
     * @param url              get请求的url
     * @param params           get请求的参数
     * @param timeOut          get请求的超时时间  因为可能 一些接口设置的超时时间 不一样
     * @param responesCallBack get请求的回调函数
     * @param cach             是否进行数据的缓存
     * @throws NetException
     */
    void get(String url, Map<String, Object> params, long timeOut, boolean cach, NetCallback responesCallBack) throws NetException;

    /**
     * @param url              get请求的url
     * @param params           get请求的参数
     * @param header           get请求的头参数
     * @param timeOut          get请求的超时时间  因为可能 一些接口设置的超时时间 不一样
     * @param cach             是否进行数据的缓存
     * @param responesCallBack get请求的回调函数
     * @throws NetException
     */
    void getWithHeader(String url, Map<String, Object> params, Map<String, String> header,
                       long timeOut, boolean cach, NetCallback responesCallBack) throws NetException;


    /**
     * @param url              post请求的url
     * @param params           post请求的参数
     * @param timeOut          post请求的超时时间  因为可能 一些接口设置的超时时间 不一样
     * @param responesCallBack post请求的回调函数
     * @param cach             是否进行数据的缓存
     * @throws NetException
     */
    void post(String url, Map<String, Object> params, long timeOut, boolean cach, NetCallback responesCallBack) throws NetException;

    /**
     * @param url              post请求的url
     * @param params           post请求的参数
     * @param header           post请求的头参数
     * @param timeOut          post请求的超时时间  因为可能 一些接口设置的超时时间 不一样
     * @param cach             是否进行数据的缓存
     * @param responesCallBack post请求的回调函数
     * @throws NetException
     */
    void postWithHeader(String url, Map<String, Object> params, Map<String, String> header,
                        long timeOut, boolean cach, NetCallback responesCallBack) throws NetException;

    /**
     * 请求的取消 如dialog的消失 或者 页面的跳转 调用这个函数
     */
    void cancle();


}
