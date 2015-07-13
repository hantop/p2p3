package com.fenlibao.p2p.security.persistence;

import com.fenlibao.p2p.security.domain.Resc;

public interface RescMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resc record);

    int insertSelective(Resc record);

    Resc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resc record);

    int updateByPrimaryKey(Resc record);
}