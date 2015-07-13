package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.weixin.domain.Business;

/**
 * Created by Administrator on 2015/7/13.
 */
public interface BusinessService {

    int deleteByPrimaryKey(String id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    int saveOrUpdateByPoiId(Business record);
}
