package com.fenlibao.p2p.weixin.defines;


/**
 * 事件审核结果
 * Created by Administrator on 2015/7/13.
 */
public enum PoiCheck {
    SUCCESS {
        @Override
        public String toString() {
            return "succ";//成功succ
        }
    },
    FAIL {
        @Override
        public String toString() {
            return "fail";//失败
        }
    }
}
