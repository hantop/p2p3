package com.fenlibao.p2p.sms.persistence;

import com.fenlibao.p2p.sms.domain.RegisterInfo;

public interface RegisterInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RegisterInfo record);

    int insertSelective(RegisterInfo record);

    RegisterInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegisterInfo record);

    int updateByPrimaryKey(RegisterInfo record);
}