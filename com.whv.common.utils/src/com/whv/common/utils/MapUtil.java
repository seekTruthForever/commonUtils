package com.whv.common.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * Map封装工具类
 * @author whv
 *
 */
public class MapUtil {
	/**
	 * 从"xx.xxx.xxxx"封装Map数据
	 * @param dataMap 数据Map
	 * @param mapKey map键："xx.xxx.xxxx"
	 * @param dataObj map值
	 * @param index map键的index索引
	 * @return
	 */
	public static Map equipMapData(Map dataMap,String mapKey,Object dataObj,int index)  {
		if(dataMap==null || (mapKey==null || "".equals(mapKey.trim()))){
			return new HashMap();
		}
		String[] keys = mapKey.split("\\.");
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

	
}
