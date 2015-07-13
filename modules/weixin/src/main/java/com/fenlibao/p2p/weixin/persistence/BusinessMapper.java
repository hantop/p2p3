package com.fenlibao.p2p.weixin.persistence;

import com.fenlibao.p2p.weixin.domain.Business;

public interface BusinessMapper {
    int deleteByPrimaryKey(String id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    Business selectByPoiId(String selectByPoiId);

    int updateByPrimaryPoiSelective(Business record);
}