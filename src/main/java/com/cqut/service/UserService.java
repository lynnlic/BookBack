package com.cqut.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.dao.base.EntityDao;
import com.cqut.dao.base.SearchDao;
import com.cqut.entity.user.User;
import com.cqut.util.EntityIDFactory;

@Service("userService")
public class UserService implements IUserService{
	@Resource(name = "entityDao")
    private EntityDao entityDao;
	@Resource(name = "searchDao")
	private SearchDao searchDao;
	
	@Override
	public Map<String, Object> getUser(int row, int page,
			String user_name) {
		String condition = " 1=1 ";
		if (user_name != null && !user_name.isEmpty())
			condition += " AND user.user_name like '%"
					+ user_name + "%'";
		String joinEntity = " left join department on department.id = `user`.department_id ";
		List<Map<String, Object>> resultList = searchDao
				.searchWithpagingInMysql(
						new String[] {  " `user`.id, "
								+ " `user`.user_name, "
								+ " `user`.telephone, "
								+ " `user`.department_id, "
								+ " department.`name`, "
								+ " department.phone_number "},
						" user ", joinEntity, null, null, condition,
						null, "user.id", "ASC", row, page);
		int count = searchDao.getForeignCount(" user.id ", " user ", joinEntity, null, null, condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", resultList);
		map.put("total", count);
		return map;
	}
	
	@Override
	public Map<String, Object> addUser(Map<String, Object> user) {
		User users = new User();
		String id = EntityIDFactory.createId();
		users.setId(id);
		users.setProperties(user);
		
		Map<String, Object> result = new HashMap<String, Object>();;
		String resultString = entityDao.save(users) == 1 ? "true": "false";
		result.put("result", resultString);
		
		return result;
	}

	@Override
	public Map<String, Object> updateUser(String user_id, Map<String, Object> user) {
		User users = entityDao.getByID(user_id, User.class);
		users.setProperties(user);
		System.out.println(users.toString());
		String resultString = entityDao.updatePropByID(users,
				user_id) == 1 ? "true" : "false";
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result", resultString);
		return result;
	}

	@Override
	public int deleteUser(String user_id) {
		String[] ids = user_id.split(",");
		int BackValue = 0;
		for(String id:ids){
			BackValue += entityDao.deleteByID(id, User.class);
		}
		if(BackValue != ids.length){
			BackValue = BackValue - ids.length;
		}
		//返回删除成功的个数
	    return BackValue;
	}

	@Override
	public List<Map<String, Object>> getDepartmentName() {
		List<Map<String, Object>> resultList = searchDao.
				searchForeign(new String[]{ " department.id, department.name "},
                " department ", null, null, null, null);
 		return resultList;
	}
}
