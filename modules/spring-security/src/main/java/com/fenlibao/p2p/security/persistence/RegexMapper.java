package com.fenlibao.p2p.security.persistence;

import com.fenlibao.p2p.security.domain.Regex;

import java.util.List;

public interface RegexMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Regex record);

    int insertSelective(Regex record);

    Regex selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Regex record);

    int updateByPrimaryKey(Regex record);

    List<Regex> findAll();
}