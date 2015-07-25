package com.hk.entity;

import java.io.Serializable;

public class Mp3Info implements Serializable {

	/**
	 * –Ú¡–ªØ
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String mp3Name;
	private String lrcName;
	private String mp3Size;
	private String lrcSize;
	public Mp3Info(String id, String mp3Name, String lrcName, String mp3Size,
			String lrcSize) {
		super();
		this.id = id;
		this.mp3Name = mp3Name;
		this.lrcName = lrcName;
		this.mp3Size = mp3Size;
		this.lrcSize = lrcSize;
	}

	public String getMp3Size() {
		return mp3Size;
	}
	public void setMp3Size(String mp3Size) {
		this.mp3Size = mp3Size;
	}
	public String getLrcSize() {
		return lrcSize;
	}
	public void setLrcSize(String lrcSize) {
		this.lrcSize = lrcSize;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMp3Name() {
		return mp3Name;
	}
	public void setMp3Name(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public String getLrcName() {
		return lrcName;
	}
	public void setLrcName(String lrcName) {
		this.lrcName = lrcName;
	}
	public Mp3Info() {
		super();
	}
	@Override
	public String toString() {
		return "Mp3Info [id=" + id + ", mp3Name=" + mp3Name + ", lrcName="
				+ lrcName + ", mp3Size=" + mp3Size + ", lrcSize=" + lrcSize
				+ "]";
	}
	
}
