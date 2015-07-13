package com.fenlibao.p2p.weixin.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;


public class Media implements Serializable {

	/* TODO */
	private static final long serialVersionUID = 1L;
	/* 通过上传多媒体文件，得到的id。 */
	@XStreamAlias("MediaId")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
