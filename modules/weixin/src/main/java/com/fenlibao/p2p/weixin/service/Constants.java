package com.fenlibao.p2p.weixin.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/3.
 */
public interface Constants {

    String separator = ",";
    String assignment = "=";
    String FROM_USER_NAME = "fromUserName";


    //    String TOW_LEVEL_DOMAIN = "/zhaopin/%s/%s?redirect=%s";
    String TOW_LEVEL_DOMAIN = "/weixin/oauthsns?view=%s";

    //token 接口
    String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    String OAUTH2_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";//用户授权，获取token

    //拉取用户信息(需scope为 snsapi_userinfo) http：GET（请使用https协议）
    String SNSAPI_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    String TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";//二维码ticket url

    String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    String QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

    String FANS = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

    //appid,重定向url，授权类型，重定向的参数
    //appid,重定向url，
//    String OAUTH2_ACCESS_TOKEN = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect";
    String OAUTH2_ACCESS_TOKEN = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    String JSAPI_SIGN = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";//微信js_api校验

    String TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";//发送模板消息

    //微信token
    enum TokeyType {

        ACCESS_TOKEN,//回话tokenn
        OAUTH2_ACCESS_TOKEN;//网页回话token

        private static final Map<String, TokeyType> stringToEnum = new HashMap<String, TokeyType>();

        static {
            for (TokeyType token : values()) {
                stringToEnum.put(token.toString(), token);
            }
        }

        public static TokeyType fromString(String symbol) {
            return stringToEnum.get(symbol);
        }
    }

    //微信ticket类型
    enum TicketType {

        QR_TICKET,//二维码token
        JSAPI_TICKET;//网页交互ticket

        private static final Map<String, TicketType> stringToEnum = new HashMap<String, TicketType>();

        static {
            for (TicketType token : values()) {
                stringToEnum.put(token.toString(), token);
            }
        }

        public static TicketType fromString(String symbol) {
            return stringToEnum.get(symbol);
        }
    }

    enum QrcodeType {
        QR_SCENE,//临时二维码
        QR_LIMIT_SCENE;//永久二维码

        private static final Map<String, QrcodeType> stringToEnum = new HashMap<String, QrcodeType>();

        static {
            // Initialize map from constant name to enum constant
            for (QrcodeType qrcode : values()) {
                stringToEnum.put(qrcode.toString(), qrcode);
            }
        }

        public static QrcodeType fromString(String symbol) {
            return stringToEnum.get(symbol);
        }
    }

    enum SnsapiScope {
        snsapi_base,//临时二维码
        snsapi_userinfo;//永久二维码

        private static final Map<String, SnsapiScope> stringToEnum = new HashMap<String, SnsapiScope>();

        static {
            // Initialize map from constant name to enum constant
            for (SnsapiScope snsapi : values()) {
                stringToEnum.put(snsapi.toString(), snsapi);
            }
        }

        public static SnsapiScope fromString(String symbol) {
            return stringToEnum.get(symbol);
        }
    }

}
