package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.weixin.domain.Token;

/**
 * Created by Administrator on 2015/6/3.
 */
public interface TokenService extends ServiceTemplate<String,Token> {

    Token selectLast(Constants.TokeyType tokenType);
}
