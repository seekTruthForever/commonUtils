package com.whv.common.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * Map��װ������
 * @author whv
 *
 */
public class MapUtil {
	/**
	 * ��"xx.xxx.xxxx"��װMap����
	 * @param dataMap ����Map
	 * @param mapKey map����"xx.xxx.xxxx"
	 * @param dataObj mapֵ
	 * @param index map����index����
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
