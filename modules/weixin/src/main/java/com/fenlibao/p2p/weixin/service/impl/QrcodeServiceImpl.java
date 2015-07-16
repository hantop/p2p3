package com.fenlibao.p2p.weixin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.weixin.persistence.QrcodeMapper;
import com.fenlibao.p2p.weixin.service.QrcodeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/6/18.
 */
@Service("qrcodeService")
public class QrcodeServiceImpl implements QrcodeService {

    @Inject
    private QrcodeMapper qrcodeMapper;

    @Override
    public Page<Qrcode> findPage(Page<Qrcode> page) {
        return null;
    }

    @Override
    public int insert(Qrcode record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Qrcode record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Qrcode record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(Qrcode record) {
        return this.qrcodeMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(Qrcode record) {
        return 0;
    }

    @Override
    public Qrcode selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Qrcode> findAll() {
        return null;
    }

    @Override
    public Qrcode selectLimitSceneByScene(Serializable sceneValue,String scene) {
        return qrcodeMapper.selectLimitSceneByScene(sceneValue.toString(), sceneValue.getClass().getName(), scene);
    }
}
