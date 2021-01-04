package com.bonc.cloudhandlesql.common.response;

import com.alibaba.fastjson.JSONObject;
import com.bonc.cloudhandlesql.common.msg.CodeMsg;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * API响应解析器
 * @author 作者: zlp
 * @date 创建时间:2021年1月4号 16:55
 * @version V-1.0.0
 */
public class WebApiResponse<T> {
    
    /**
     * 成功编码
     */
	public static final String SUCCESS_CODE = "200" ;
	
	/**
	 * 错误编码
	 */
	public static final String ERROE_CODE = "-1" ;
	
	/**
	 * 成功消息
	 */
	public static final String SUCCESS_MSG = "Request success!!" ;
	
	
    /**
     * 消息
     */
    private String msg ;
    
    /**
     * 返回码
     */
    private String code ;
    
    /**
     * 返回数据集
     */
    private T data ;
    
    /**
     * 请求成功，返回结果
     * @param data 结果集
     * @param msg 自定义消息
     * @param code 消息编码
     * @return WebApiResponse<T> 
     */
    public static <T>  WebApiResponse<T> success(T data,String msg,String code){
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setData(data);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
    
    /**
     * 请求成功，返回结果(取默认编码：code = 0 )
     * @param data 结果集
     * @param msg 自定义消息
     * @return WebApiResponse<T> 
     */
    public static <T>  WebApiResponse<T> success(T data,String msg){
        
        return WebApiResponse.<T>success(data,msg,SUCCESS_CODE);
    }
    
    /**
     * 请求成功，返回结果(取默认编码：code = 0 ,默认消息：Request success!!)
     * @param data 结果集
     * @param msg 自定义消息
     * @return WebApiResponse<T> 
     */
    public static <T>  WebApiResponse<T> success(T data){
        return WebApiResponse.<T>success(data,SUCCESS_MSG,SUCCESS_CODE);
    }
    
    /**
     * 请求错误，返回消息提示（根据code取默认消息提示信息）
     * @param code  消息编码
     * @return WebApiResponse<T> 
     */
    public static <T> WebApiResponse<T> error(String code){
        
        return WebApiResponse.<T>error(CodeMsg.getCodeMsg(code));
    }
    
    /**
     * 请求错误，返回消息提示
     * @param data    数据实体
     * @param msg 自定义错误消息
     * @param code 消息编码
     * @return WebApiResponse<T> 
     */
    public static <T>  WebApiResponse<T> error(T data,String msg,String code){
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setData(data);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
    
    
    /**
     * 请求错误，返回消息提示
     * @param msg 自定义错误消息
     * @param code 消息编码
     * @return WebApiResponse<T> 
     */
    public static <T> WebApiResponse<T> error(String msg, String code){
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
    
    
    /**
     * 请求错误，返回消息提示
     * @param data    数据实体
     * @param CodeMsg 自定义消息实例
     * @return WebApiResponse<T> 
     */
    public static <T> WebApiResponse<T> error(T data,CodeMsg codeMsg){
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setData(data);
        response.setCode(codeMsg.getCode());
        response.setMsg(codeMsg.getMsg());
        return response;
    }
    
    
    /**
     * 请求错误，返回消息提示
     * @param CodeMsg 自定义消息实例
     * @return WebApiResponse<T> 
     */
    public static <T> WebApiResponse<T> error(CodeMsg codeMsg){
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setCode(codeMsg.getCode());
        response.setMsg(codeMsg.getMsg());
        return response;
    }


	//-------------------------------------------------------------------------
    /**
     * 请求成功，返回结果
     * @param data 结果集
     * @param msg 自定义消息
     * @param code 消息编码
     * @return WebApiResponse<T> 
     */
    public static Map successMap(Map data,String msg,String code){
        Map map = new HashMap();
        map.put("code",code);
        map.put("msg",msg);
        map.putAll(data);
        return map;
    }
    
    /**
     * 请求成功，返回结果(取默认编码：code = 0 )
     * @param data 结果集
     * @param msg 自定义消息
     * @return WebApiResponse<T> 
     */
    public static Map successMap(Map data,String msg){
          return successMap(data,msg,SUCCESS_CODE);
    }
    
    /**
     * 请求成功，返回结果(取默认编码：code = 0 ,默认消息：Request success!!)
     * @param data 结果集
     * @param msg 自定义消息
     * @return WebApiResponse<T> 
     */
    public static Map successMap(Map data){
        return successMap(data,SUCCESS_MSG,SUCCESS_CODE);
    }
    
    
    /**
     * 请求错误，返回消息提示(取默认编码：code = 1 )
     * @param msg 自定义错误消息
     * @return Map
     */
    public static Map errorMap(String msg){
        return errorMap(msg,ERROE_CODE);
    }
    
    /**
     * 请求错误，返回消息提示
     * @param msg 自定义错误消息
     * @param code 消息编码
     * @return Map
     */
    public static Map errorMap(String msg, String code){
        Map response = new HashMap();
        response.put("code",code);
        response.put("msg",msg);
        return response;
    }
    
    /**
     * 请求错误，返回消息提示
     * @param msg 自定义错误消息
     * @param code 消息编码
     * @throws IOException 
     */
    public static void responseWriter(HttpServletResponse response , String msg, String code) throws IOException{
    	Writer writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map resultMap = new HashMap();
		resultMap.put("code", code);
		resultMap.put("msg", msg);
		writer.write(JSONObject.toJSONString(resultMap));
		writer.close();
    }
    
    /**
     * 请求错误，返回消息提示
     * @param code 消息编码
     * @throws IOException 
     */
    public static void responseWriter(HttpServletResponse response , String code) throws IOException{
    	Writer writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map resultMap = new HashMap();
		resultMap.put("code", code);
		resultMap.put("msg", CodeMsg.getCodeMsg(code));
		writer.write(JSONObject.toJSONString(resultMap));
		writer.close();
    }
    
    
    
    public String getmsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	} 
    
    
}