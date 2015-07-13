package com.fenlibao.p2p.weixin.interceptor;

import com.alibaba.fastjson.JSON;
import com.fenlibao.p2p.weixin.annotation.Thing;
import com.fenlibao.p2p.weixin.config.WeixinConfig;
import com.fenlibao.p2p.weixin.defines.CodeMsg;
import com.fenlibao.p2p.weixin.domain.Log;
import com.fenlibao.p2p.weixin.event.LogEvent;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.WxApiMsg;
import com.fenlibao.p2p.weixin.persistence.LogMapper;
import com.fenlibao.p2p.weixin.service.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/6/10.
 */
@Aspect
@Configuration
public class WeixinInterceptor {

    private final static Logger log = LoggerFactory.getLogger(WeixinInterceptor.class);

    @Autowired
    private WeixinConfig weixinConfig;



    @Autowired
    private ApplicationEventPublisher publisher;

    @Pointcut("execution (* com.fenlibao.p2p.weixin.proxy.WeixinProxy.http* (..)) ")
    public void aspect() {
    }

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("before " + joinPoint);
        }
    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("after " + joinPoint);
        }
    }


    @Around(value = "aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        String thingName = methodSignature.getName();
        Thing thing = targetMethod.getAnnotation(Thing.class);
        if(thing != null) {
            thingName = thing.value().toString();
        }
        //访问目标方法的参数：
        Object[] args = joinPoint.getArgs();
        String signature = methodSignature.toLongString();


        Object returnValue = null;
        try {
            //执行目标方法
            returnValue = joinPoint.proceed(args);
            if (returnValue instanceof WxApiMsg) {
                WxApiMsg message = (WxApiMsg) returnValue;
                CodeMsg codeMsg = handleException(message);
                Log log = new Log(weixinConfig.getAppId(),target.getClass().getName(),signature, JSON.toJSONString(args),String.valueOf(codeMsg.getErrorcode()),codeMsg.getErrmsg(),thingName);
                publisher.publishEvent(new LogEvent(this,log,returnValue));
                if(codeMsg != CodeMsg.SUCCESS) {
                    throw new WeixinException("微信错误消息：" + codeMsg.getErrmsg(), codeMsg.getErrorcode());
                }
            }
        } catch (WeixinException ex) {
            throw ex;
        }
        return returnValue;
    }


    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("afterReturn " + joinPoint);
        }
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Throwable ex) throws Throwable {
        if (log.isInfoEnabled()) {
            log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
        }
        throw ex;
    }

    private CodeMsg handleException(WxApiMsg wxApiMsg) throws WeixinException {
        CodeMsg[] values = CodeMsg.values();
        for (CodeMsg status : values) {
            int errcode = wxApiMsg.getErrcode();
            if (errcode != 0 && status.getErrorcode() == errcode) {
                if (log.isErrorEnabled()) {
                    log.error("微信错误消息：" + status.getErrmsg());
                }
                return status;
//                throw new WeixinException("微信错误消息：" + status.getErrmsg(), errcode);
            }
        }
        return CodeMsg.SUCCESS;
    }
}
