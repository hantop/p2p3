package com.fenlibao.p2p.sms.annotation;

import com.fenlibao.p2p.sms.variable.SmsThing;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/7/3.
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Thing {
    SmsThing value();
}
