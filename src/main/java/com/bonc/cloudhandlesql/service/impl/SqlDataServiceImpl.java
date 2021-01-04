package com.bonc.cloudhandlesql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.cloudhandlesql.dao.mapper.SqlDataMapper;
import com.bonc.cloudhandlesql.pojo.SqlData;
import com.bonc.cloudhandlesql.service.SqlDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zlp
 * Date: 2021-01-04 16:43
 * Description:
 */
@Service
public class SqlDataServiceImpl extends ServiceImpl<SqlDataMapper,SqlData> implements SqlDataService {

    @Autowired
    private SqlDataMapper sqlDataMapper;

    @Override
    public List<SqlData> getAllSqlDatasToRequest() {
         return sqlDataMapper.getAllSqlDatas();
    }
}
