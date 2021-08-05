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

    /**
     * 无参构造
     */
    public Mymap(){
        this.init();
    }

    /**
     * 有参构造
     * @param dataMap 默认数据Map
     * @param mapKey 新增数据的key
     * @param data 新增的数据
     */
    public Mymap(Map dataMap, String mapKey,Object data){
        this(DEFAULT_SPLIT_FLAG,dataMap,mapKey,data);
    }

    /**
     * 有参构造
     * @param keySplit map的key层级之间的分隔符
     * @param dataMap 默认数据Map
     * @param mapKey 新增数据的key
     * @param data 新增的数据
     */
    public Mymap(String keySplit,Map dataMap, String mapKey,Object data){
        this.init(keySplit,dataMap);
        this.equipData(mapKey,data);
    }

    /**
     * 初始化
     */
    public void init(){
        this.init(DEFAULT_SPLIT_FLAG,new HashMap());
    }

    /**
     * 初始化
     * @param keySplit 分隔符
     */
    public void init(String keySplit){
        this.init(keySplit,new HashMap());
    }
    /**
     * 初始化
     * @param dataMap 默认map
     */
    public void init(Map dataMap){
        this.init(DEFAULT_SPLIT_FLAG,dataMap);
    }
    /**
     * 初始化
     * @param keySplit 分隔符
     * @param dataMap 默认map
     */
    public void init(String keySplit,Map dataMap){
        if(keySplit==null || keySplit.length()==0){
            keySplit = DEFAULT_SPLIT_FLAG;
        }
        this.splitFlag = keySplit;
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
