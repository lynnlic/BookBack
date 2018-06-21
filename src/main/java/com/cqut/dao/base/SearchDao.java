package com.cqut.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SearchDao {
	
	public List<Map<String,Object>> searchForeign(@Param("properties")String[] properties, @Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity, @Param("foreingKey")List<String> foreingKeySql,
			@Param("condition")String condition);
	
	public int getForeignCount(@Param("primaryKey")String primaryKey,@Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity,@Param("foreingKey")List<String> foreingKeySql,
			@Param("condition") String condition);
	
	public List<Map<String,Object>> searchWithpagingInMssql(@Param("properties")String[] properties, @Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity, @Param("foreingKey")List<String> foreingKeySql,
			@Param("condition")String condition,@Param("groupField")String groupField,
			@Param("orderField")String orderField, @Param("sortMode")String sortMode,
			@Param("startIndex")int startIndex, @Param("pageNum")int pageNum);
	
	public List<Map<String,Object>> searchOrderByTime(@Param("title1")String title1,@Param("title2")String title2,
			@Param("title3")String title3,@Param("title4")String title4,
			@Param("properties")String[] properties, @Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity, @Param("foreingKey")List<String> foreingKeySql,
			@Param("condition")String condition,@Param("groupField")String groupField);
	
	public List<Map<String,Object>> searchWithpagingInMysql(@Param("properties")String[] properties, @Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity, @Param("foreingKey")List<String> foreingKeySql,
			@Param("condition")String condition,@Param("groupField")String groupField,
			@Param("orderField")String orderField, @Param("sortMode")String sortMode, 
			@Param("startIndex")int startIndex, @Param("pageNum")int pageNum);
	
	public List<Map<String,Object>> searchWithpagingInOracle(@Param("properties")String[] properties, @Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity, @Param("foreingKey")List<String> foreingKeySql,
			@Param("condition")String condition,@Param("groupField")String groupField,
			@Param("orderField")String orderField, @Param("sortMode")String sortMode, 
			@Param("startIndex")int startIndex, @Param("endIndex")int endIndex);
	
	public List<Map<String,Object>> searchWithpagingInMysqlNoOrder(@Param("properties")String[] properties, @Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity, @Param("foreingKey")List<String> foreingKeySql,
			@Param("condition")String condition, @Param("sortMode")String sortMode, 
			@Param("startIndex")int startIndex, @Param("pageNum")int pageNum);
	
	public List<Map<String,Object>> searchProperty(@Param("properties")String[] properties, @Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,
			@Param("foreignEntity")String[] foreignEntity, @Param("foreingKey")List<String> foreingKeySql,
			@Param("condition")String condition,@Param("groupField")String groupField,
			@Param("orderField")String orderField, @Param("sortMode")String sortMode);
	
	public List<Map<String,Object>> searchByGroup(@Param("properties")String[] properties, 
			@Param("expressResult")String expressResult,@Param("expression")String expression,
			@Param("baseproperties")String[] baseproperties,@Param("baseEntity")String baseEntity,
			@Param("joinEntity")String joinEntity,@Param("condition")String condition);
}
