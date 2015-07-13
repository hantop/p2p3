package com.fenlibao.p2p.zhaopin.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.zhaopin.domain.Relationship;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/18.
 */
public interface RelationshipService extends ServiceTemplate<Long,Relationship> {
     int tryStatus(Long id);

    int positiveStatus(Long id);

    Relationship selectByFromUserAndToUser(String fromUserName, String toUserName);
}
