package com.fenlibao.p2p.zhaopin.persistence;

import com.fenlibao.p2p.common.persistence.PersistenceTemplate;
import com.fenlibao.p2p.zhaopin.domain.Relationship;

public interface RelationshipMapper extends PersistenceTemplate<Long,Relationship> {

    Relationship selectByFromUserAndToUser(String fromUserName, String toUserName);
}