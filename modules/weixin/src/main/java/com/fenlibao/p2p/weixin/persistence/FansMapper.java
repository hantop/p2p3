package com.fenlibao.p2p.weixin.persistence;

import com.fenlibao.p2p.weixin.domain.Fans;

public interface FansMapper {
    int deleteByPrimaryKey(String id);

    int insert(Fans record);

    int insertSelective(Fans record);

    Fans selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Fans record);

    int updateByPrimaryKey(Fans record);

    Fans selectByOpenId(String openid);

    int updateByOpenIdSelective(Fans record);
}