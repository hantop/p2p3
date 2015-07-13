package com.fenlibao.p2p;

import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.template.TemplateMsg;
import com.fenlibao.p2p.weixin.message.template.TemplateMsgData;
import com.fenlibao.p2p.weixin.service.WxApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/9.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SimpleApplication.class)
public class WeixinTest {

//    @Autowired
//    private WxApi wxApi;
//
//    @Test
//    public void send() throws WeixinException {
//        TemplateMsg templateMsg = new TemplateMsg("o5D9Ts8qEfQy73VwwTOeUbG34Sfw","tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI","http://weixin.qq.com/download","#FF0000");
//        Map<String,TemplateMsgData> data = new HashMap<>();
//        data.put("first",new TemplateMsgData("陆先生","#173177"));
//        data.put("account",new TemplateMsgData("business@fenlibao.com","#173177"));
//        data.put("time",new TemplateMsgData(new Date().toLocaleString(),"#173177"));
//        data.put("type",new TemplateMsgData("线上消费赠返积分","#173177"));
//        data.put("creditChange",new TemplateMsgData("到账","#173177"));
//        data.put("number",new TemplateMsgData("1500","#173177"));
//        data.put("creditName",new TemplateMsgData("账户积分","#173177"));
//        data.put("amount",new TemplateMsgData("20000积分","#173177"));
//        data.put("remark",new TemplateMsgData("您可以点击下方菜单-我的账户，随时查询账户余额","#173177"));
//        templateMsg.setData(data);
//        Message message =  wxApi.send(templateMsg);
//        System.out.println(message);
//    }

}
