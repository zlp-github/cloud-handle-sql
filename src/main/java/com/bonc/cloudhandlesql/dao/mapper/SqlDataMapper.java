package com.bonc.cloudhandlesql.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonc.cloudhandlesql.pojo.SqlData;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author: zlp
 * Date: 2021-01-04 16:40
 * Description:
 */

public interface SqlDataMapper extends BaseMapper<SqlData> {

    @Select("select * from sql_data")
    List<SqlData> getAllSqlDatas();
}
