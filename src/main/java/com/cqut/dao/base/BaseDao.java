package com.cqut.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author lxr
 * @description 不好拼接sql的，用于存放整个sql
 */
public interface BaseDao {
	
	public List<Map<String, Object>> getModulesByCondition(
			@Param("loginName") String loginName);
}
