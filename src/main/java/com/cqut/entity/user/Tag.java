package com.cqut.entity.user;

import com.cqut.entity.base.Entity;

public class Tag extends Entity{
	private String id;
	private String tagName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tagName=" + tagName + "]";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Tag";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "id";
	}

}
