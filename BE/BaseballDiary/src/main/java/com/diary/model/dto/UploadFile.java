package com.diary.model.dto;

public class UploadFile {
	String id;
	String displayName;
	int size;
	int count;
	String uploadDateTime;
	
	public UploadFile() {
		// TODO Auto-generated constructor stub
	}
	
	public UploadFile(String id, String displayName, int size, int count, String uploadDateTime) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.size = size;
		this.count = count;
		this.uploadDateTime = uploadDateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime(String uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	@Override
	public String toString() {
		return "UploadFile [id=" + id + ", displayName=" + displayName + ", size=" + size + ", count=" + count
				+ ", uploadDateTime=" + uploadDateTime + "]";
	}
	
	
	
}
