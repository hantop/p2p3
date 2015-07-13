package com.fenlibao.p2p.weixin.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/5/25.
 */
public class Image implements Serializable {

    private Long id ;

    private Long message;

    @XStreamAlias("MediaId")
    private String mediaId;//	是	通过上传多媒体文件，得到的id。

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessage() {
        return message;
    }

    public void setMessage(Long message) {
        this.message = message;
    }
}
