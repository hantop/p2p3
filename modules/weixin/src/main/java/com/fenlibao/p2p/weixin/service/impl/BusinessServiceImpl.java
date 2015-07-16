package com.fenlibao.p2p.weixin.service.impl;

import com.fenlibao.p2p.weixin.domain.Business;
import com.fenlibao.p2p.weixin.persistence.BusinessMapper;
import com.fenlibao.p2p.weixin.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/7/13.
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {


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
        Business business = this.businessMapper.selectByPoiId(record.getPoiId());
        if(business != null) {
           this.businessMapper.updateByPrimaryPoiSelective(record);
        } else {
            this.businessMapper.insertSelective(record);
        }
        return 0;
    }
}
