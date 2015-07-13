package com.fenlibao.p2p.zhaopin.persistence;

import com.fenlibao.p2p.common.persistence.PersistenceTemplate;
import com.fenlibao.p2p.zhaopin.domain.Channel;

public interface ChannelMapper extends PersistenceTemplate<Long,Channel>{

    Channel selectBySceneId(int sceneId);
}