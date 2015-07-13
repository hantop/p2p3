package com.fenlibao.p2p.weixin.annotation;

import com.fenlibao.p2p.weixin.variable.WeiXinThing;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/7/3.
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Thing {
    WeiXinThing value();
}
