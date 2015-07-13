package com.fenlibao.p2p.constant.persistence;

import com.fenlibao.p2p.common.persistence.PersistenceTemplate;
import com.fenlibao.p2p.constant.domain.Constant;
import com.fenlibao.p2p.constant.variable.ConstantVariable;

import java.util.List;

public interface ConstantMapper extends PersistenceTemplate<Long,Constant>{

    Constant selectByKey(String key);

    List<Constant> selectByType(Enum type);
}