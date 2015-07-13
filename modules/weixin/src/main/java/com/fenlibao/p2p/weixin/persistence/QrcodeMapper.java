package com.fenlibao.p2p.weixin.persistence;

import com.fenlibao.p2p.weixin.domain.Qrcode;

public interface QrcodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Qrcode record);

    int insertSelective(Qrcode record);

    Qrcode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Qrcode record);


    int updateByPrimaryKey(Qrcode record);

    Qrcode selectLimitSceneBySceneStr(String sceneStr, String scene);

    Qrcode selectLimitSceneBySceneId(Integer sceneId, String scene);
}