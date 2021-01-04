package com.bonc.cloudhandlesql.service;

import com.bonc.cloudhandlesql.pojo.SqlData;

import java.util.List;

/**
 * Author: zlp
 * Date: 2021-01-04 16:42
 * Description:
 */
public interface SqlDataService extends IBaseService<SqlData>{

    List<SqlData> getAllSqlDatasToRequest();
}
