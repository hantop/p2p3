package com.fenlibao.p2p.constant.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.constant.domain.Constant;
import com.fenlibao.p2p.constant.variable.ConstantVariable;

import java.util.List;

/**
 * Created by Administrator on 2015/6/30.
 */
public interface ConstantService extends ServiceTemplate<Long,Constant> {

    /**
     * 根据key进行查询
     * @param key
     * @return
     */
    Constant selectByKey(String key);

    /**
     * 根据类型获取改类型的常量信息
     * @param type
     * @return
     */
    List<Constant> selectByType(Enum type);
}
