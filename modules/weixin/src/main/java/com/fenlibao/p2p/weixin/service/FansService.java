package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.weixin.domain.Fans;

/**
 * Created by Administrator on 2015/6/4.
 */
public interface FansService extends ServiceTemplate<Long,Fans> {

    Fans selectByOpenId(String openid);

    int updateByOpenIdSelective(Fans record);

    int saveOrUpdate(Fans record);
}
