package com.fenlibao.p2p.sms.controller;

import com.fenlibao.p2p.sms.defines.CodeMsg;
import com.fenlibao.p2p.sms.message.Message;
import com.fenlibao.p2p.sms.service.SmsApi;
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

    @Inject
    private SmsApi smsApi;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/send", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.POST,params = "version")
    public
    @ResponseBody
    Message send(@RequestBody final Message message) {
        CodeMsg result = (CodeMsg) smsApi.sendSMS(message.getMobiles(), message.getContent(), message.getPriority());
        message.setErrorcode(result.getErrorcode());
        message.setErrmsg(result.getErrmsg());
        message.setSource(result.getSource());
        return message;
    }
}
