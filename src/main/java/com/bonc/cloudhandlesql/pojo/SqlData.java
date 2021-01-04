package com.bonc.cloudhandlesql.pojo;

import lombok.Data;

/**
 * Author: zlp
 * Date: 2021-01-04 16:30
 * Description:
 */
@Data
public class SqlData {

    private Integer id;
    /**
     * sql语句
     */
    private String sql;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * 状态码
     */
    private String Code;

}
