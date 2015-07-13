package com.fenlibao.p2p.weixin.persistence;

import com.fenlibao.p2p.weixin.domain.Token;
import com.fenlibao.p2p.weixin.service.Constants;

public interface TokenMapper {
    int deleteByPrimaryKey(String id);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);

    Token selectLast(Constants.TokeyType tokenType);
}