package com.whv.common.utils.json;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
/**
 * jsonת��������
 * @author whv
 *
 */
public class JsonUtil {
	//Ĭ�ϱ���
	private final static String DEF_CHARSET="GBK";
    /**
     * JSON data -> json
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static StringWriter object2Json(Object data) throws Exception {
        StringWriter write = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(write, data);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
        return write;
    }

    public static void writeJson(HttpServletResponse rep, Object data) {
        writeJson(rep, data, DEF_CHARSET);
    }
    public static void writeJson(HttpServletResponse rep, Object data,String charset) {
        rep.setContentType("application/json;charset="+charset);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(rep.getWriter(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ������ת����json�ַ�����
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String object2JsonStr(Object data) {
    	String str = null;
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			str = mapper.writeValueAsString(data);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return str;
    }
    
    /**
     * ��json�����ת��Ϊ����
     * 
     * @param jsonData json����
     * @param clazz �����е�object����
     * @return
     */
    public static <T> T json2Pojo(String jsonData, Class<T> beanType) {
    	ObjectMapper mapper = new ObjectMapper();
    	T t = null;
        try {
            t = mapper.readValue(jsonData, beanType);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return t;
    }
    
    /**
     * ��json����ת����pojo����list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> json2List(String jsonData, Class<T> beanType) {
    	ObjectMapper mapper = new ObjectMapper();
    	List<T> list = null;
    	JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		list = mapper.readValue(jsonData, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return list;
    }
    public static void appWriteJson(HttpServletResponse rep, Object data,Object collections, int status, String message) {
    	appWriteJson(rep, data, collections, status, message, DEF_CHARSET);
    }
    public static void appWriteJson(HttpServletResponse rep, Object data,Object collections, int status, String message,String charset) {
        rep.setContentType("application/json;charset="+charset);
        ObjectMapper mapper = new ObjectMapper();
        try {
        	AppData appData = new AppData();
        	Date date = new Date();
    		String a =String.valueOf( date.getTime());
        	appData.setCurrentTime(a);
        	appData.setMessage(message);
        	appData.setStatus(status);
        	appData.setData(data);
        	appData.setCollections(collections);
            mapper.writeValue(rep.getWriter(), appData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
