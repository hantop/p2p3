package com.fenlibao.p2p.weixin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Ticket;
import com.fenlibao.p2p.weixin.persistence.TicketMapper;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Page<Ticket> findPage(Page<Ticket> page) {
        return null;
    }

    @Override
    public int insert(Ticket record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Ticket record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Ticket record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(Ticket record) {
        return ticketMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(Ticket record) {
        return 0;
    }

    @Override
    public Ticket selectByPrimaryKey(String id) {
        return ticketMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Ticket selectLastTicket(Constants.TicketType ticketType) {
        return this.ticketMapper.selectLastTicket(ticketType);
    }
}
