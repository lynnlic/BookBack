package com.cqut.service;

import java.util.List;
import java.util.Map;

public interface IUserService {
	public Map<String, Object> getUser(int page, int row, String user_name);
	public Map<String, Object> addUser(Map<String, Object> user);
	public Map<String, Object> updateUser(String user_id, Map<String, Object> user);
	public int deleteUser(String user_id);
	public List<Map<String, Object>> getDepartmentName();
}
