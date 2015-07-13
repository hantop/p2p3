package com.fenlibao.p2p.weixin.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2015/5/25.
 */
public class Video {

    @XStreamAlias("MediaId")
    private String mediaId;//消息媒体id，可以调用多媒体文件下载接口拉取数据。

    @XStreamAlias("Title")
    private String title;//	否	视频消息的标题

    @XStreamAlias("Description")
    private String description;//	否	视频消息的描述

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

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
}
