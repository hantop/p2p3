package com.fenlibao.p2p.zhaopin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.zhaopin.domain.RelationshipHistory;
import com.fenlibao.p2p.zhaopin.persistence.RelationshipHistoryMapper;
import com.fenlibao.p2p.zhaopin.service.RelationshipHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/18.
 */
@Service("relationshipHistoryService")
public class RelationshipHistoryServiceImpl implements RelationshipHistoryService {

    @Autowired
    private RelationshipHistoryMapper relationshipHistoryMapper;

    @Override
    public Page<RelationshipHistory> findPage(Page<RelationshipHistory> page) {
        List<RelationshipHistory> results = relationshipHistoryMapper.findPage(page);
        page.addContent(results);
        return page;
    }

    @Override
    public int insert(RelationshipHistory record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RelationshipHistory record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(RelationshipHistory record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(RelationshipHistory record) {
        return this.relationshipHistoryMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(RelationshipHistory record) {
        return 0;
    }

    @Override
    public RelationshipHistory selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<RelationshipHistory> findAll() {
        return null;
    }
}
