package com.fenlibao.p2p.weixin.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

public class Music implements Serializable {

	/* TODO */
	private static final long serialVersionUID = 1L;

	// 音乐名称
	@XStreamAlias("Title")
	private String title;
	// 音乐描述
	@XStreamAlias("Description")
	private String description;
	// 音乐链接
	@XStreamAlias("MusicUrl")
	private String musicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	@XStreamAlias("HQMusicUrl")
	private String hQMusicUrl;
	/* 缩略图的媒体id，通过上传多媒体文件，得到的id */
	@XStreamAlias("thumbMediaId")
	private String ThumbMediaId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
