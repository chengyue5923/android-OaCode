package com.idxk.mobileoa.utils.net.callback;
/**
 *
 */

/**
 * 网络的callback---回调
 *
 * @author Administrator
 */
public interface NetCallback {


    /**
     * 得到网络返回结果
     *
     * @param jsonstring
     */
    void dealReslut(String jsonstring);

    /**
     * 网络返回异常 需要传到外面
     *
     * @param e
     */
    void dealFailer(Exception e);


    /**
     * 访问开始
     */
    void onstart();

    /**
     * 访问结束
     */
    void onEnd();
}
