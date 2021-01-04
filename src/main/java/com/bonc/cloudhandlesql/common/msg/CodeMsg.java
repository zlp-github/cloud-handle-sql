package com.bonc.cloudhandlesql.common.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 消息处理类
 * @author 作者: zlp
 * @date 创建时间：2021年1月4号 16:55
 * @version V-1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeMsg {
	
	
    private static Map<Object ,Object> map  = new HashMap<Object, Object>();
    
    
    /**
     * 加载配置
     */
    static {
    	Properties props = new Properties();
		try {
	         InputStream in = CodeMsg.class.getClassLoader()
	        		 .getResourceAsStream("i18n/messages_api.properties");
	         props.load(in);
	         Enumeration en = props.propertyNames();
		         while (en.hasMoreElements()) {	 
		              String key = (String) en.nextElement();
		              String Property = props.getProperty (key);
		              map.put(key,Property);
		         }
	    } catch (Exception e) {
	         e.printStackTrace();
	    }
    }
	
	//自定义状态码
    private String code;
    
    //消息信息
    private String msg;
    
    
	/**
	 * 成功消息
	 */
    public static CodeMsg SUCCESS_MSG = new CodeMsg("200", (String)map.get("200"));
    
    /**
     * 服务器异常
     */
    public static CodeMsg SERVER_ERROR = new CodeMsg("500", (String)map.get("500"));
    /**
     * Session is not found!
     */
    public static CodeMsg SESSION_ERROR = new CodeMsg("10014", (String)map.get("10014"));
    
    
    /**
     * 
     * @param code
     * @return
     */
    public static CodeMsg getCodeMsg(String code) {
        return new CodeMsg(code, (String)map.get(code));
    }
    
    public CodeMsg fillArgs(Object... args) {
    	String code = this.code;
        String msg = String.format(this.msg, args);
        return new CodeMsg(code, msg);
    }
}
