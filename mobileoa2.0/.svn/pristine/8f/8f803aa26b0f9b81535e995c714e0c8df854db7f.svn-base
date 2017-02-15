package com.idxk.mobileoa.dao;

import com.idxk.mobileoa.utils.cache.db.SqlRequest;
import com.idxk.mobileoa.utils.cache.db.callback.DbOpration;
import com.idxk.mobileoa.utils.cache.db.factory.DBFactory;

/**
 * 设计dao层的意义是 灵活的修改格式
 */
public class BaseDao {


    protected DbOpration opration;

    public BaseDao() {

        opration = DBFactory.getInstance().getDbcommon(new String[]{},
                new String[]{}, "user.db",
                1);
    }

    public void crearCach() {

        String createSql = "CREATE TABLE IF NOT EXISTS cach "
                + "( id INTEGER  primary key  AUTOINCREMENT," +
                "uid varchar  ," +
                "mId varchar," +
                " uName varchar," +
                "isU INTEGER,ctime INTEGER)";


        opration.execSql(new SqlRequest(createSql, new String[]{}));
    }


    public void clearContact() {
        String sql1 = "DROP TABLE IF EXISTS person";
        String sql2 = "DROP TABLE IF EXISTS pdmapping";
        String sql = "CREATE TABLE IF NOT EXISTS person "
                + "(uid varchar PRIMARY KEY ," +
                "uname varchar," +
                " login varchar," +
                " mobile varchar," +
                " workphone varchar," +
                " phoneext varchar," +
                "first_letter varchar," +
                "url varchar," +
                "department varchar)";
        String sqlMapping = "CREATE TABLE IF NOT EXISTS pdmapping "
                + "(" +
                "id INTEGER  primary key  AUTOINCREMENT," +
                "uid varchar ," +
                "department varchar)";
        opration.execSql(new SqlRequest(sql1, new String[]{}));
        opration.execSql(new SqlRequest(sql2, new String[]{}));
        opration.execSql(new SqlRequest(sql, new String[]{}));
        opration.execSql(new SqlRequest(sqlMapping, new String[]{}));
    }

    protected void createContactsTable() throws Exception {
        String sql21 = "DROP TABLE IF EXISTS departments";
        String sqlDepartments = "CREATE TABLE IF NOT EXISTS departments "
                + "(" +
                "id INTEGER  primary key  AUTOINCREMENT," +
                "uid varchar ," +
                "department varchar)";
        opration.execSql(new SqlRequest(sql21, new String[]{}));
        opration.execSql(new SqlRequest(sqlDepartments, new String[]{}));
    }


}
