package com.cqut.util;

import com.cqut.entity.base.Entity;

public class EntityFilter implements ClassFilter{

	public boolean accept(Class clazz) {
		return clazz.getSuperclass().equals(Entity.class);
	}

}
