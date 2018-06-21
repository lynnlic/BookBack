package com.cqut.entity.user;

import com.cqut.entity.base.Entity;

public class Book extends Entity{
	private String id;
	private String bookName;
	private String bookAuthor;
	private String imageUrl;
	private String introduce;
	private String highlight;
	private String uploader;
	private String socre;
	private String uploadTime;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getSocre() {
		return socre;
	}

	public void setSocre(String socre) {
		this.socre = socre;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", imageUrl=" + imageUrl
				+ ", introduce=" + introduce + ", highlight=" + highlight + ", uploader=" + uploader + ", socre="
				+ socre + ", uploadTime=" + uploadTime + "]";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Book";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "id";
	}

}
