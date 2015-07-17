package com.fenlibao.p2p.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Ticket;
import com.fenlibao.p2p.weixin.persistence.TicketMapper;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.service.TicketService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
    @Inject
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
        if(log.isInfoEnabled()) {
            log.info("保存微信ticket信息:{}", JSON.toJSONString(record, SerializerFeature.PrettyFormat,SerializerFeature.WriteClassName));
        }
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
