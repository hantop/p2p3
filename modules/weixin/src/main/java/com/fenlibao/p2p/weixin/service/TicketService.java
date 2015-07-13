package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.weixin.domain.Ticket;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface TicketService extends ServiceTemplate<String,Ticket> {


    /**
     * 根据ticket类型获取最后生成的那个ticket
     * @param ticketType
     * @return
     */
    Ticket selectLastTicket(Constants.TicketType ticketType);
}
