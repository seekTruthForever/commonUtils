package com.whv.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *自定义map操作类
 */
public class Mymap{
    private final static String DEFAULT_SPLIT_FLAG = "\\.";
    private Map mymap;
    private String splitFlag;
    public Map getMymap() {
        return mymap;
    }

    public Mymap(){
        this.splitFlag = DEFAULT_SPLIT_FLAG;
        this.mymap = new HashMap();
    }
    public Mymap(Map dataMap, String mapKey,Object data){
        this.splitFlag = DEFAULT_SPLIT_FLAG;
        this.mymap = dataMap;
        equipMapData(this.mymap,mapKey,data,0);
    }
    public Mymap(String keySplit,Map dataMap, String mapKey,Object data){
        if(keySplit!=null&&keySplit.length()>0){
            this.splitFlag = keySplit;
        }
        this.mymap = dataMap;
        equipMapData(this.mymap,mapKey,data,0);
    }

    /**
     * 初始化
     */
    public void init(){
        this.mymap = new HashMap();
    }

    /**
     * 初始化
     * @param keySplit 分隔符
     */
    public void init(String keySplit){
        if(keySplit!=null&&keySplit.length()>0){
            this.splitFlag = keySplit;
        }
        this.init();
    }
    public void init(Map dataMap){
        this.mymap = dataMap;
    }

    /**
     * 装载map
     * @param mapKey map键："xx.xxx.xxxx"
     * @param dataObj map值
     */
    public void equipData(String mapKey, Object dataObj){
        equipMapData(this.mymap, mapKey, dataObj, 0);
    }

    /**
     * 获取数据
     * @param mapKey map键："xx.xxx.xxxx"
     * @return map值
     */
    public Object getData(String mapKey){
        if(null == mapKey || "".equals(mapKey)){
            return this.mymap;
        }
        String[] keys = mapKey.split(this.splitFlag);
        Map dataMap = new HashMap();
        dataMap.putAll(this.mymap);
        for(int index = 0;index < keys.length-1;index ++){
            String keyStr = keys[index];
            if(!dataMap.containsKey(keyStr)){
                return null;
            }
            dataMap = (Map) dataMap.get(keyStr);
        }
        return dataMap.get(keys[keys.length-1]);
    }
    /**
     * 从"xx.xxx.xxxx"封装Map数据
     * @param dataMap 数据Map
     * @param mapKey map键："xx.xxx.xxxx"
     * @param dataObj map值
     * @param index map键的index索引
     * @return
     */
    private  Map equipMapData(Map dataMap, String mapKey, Object dataObj, int index)  {
        if(dataMap==null || (mapKey==null || "".equals(mapKey.trim()))){
            return new HashMap();
        }
        String[] keys = mapKey.split(this.splitFlag);
        if(index < keys.length-1){
            String keyStr = keys[index];
            index ++;
            if(dataMap.containsKey(keyStr)){
                return equipMapData((Map) dataMap.get(keyStr),mapKey,dataObj,index);
            }
            dataMap.put(keyStr,new HashMap<String,Object>());
            return equipMapData((Map) dataMap.get(keyStr),mapKey,dataObj,index);
        }
        dataMap.put(keys[index],dataObj);
        return dataMap;
    }
    
    @Override
    public String toString() {
    	return mymap.toString();
    }
}
