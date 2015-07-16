package com.fenlibao.p2p.sms.service.impl;

import cn.emay.sdk.client.api.Client;
import cn.emay.sdk.client.api.MO;
import cn.emay.sdk.client.api.StatusReport;
import com.fenlibao.p2p.constant.domain.Constant;
import com.fenlibao.p2p.constant.service.ConstantService;
import com.fenlibao.p2p.constant.util.Utils;
import com.fenlibao.p2p.sms.config.SmsConfig;
import com.fenlibao.p2p.sms.defines.CodeMsg;
import com.fenlibao.p2p.sms.domain.Sign;
import com.fenlibao.p2p.sms.persistence.SignMapper;
import com.fenlibao.p2p.sms.service.SmsApi;
import com.fenlibao.p2p.sms.variable.SmsThing;
import com.fenlibao.p2p.sms.variable.SmsVariable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2015/6/30.
 */
@Service("smsApi")
public class SmsApiImpl implements SmsApi, ApplicationListener<ContextRefreshedEvent>,ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Client sdkclient;

    @Inject
    private ConstantService constantService;//常量service

    @Inject
    private SignMapper signMapper;//初始化注册信息

    private SmsConfig smsConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //防止重复执行。
        if (event.getApplicationContext().getParent() == null) {
            List<Constant> constants = this.constantService.selectByType(SmsVariable.SMS);//获取类型为短信的配置信息
            if (smsConfig == null) {
                smsConfig = new SmsConfig();
            }
            //获取数据库配置的常量值，初始化配置
            Utils.setMapValue(constants, smsConfig);
            setSmsConfig(smsConfig);
        }
    }

    @Override
    public SmsConfig getSmsConfig() {
        return this.smsConfig;
    }

    @Override
    public Serializable registEx(String serialpass) {
        return sdkclient.registEx(serialpass);
    }

    @Override
    public Serializable registDetailInfo(String name, String linkMan, String phoneNum, String mobile, String email, String fax, String address, String postcode) {
        return sdkclient.registDetailInfo(name, linkMan, phoneNum, mobile, email, fax, address, postcode);
    }

    @Override
    public Serializable logout() {
        return sdkclient.logout();
    }

    @Override
    public double getEachFee() {
        return sdkclient.getEachFee();
    }

    @Override
    public double getBalance() throws Exception {
        return sdkclient.getBalance();
    }

    @Override
    public Serializable chargeUp(String cardNo, String cardPass) {
        return sdkclient.chargeUp(cardNo, cardPass);
    }

    @Override
    public Serializable sendSMS(String[] mobiles, String smsContent, int smsPriority) {
        return sdkclient.sendSMS(mobiles, smsContent, smsPriority);
    }

    @Override
    public Serializable sendSMS(String[] mobiles, String smsContent, String addSerial, int smsPriority) {
        return sdkclient.sendSMS(mobiles, smsContent, addSerial, smsPriority);
    }

    @Override
    public Serializable sendSMSEx(String[] mobiles, String smsContent, String srcCharset, int smsPriority) {
        return sdkclient.sendSMSEx(mobiles, smsContent, srcCharset, smsPriority);
    }

    @Override
    public Serializable sendSMSEx(String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority) {
        return sdkclient.sendSMSEx(mobiles, smsContent, addSerial, srcCharset, smsPriority);
    }

    @Override
    public Serializable sendSMSEx(String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority, long smsID) {
        return sdkclient.sendSMSEx(mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID);
    }

    @Override
    public Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime) {
        return sdkclient.sendScheduledSMS(mobiles, smsContent, sendTime);
    }

    @Override
    public Serializable sendScheduledSMSEx(String[] mobiles, String smsContent, String sendTime, String srcCharset) {
        return sdkclient.sendScheduledSMSEx(mobiles, smsContent, sendTime, srcCharset);
    }

    @Override
    public Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime, String addSerial) {
        return sdkclient.sendScheduledSMS(mobiles, smsContent, sendTime,addSerial);
    }

    @Override
    public Serializable sendScheduledSMS(String[] mobiles, String smsContent, String sendTime, String addSerial, String srcCharset) {
        return sdkclient.sendScheduledSMS(mobiles, smsContent, sendTime,addSerial,srcCharset);
    }

    @Override
    public List<MO> getMO() throws Exception {
        return sdkclient.getMO();
    }

    @Override
    public List<StatusReport> getReport() throws Exception {
        return sdkclient.getReport();
    }

    @Override
    public Serializable serialPwdUpd(String serialPwd, String serialPwdNew) {
        return sdkclient.serialPwdUpd(serialPwd,serialPwdNew);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setSmsConfig(SmsConfig smsConfig) {
        Utils.validate(smsConfig)   ;
        this.smsConfig = smsConfig;
        /**
         * 实例化客户端，如果客服端已经注册过就不需要再次注册
         */
        try {
            this.sdkclient = new Client(smsConfig.getSoftwareSerialNo(), smsConfig.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据该配置校验是否已经注册过了
        Sign sign = signMapper.selectSelective(new Sign(smsConfig.getSoftwareSerialNo(), smsConfig.getPwd(), smsConfig.getKey()));
        if (sign == null || !sign.getLog().getThing().equals(SmsThing.REGISTER_EX.toString()) || (sign.getLog().getThing().equals(SmsThing.REGISTER_EX.toString()) && !sign.getLog().getCode().equals(String.valueOf(CodeMsg.SUCCESS.getErrorcode())))) {
            //未注册改序列号，进行序列号注册
            if(smsConfig.getAutoRegister() == true) {
                SmsApi smsApi = this.applicationContext.getBean(SmsApi.class);
                smsApi.registEx(smsConfig.getPwd());
            }
        }
    }
}
