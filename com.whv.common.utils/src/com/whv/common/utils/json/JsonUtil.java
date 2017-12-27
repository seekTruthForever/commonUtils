package com.whv.common.utils.json;

import java.io.StringWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
    /**
     * JSON data -> json
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static StringWriter objectToJson(Object data) throws Exception {
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
        rep.setContentType("application/json;charset=GBK");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(rep.getWriter(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appWriteJson(HttpServletResponse rep, Object data,Object collections, int status, String message) {
        rep.setContentType("application/json;charset=GBK");
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
//            BspInfoImpl bspInfo = new BspInfoImpl();
//            bspInfo.setUserName("ÕÅÈý");
//            bspInfo.setUserId("123");
//            ContextHolder.setBspInfo(bspInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
