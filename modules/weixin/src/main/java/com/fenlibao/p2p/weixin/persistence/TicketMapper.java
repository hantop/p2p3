package com.fenlibao.p2p.weixin.persistence;

import com.fenlibao.p2p.weixin.domain.Ticket;
import com.fenlibao.p2p.weixin.service.Constants;

public interface TicketMapper {
    int deleteByPrimaryKey(String id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    Ticket selectLastTicket(Constants.TicketType ticketType);
}