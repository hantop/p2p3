package com.fenlibao.p2p.weixin.persistence;

import com.fenlibao.p2p.weixin.domain.Media;

import java.util.List;

public interface MediaMapper {
    int insert(Media record);

    int insertSelective(Media record);

    int insertBatch(List<? extends Media> itemList);
}