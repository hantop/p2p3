package com.fenlibao.p2p.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Token;
import com.fenlibao.p2p.weixin.persistence.TokenMapper;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.service.TokenService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2015/6/3.
 */
@Service("tokenService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TokenServiceImpl implements TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Inject
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
        if(log.isInfoEnabled()) {
            log.info("保存微信token:{}", JSON.toJSONString(record, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
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
