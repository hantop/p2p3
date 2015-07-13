package com.fenlibao.p2p.sms.interceptor;

import com.alibaba.fastjson.JSON;
import com.fenlibao.p2p.sms.annotation.Thing;
import com.fenlibao.p2p.sms.config.SmsConfig;
import com.fenlibao.p2p.sms.defines.CodeMsg;
import com.fenlibao.p2p.sms.domain.Log;
import com.fenlibao.p2p.sms.event.SmsInvokeEvent;
import com.fenlibao.p2p.sms.persistence.LogMapper;
import com.fenlibao.p2p.sms.service.SmsApi;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.lang.reflect.Method;


/**
 * 短信日志记录
 * Created by Administrator on 2015/7/2.
 */
@Aspect
@Configuration
public class SmsLogInterceptor {

    private final static Logger log = LoggerFactory.getLogger(SmsLogInterceptor.class);

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Pointcut("execution (* com.fenlibao.p2p.sms.service.SmsApi.* (..)) ")
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
        SmsApi target = (SmsApi) joinPoint.getTarget();
        SmsConfig config = target.getSmsConfig();


        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        Class<?> returnType = targetMethod.getReturnType();
        String thingName = methodSignature.getName();
        Thing thing = targetMethod.getAnnotation(Thing.class);
        if(thing != null) {
            thingName = thing.value().toString();
        }

        Object[] args = joinPoint.getArgs();
        String signature = methodSignature.getName();


        if (signature.matches("send.*")) {
            String content = config.getSign() + (args[1] != null ? args[1].toString() : null);
            args[1] = content;
        }
        signature = methodSignature.toLongString();
        Object returnValue = joinPoint.proceed(args);
        CodeMsg codeMsg = CodeMsg.handCode(returnValue);

        Log log = new Log(config.getSoftwareSerialNo(), target.toString(), signature, JSON.toJSONString(args), returnValue != null ? returnValue.toString() : "", codeMsg.getSource(), codeMsg.getErrmsg(), thingName);
        logMapper.insertSelective(log);
        publisher.publishEvent(new SmsInvokeEvent(this, target, args, log));
        if(returnType == Serializable.class) {
            return codeMsg;
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
}
