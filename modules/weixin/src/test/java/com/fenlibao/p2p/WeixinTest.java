package com.fenlibao.p2p;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.weixin.defines.MenuType;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.message.WxMsg;
import com.fenlibao.p2p.weixin.message.card.Card;
import com.fenlibao.p2p.weixin.message.card.CardTypeValue;
import com.fenlibao.p2p.weixin.message.card.UserCard;
import com.fenlibao.p2p.weixin.message.card.req.ReqBatchCatch;
import com.fenlibao.p2p.weixin.message.card.req.ReqUserCard;
import com.fenlibao.p2p.weixin.message.menu.Button;
import com.fenlibao.p2p.weixin.message.req.ReqTicket;
import com.fenlibao.p2p.weixin.message.req.White;
import com.fenlibao.p2p.weixin.message.template.TemplateMsg;
import com.fenlibao.p2p.weixin.message.template.TemplateMsgData;
import com.fenlibao.p2p.weixin.proxy.WeixinProxy;
import com.fenlibao.p2p.weixin.service.WxApi;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2015/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SimpleApplication.class)
public class WeixinTest {

    private final static Logger log = LoggerFactory.getLogger(WeixinTest.class);

    @Inject
    private WxApi wxApi;

    @Inject
    private WeixinProxy weixinProxy;

    @Test
    public void send() throws WeixinException {
        TemplateMsg templateMsg = new TemplateMsg("o5D9Ts8qEfQy73VwwTOeUbG34Sfw", "tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI", "http://weixin.qq.com/download", "#FF0000");
        Map<String, TemplateMsgData> data = new HashMap<>();
        data.put("first", new TemplateMsgData("陆先生", "#173177"));
        data.put("account", new TemplateMsgData("business@fenlibao.com", "#173177"));
        data.put("time", new TemplateMsgData(new Date().toLocaleString(), "#173177"));
        data.put("type", new TemplateMsgData("线上消费赠返积分", "#173177"));
        data.put("creditChange", new TemplateMsgData("到账", "#173177"));
        data.put("number", new TemplateMsgData("1500", "#173177"));
        data.put("creditName", new TemplateMsgData("账户积分", "#173177"));
        data.put("amount", new TemplateMsgData("20000积分", "#173177"));
        data.put("remark", new TemplateMsgData("您可以点击下方菜单-我的账户，随时查询账户余额", "#173177"));
        templateMsg.setData(data);
        Message message = wxApi.send(templateMsg);
        System.out.println(message);
    }

    @Test
    public void testWhiteList() {
        White white = new White();
        white.addOpenid("o5D9Ts8qEfQy73VwwTOeUbG34Sfw");
        white.addUsername("zhaobobo2014");
        WxMsg wxMsg = weixinProxy.testwhitelist(white);
        System.out.println(JSON.toJSONString(wxMsg));
    }

    @Test
    public void getUserCardList() {
        ReqUserCard reqUserCard = new ReqUserCard("o5D9Ts8qEfQy73VwwTOeUbG34Sfw", null);

        UserCard userCard = this.wxApi.getUserCardList(reqUserCard);

        log.info(JSON.toJSONString(userCard, SerializerFeature.PrettyFormat));
    }

    @Test
    public void getCard() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("card_id", "p5D9Ts6TAHJIL8z_NqS9sy6RGcOw");
        Card card = this.weixinProxy.getCard(jsonObject);
        System.out.println(card);
    }


    @Test
    public void httpCardQrcode() throws IOException {
        ReqTicket reqTicket = new ReqTicket("p5D9Tszt2VCNzNeX3BsBl0JjLhVo", 2, null, null, null, null);
        Qrcode qrcode = this.weixinProxy.httpQrcode(reqTicket, "qr_card");
        ByteArrayInputStream bw = new ByteArrayInputStream(qrcode.getBytes());
        FileUtils.copyInputStreamToFile(bw, new File("d:/二维码.jpg"));
    }

    @Test
    public void signatureCard() {
        Map<String, String> signature = this.wxApi.signature(CardTypeValue.CASH, null, null);
        log.info(JSON.toJSONString(signature));
    }

    @Test
    public void signatureCardExt() {
        List<String> cardsId = new ArrayList<>();
        cardsId.add("p5D9Ts6TAHJIL8z_NqS9sy6RGcOw");
        cardsId.add("p5D9Tszt2VCNzNeX3BsBl0JjLhVo");
        List<Map<String, Object>> signature = this.wxApi.signature(cardsId, null, null);
        log.info(JSON.toJSONString(signature, SerializerFeature.PrettyFormat));
    }

    @Test
    public void batchCard() {
        ReqBatchCatch batch = new ReqBatchCatch(0, 10);
        Card card = this.wxApi.batchCard(batch);
        log.info(JSON.toJSONString(card, SerializerFeature.PrettyFormat));
    }

    @Test
    public void signature() {
        ReqBatchCatch batch = new ReqBatchCatch(0, 10);
        List<Map<String, Object>> result = this.wxApi.signature(batch, null, null);
        log.info(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    @Test
    public void createMenu() {

        Button btn1 = new Button();
        btn1.setName("今日歌曲");
        btn1.setType(MenuType.CLICK);
        btn1.setKey("V1001_TODAY_MUSIC");


        Button btn2 = new Button();
        btn2.setName("菜单");

        Button sub1 = new Button();
        sub1.setName("搜索");
        sub1.setType(MenuType.VIEW);
        sub1.setUrl("http://www.soso.com/");

        Button sub2 = new Button();
        sub2.setName("视频");
        sub2.setType(MenuType.VIEW);
        sub2.setUrl("http://v.qq.com/");

        Button sub3 = new Button();
        sub3.setName("赞一下我们");
        sub3.setType(MenuType.CLICK);
        btn1.setKey("V1001_GOOD");

        btn2.setSubButton(Arrays.asList(new Button[]{sub1, sub2, sub3}));

        List<Button> buttons = Arrays.asList(btn1, btn2);
        WxMsg message = this.wxApi.createMenu(buttons);
        log.info("自定义菜单：{}" ,JSON.toJSONString(message, SerializerFeature.PrettyFormat));
    }
}
