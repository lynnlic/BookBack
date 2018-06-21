package com.cqut.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JsonTool {
	
	public static Map<String, Object> fromJSONtoMap(String jsonString) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (jsonString==null) {
			map.put("rowData", -1);
		}else {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			JSONArray array = new JSONArray();
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			Iterator keyIter = jsonObject.keys();
			String key;
			Object value;
			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jsonObject.get(key);
				if ((value instanceof JSONArray)) {
					array =  (JSONArray) JSONSerializer.toJSON(value);
					mapList=addListMap(array);
					map.put(key, mapList);
				}else if ((value instanceof JSONObject)) {
					array =  (JSONArray) JSONSerializer.toJSON(value);
					mapList=addListMap(array);
					map.put(key, mapList);
				}else {
					map.put(key, value);
				}
			}
		}
		return map;
	}
	
	public static List<Map<String, Object>> addListMap(JSONArray array){
		  Iterator<Object> it = array.iterator();
		  Map<String, Object> map = new HashMap<String, Object>();
		  List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		  for (int i = 0; i < array.size(); i++) {
			  JSONObject ob = (JSONObject) it.next();
              map=addMap(ob);
              mapList.add(map);
		}
		return mapList;
	}
	
	public static Map<String, Object> addMap(JSONObject jsonObject){
		 Map<String, Object> map = new HashMap<String, Object>();
		 List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		 Iterator keyIter = jsonObject.keys();
		 JSONArray array = new JSONArray();
		 String key;
		 Object value;
		 while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jsonObject.get(key);
				if ((value instanceof JSONArray)) {
					array =  (JSONArray) JSONSerializer.toJSON(value);
					mapList=addListMap(array);
					map.put(key, mapList);
				}else {
					map.put(key, value);
				}
		}
	return map;
	}

}
