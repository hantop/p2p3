package com.fenlibao.p2p.weixin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Token;
import com.fenlibao.p2p.weixin.persistence.TokenMapper;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/3.
 */
@Service("tokenService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Token selectLast(Constants.TokeyType tokenType) {
        return tokenMapper.selectLast(tokenType);
    }

    @Override
    public Page<Token> findPage(Page<Token> page) {
        return null;
    }

    @Override
    public int insert(Token record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Token record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Token record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(Token record) {
        return this.tokenMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(Token record) {
        return 0;
    }

    @Override
    public Token selectByPrimaryKey(String id) {
        return this.tokenMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Token> findAll() {
        return null;
    }
}
