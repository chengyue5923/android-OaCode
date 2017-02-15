package com.idxk.mobileoa.utils.cache.db.callback;


import com.idxk.mobileoa.utils.cache.db.SqlRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by Wesley on 14-1-26.
 */
public interface DbOpration {
    List<Map<String, String>> querySql(SqlRequest request);

    /**
     * 批量查询
     *
     * @param request sqlrequest集合
     * @return 结果集合
     */
    List<Map<String, String>> querySqls(SqlRequest[] request);


    int queryCount(SqlRequest request);

    boolean execSql(SqlRequest request);

    boolean execSqls(SqlRequest[] requests);

    boolean execSqls(List<SqlRequest> requests);

    boolean execSqls(String[] requests);

    String[][] querySqlNew(SqlRequest request);

    void close();

}
