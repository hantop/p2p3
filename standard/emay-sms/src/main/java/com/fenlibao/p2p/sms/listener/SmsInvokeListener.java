package com.fenlibao.p2p.sms.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.sms.config.SmsConfig;
import com.fenlibao.p2p.sms.domain.*;
import com.fenlibao.p2p.sms.event.SmsInvokeEvent;
import com.fenlibao.p2p.sms.persistence.ChargeLogMapper;
import com.fenlibao.p2p.sms.persistence.RegisterInfoMapper;
import com.fenlibao.p2p.sms.persistence.SignMapper;
import com.fenlibao.p2p.sms.persistence.TaskMapper;
import com.fenlibao.p2p.sms.service.SmsApi;
import com.fenlibao.p2p.sms.variable.SendVariable;
import com.fenlibao.p2p.sms.variable.SmsThing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * 微信方法监听器
 * Created by Administrator on 2015/7/2.
 */
@Component
public class SmsInvokeListener implements ApplicationListener<SmsInvokeEvent> {

    private final static Logger log = LoggerFactory.getLogger(SmsInvokeListener.class);

    @Inject
    private SignMapper signMapper;

    @Inject
    private RegisterInfoMapper registerInfoMapper;

    @Inject
    private ChargeLogMapper chargeLogMapper;

    @Inject
    private TaskMapper taskMapper;

    @Override
    public void onApplicationEvent(SmsInvokeEvent event) {
        if (log.isInfoEnabled()) {
            SmsInvokeListener.log.info("短信sdk操作:{}", JSON.toJSONString(event, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        SmsApi smsApi = event.getSmsApi();
        SmsConfig config = smsApi.getSmsConfig();
        Log log = event.getLog();
        Object[] args = event.getArgs();
        String thing = log.getThing();
        if (thing.equals(SmsThing.REGISTER_EX.toString()) || thing.equals(SmsThing.LOGOUT.toString())) {
            //注册序列号, //注销
            Sign smsSign = new Sign(config.getSoftwareSerialNo(), config.getPwd(), config.getSpecialNo(), config.getKey(), log.getId());
            signMapper.insertSelective(smsSign);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("注册/注销序列号:{}", JSON.toJSONString(smsSign, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
            }
        } else if (thing.equals(SmsThing.REGIST_DETAIL_INFO.toString())) {
            //注册企业信息
            String name = args[0] != null ? args[0].toString() : null;
            String linkMan = args[1] != null ? args[1].toString() : null;
            String phoneNum = args[2] != null ? args[2].toString() : null;
            String mobile = args[3] != null ? args[3].toString() : null;
            String email = args[4] != null ? args[4].toString() : null;
            String fax = args[5] != null ? args[5].toString() : null;
            String address = args[6] != null ? args[6].toString() : null;
            String postcode = args[7] != null ? args[7].toString() : null;
            RegisterInfo registerInfo = new RegisterInfo(config.getSoftwareSerialNo(), name, linkMan, phoneNum, mobile, email, fax, address, postcode, log.getId());
            registerInfoMapper.insertSelective(registerInfo);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("注册企业信息:{}", JSON.toJSONString(registerInfo, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
            }
        } else if (thing.equals(SmsThing.CHARGE_UP.toString())) {
            //充值记录
            String cardNo = args[0] != null ? args[0].toString() : null;
            String cardPass = args[1] != null ? args[1].toString() : null;
            ChargeLog chargeLog = new ChargeLog(cardNo, cardPass, log.getId());
            this.chargeLogMapper.insertSelective(chargeLog);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("充值:{}", JSON.toJSONString(chargeLog, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
            }
        } else if (thing.matches("send.*")) {
            //发送信息
            String[] mobiles = args[0] != null ? (String[]) args[0] : null;
            String smsContent = args[1] != null ? args[1].toString() : null;
            Task task = new Task(System.currentTimeMillis(), SendVariable.Z, Arrays.asList(mobiles).toString(), smsContent, log.getId());
            taskMapper.insertSelective(task);
            if (SmsInvokeListener.log.isInfoEnabled()) {
                SmsInvokeListener.log.info("发送短信:{}", JSON.toJSONString(task, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
            }
        }
    }
}
