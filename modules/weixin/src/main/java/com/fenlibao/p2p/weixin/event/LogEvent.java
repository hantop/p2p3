package com.fenlibao.p2p.weixin.event;

import com.fenlibao.p2p.weixin.domain.Log;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/7/6.
 */
public class LogEvent extends ApplicationEvent {

    private Log log;

    private Object returnValue;

    public LogEvent(Object source, Log log, Object returnValue) {
        super(source);
        this.log = log;
        this.returnValue = returnValue;
    }

    public Log getLog() {
        return log;
    }

    public Object getReturnValue() {
        return returnValue;
    }
}
