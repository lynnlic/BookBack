package com.cqut.entity.user;

import com.cqut.entity.base.Entity;

public class BookTag extends Entity{
	private String id;
	private String bookId;
	private String tagId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	@Override
	public String toString() {
		return "BookTag [id=" + id + ", bookId=" + bookId + ", tagId=" + tagId + "]";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "BookTag";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "id";
	}

}
