package com.fenlibao.p2p;

import cn.emay.sdk.client.api.MO;
import cn.emay.sdk.client.api.StatusReport;
import com.alibaba.fastjson.JSON;
import com.fenlibao.p2p.sms.message.Message;
import com.fenlibao.p2p.sms.service.SmsApi;
import com.thoughtworks.xstream.XStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/2.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SimpleApplication.class)
public class SmsTest {

    private XStream xStream = new XStream();


    @Before
    public void before() {
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(Message.class);
    }
//
//    @Inject
//    private SmsApi smsApi;
//
//    @Test
//    public void registEx() {
//        Object result = smsApi.registEx("abc");
//        System.out.println(JSON.toJSON(result));
//    }
//
//    @Test
//    public void registDetailInfo() {
//        //  int registDetailInfo(String name, String linkMan, String phoneNum, String mobile, String email, String fax, String address, String postcode);
//        smsApi.registDetailInfo("分利宝","陆世明","18589221737","18589221737","business@fenlibao.com","020-38773163","广州市","518140");
//    }
//
//    @Test
//    public void chargeUp() {
//        smsApi.chargeUp("cardNo","cardPass");
//    }
//
//    @Test
//    public void sendSMS() {
//        smsApi.sendSMS(new String[]{"18581833858"},"要有梦想，即使很遥远",5);
//    }
//
//    @Test
//    public void logout() {
//        smsApi.logout();
//    }
//
//    @Test
//    public void getMO() {
//        try {
//            List<MO> mos = smsApi.getMO();
//            for(MO mo : mos) {
//                System.out.println(mo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getReport() {
//        try {
//            List<StatusReport> reports = smsApi.getReport();
//            for(StatusReport report : reports) {
//                System.out.println(report);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getBalance() {
//        try {
//            double b = smsApi.getBalance();
//            System.out.println(b);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void sendMsgTest() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept","application/json");
//        headers.add("Content-Type","application/json;charset=utf-8");
//
//        Message message = new Message();
//        message.setMobiles(new String[]{"18581833858"});
//        message.setContent("短信内容");
//        String requestJson = JSON.toJSONString(message);
//        System.out.println(requestJson);
//        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/sms/send";
////        restTemplate.put(url,entity);
//        Message result = restTemplate.postForObject(url, entity, Message.class);
//        System.out.println(JSON.toJSON(result));
//    }

//    @Test
//    public void sendMsgTestXml() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept","application/xml");
//        headers.add("Content-Type","application/xml;charset=utf-8");
//
//        Message message = new Message();
//        message.setMobiles(new String[]{"18581833858"});
//        message.setContent("短信内容");
//        String xml = xStream.toXML(message);
//        System.out.println(xml);
//        HttpEntity<String> entity = new HttpEntity<String>(xml,headers);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/sms/send?version";
////        restTemplate.put(url,entity);
//        String result = restTemplate.postForObject(url, entity,String.class);
//        System.out.println(result);
//    }

}
