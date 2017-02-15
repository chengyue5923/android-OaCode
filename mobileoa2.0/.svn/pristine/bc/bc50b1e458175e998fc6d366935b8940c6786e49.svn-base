package com.idxk.mobileoa.dao;

import com.idxk.mobileoa.utils.cache.db.SqlRequest;
import com.idxk.mobileoa.utils.common.java.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/17.
 */
public class CachDao extends BaseDao{

    public CachDao() {
        super();
    }

    public void insert(String uid,String name,String mId,boolean isUser){

        clearMe();



        if (quaryCount(uid,mId,isUser)>0){
            return;
        }
        String sql = "INSERT INTO cach " +
                "(uid,mId,uName,isU,ctime) VALUES(?, ?, ?,?,?)";
         opration.execSql(new SqlRequest(sql,new Object[]{uid,mId,name,isUser?1:0,(int)(System.currentTimeMillis()/1000)}));
    }
    public void inserts(String uid,String name,String mId,boolean isUser){

        clearMe();
        if (quaryCount(uid,mId,isUser)>0){
            return;
        }
        String sql = "INSERT INTO cach " +
                "(uid,mId,uName,isU) VALUES(?, ?, ?,?)";
        opration.execSql(new SqlRequest(sql,new Object[]{uid,mId,name,isUser?1:0}));
    }

    public int quaryCount(String uid,String mId,boolean isUser){
//        clearMe();
        String sql = "select  count(*) from cach where uid=? and mId=? and isU=?";
        return opration.queryCount(new SqlRequest(sql,new String[]{uid,mId,isUser?String.valueOf(1):String.valueOf(0)}));

    }

    public List<String[]> quaryAllByMid(String mId){
        clearMe();
        String sql = "select * from cach where mId=? order by ctime desc";
        List<String[]> result = new ArrayList<String[]>();
        List<Map<String,String>> list = opration.querySql(new SqlRequest(sql,new String[]{mId}));
        if (ListUtil.isNullOrEmpty(list)){
            return result;
        }
        for (Map<String,String> map:list){
            String[] array = new String[3];
            array[0] = map.get("uid");
            array[1] = map.get("uName");
            array[2] = map.get("isU");
            result.add(array);
        }
        return result;
    }
    public List<String[]> quaryAllBynotId(String uid,String mId){
        clearMe();
        String sql = "select * from cach where mId=? and uid !=? order by ctime desc";
        List<String[]> result = new ArrayList<String[]>();
        List<Map<String,String>> list = opration.querySql(new SqlRequest(sql,new String[]{mId,uid}));
        if (ListUtil.isNullOrEmpty(list)){
            return result;
        }
        for (Map<String,String> map:list){
            String[] array = new String[3];
            array[0] = map.get("uid");
            array[1] = map.get("uName");
            array[2] = map.get("isU");
            result.add(array);
        }
        return result;
    }

    public String[] quaryOneById(String uid,String mId){
        clearMe();
        String sql = "select * from cach where mId=? and uid =? ";
        List<String[]> result = new ArrayList<String[]>();
        List<Map<String,String>> list = opration.querySql(new SqlRequest(sql,new String[]{mId,uid}));
        if (ListUtil.isNullOrEmpty(list)){
            return new String[0];
        }
        for (Map<String,String> map:list){
            String[] array = new String[3];
            array[0] = map.get("uid");
            array[1] = map.get("uName");
            array[2] = map.get("isU");
            result.add(array);
        }
        return result.get(0);
    }

    private void clearMe(){
        crearCach();

    }





}
