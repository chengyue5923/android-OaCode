package com.idxk.mobileoa.utils.common.android;


import com.google.gson.Gson;
import com.idxk.mobileoa.exception.ResolveException;
import com.idxk.mobileoa.utils.common.java.StringTools;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * gson的操作工具类
 *
 * @author 林夕   下午2:03:09   2013年12月6日
 */
public class GsonTool {

    /**
     * 将 json转化成实体
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToEntity(String json, Class<T> clazz) throws ResolveException {
//        Logger.e("--json--" + json);
        if (StringTools.isNullOrEmpty(json)) {
            throw new ResolveException("json转换成实体" + json);
        }
        try {
            Gson g = new Gson();
            return g.fromJson(json, clazz);
        } catch (Exception e) {


            Logger.e(e.getLocalizedMessage(), e);
            throw new ResolveException("json转换成实体" + json);

        }
    }


    public static <T> List<T> jsonToArrayEntity(String jsonArray, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            JSONArray ja = new JSONArray(jsonArray);
            for (int i = 0; i < ja.length(); i++) {
                list.add(jsonToEntity(ja.get(i).toString(), clazz));
            }

        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

        Logger.e("---lis size---" + list.size());
        return list;
    }


    public static List<String> jsonToStringArrayEntity(String jsonArray) {

        List<String> list = new ArrayList<>();
        try {
            JSONArray ja = new JSONArray(jsonArray);
            for (int i = 0; i < ja.length(); i++) {
                list.add(ja.get(i).toString());
            }
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

        Logger.e("---lis size---" + list.size());
        return list;
    }


    /**
     * 将实体转换成json
     *
     * @param clazz
     * @return
     */
    public static <T> String entityToJson(T clazz) throws ResolveException {
        try {
            Gson g = new Gson();
            return g.toJson(clazz);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
            throw new ResolveException("实体转换成json");
        }

    }

    /**
     * 将 json 转换成hasmap
     *
     * @param json
     * @return
     */
    public static HashMap jsonToHas(String json) throws ResolveException {
        try {
            Gson gson = new Gson();
            HashMap hm = gson.fromJson(json, HashMap.class);
            return hm;
        } catch (Exception e) {
            // TODO: handle exception
            Logger.e(e.getLocalizedMessage(), e);
            throw new ResolveException("json转成hasmap" + json);
        }

    }

    /**
     * 实体转换成 hasmap
     *
     * @param t
     * @return
     */
    public static <T> HashMap EntityToHas(T t) throws ResolveException {
        String json = entityToJson(t);
        HashMap hm = jsonToHas(json);
        return hm;
    }


}
