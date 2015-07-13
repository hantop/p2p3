package com.fenlibao.p2p.zhaopin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.zhaopin.domain.Rule;
import com.fenlibao.p2p.zhaopin.persistence.RuleMapper;
import com.fenlibao.p2p.zhaopin.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/18.
 */
@Service("ruleService")
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public Page<Rule> findPage(Page<Rule> page) {
        List<Rule> rules = this.ruleMapper.findPage(page);
        page.addContent(rules);
        return page;
    }

    @Override
    public int insert(Rule record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Rule record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Rule record) {
        return this.ruleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(Rule record) {
        return this.ruleMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(Rule record) {
        return 0;
    }

    @Override
    public Rule selectByPrimaryKey(Long id) {
        return this.ruleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Rule> findAll() {
        return this.ruleMapper.findAll();
    }
}
