package com.fenlibao.p2p.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.weixin.domain.Business;
import com.fenlibao.p2p.weixin.persistence.BusinessMapper;
import com.fenlibao.p2p.weixin.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/7/13.
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

    private static final Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);
//    @Inject
    private BusinessMapper businessMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Business record) {
        return 0;
    }

    @Override
    public int insertSelective(Business record) {
        return 0;
    }

    @Override
    public Business selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Business record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Business record) {
        return 0;
    }

    @Override
    public int saveOrUpdateByPoiId(Business record) {
        if(log.isInfoEnabled()) {
            log.info("保存或修改门店信息:{}", JSON.toJSONString(record, SerializerFeature.PrettyFormat,SerializerFeature.WriteClassName));
        }
        Business business = this.businessMapper.selectByPoiId(record.getPoiId());
        if(business != null) {
           this.businessMapper.updateByPrimaryPoiSelective(record);
        } else {
            this.businessMapper.insertSelective(record);
        }
        return 0;
    }
}
