package com.fenlibao.p2p.weixin.persistence;

import com.fenlibao.p2p.weixin.domain.Msg;

public interface MsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);
}