package com.fenlibao.p2p.sms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.sms.defines.CodeMsg;
import com.fenlibao.p2p.sms.message.Message;
import com.fenlibao.p2p.sms.service.SmsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by Administrator on 2015/7/10.
 */
@RestController
@RequestMapping("/sms")
public class SmsController {
    private static final Logger log = LoggerFactory.getLogger(SmsController.class);
    @Inject
    private SmsApi smsApi;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/send", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.POST,params = "version")
    public
    @ResponseBody
    Message send(@RequestBody final Message message) {
        if (log.isInfoEnabled()) {
            log.info("接收待发送的短信信息:{}", JSON.toJSONString(message, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        CodeMsg result = (CodeMsg) smsApi.sendSMS(message.getMobiles(), message.getContent(), message.getPriority());
        message.setErrorcode(result.getErrorcode());
        message.setErrmsg(result.getErrmsg());
        message.setSource(result.getSource());
        if (log.isInfoEnabled()) {
            log.info("返回发送短信结果信息:{}", JSON.toJSONString(message, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return message;
    }
}
