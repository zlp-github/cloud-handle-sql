package com.bonc.cloudhandlesql.controller;

import com.alibaba.fastjson.JSONObject;
import com.bonc.cloudhandlesql.common.response.WebApiResponse;
import com.bonc.cloudhandlesql.pojo.SqlData;
import com.bonc.cloudhandlesql.service.SqlDataService;
import com.bonc.cloudhandlesql.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: zlp
 * Date: 2021-01-04 16:50
 * Description:
 */
@RestController
public class SqlDataController {

    @Autowired
    private SqlDataService sqlDataService;


    @Value("${send.sql.to.url}")
    private String url;


    @RequestMapping(value = "/getSqlDataListToRequest",method = RequestMethod.GET)
    private WebApiResponse getSqlDataListToRequest(){
        List<SqlData> sqlDataList = sqlDataService.getAllSqlDatasToRequest();
        List<SqlData> dataList = new ArrayList<>();
        for (SqlData sqlData : sqlDataList) {
            Map<String,Object> map = new HashMap<>();
            Map<String,String> objectInfo = new HashMap<>();
            objectInfo.put("script",sqlData.getSql());
            objectInfo.put("fileName",sqlData.getFileName());
            objectInfo.put("userId",sqlData.getUserId());
            objectInfo.put("tenantId",sqlData.getTenantId());
            map.put("objectInfo",objectInfo);
            String result = HttpUtil.doPost(url,map);
            if(result != null){
                JSONObject resultJson = JSONObject.parseObject(result);
                String code = resultJson.getString("returnStatus");
                String status = resultJson.getString("returnStatusStr");
                sqlData.setCode(code);
                dataList.add(sqlData);
            }
        }
        sqlDataService.updateBatchById(dataList);
        return WebApiResponse.success(dataList);
    }
}
