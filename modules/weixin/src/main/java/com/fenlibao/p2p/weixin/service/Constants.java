package com.fenlibao.p2p.weixin.service;

/**
 * Created by Administrator on 2015/6/3.
 */
public interface Constants {

    String separator = ",";
    String assignment = "=";
    String FROM_USER_NAME = "fromUserName";


    String TOW_LEVEL_DOMAIN_URL = "/weixin/oauthsns?view=%s";

    //token 接口
    String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    String OAUTH2_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";//用户授权，获取token

    //拉取用户信息(需scope为 snsapi_userinfo) http：GET（请使用https协议）
    String SNSAPI_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";//二维码ticket url

    String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    String CARD_QRCODE_URL = "https://api.weixin.qq.com/card/qrcode/create?access_token=%s";//二维码卡券投放的ticket url

    String QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";


    String FANS_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

    //appid,重定向url，授权类型，重定向的参数
    //appid,重定向url，
//    String OAUTH2_ACCESS_TOKEN = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect";
    String OAUTH2_ACCESS_TOKEN = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    String JSAPI_SIGN_URL = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";//微信js_api校验

    String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";//发送模板消息

    String POI_URL = "http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=%s";//查询门店信息

    String CARD_TESTWHITELIST_URL = "https://api.weixin.qq.com/card/testwhitelist/set?access_token=%s";//卡券设置测试白名单

    String CONSUME_URL = "https://api.weixin.qq.com/card/code/consume?access_token=%s";//核销Code接口

    String DECRYPT_URL = "https://api.weixin.qq.com/card/code/decrypt?access_token=%s";//Code解码接口URL

    String CODE_CARD_URL = "https://api.weixin.qq.com/card/code/get?access_token=%s";//查询code 根据code查询卡券信息

    String USER_CARD_LIST_URL = "https://api.weixin.qq.com/card/user/getcardlist?access_token=%s";//获取用户已领取卡券接口,用于获取用户卡包里的，属于该appid下的卡券。

    String CARD_URL = "https://api.weixin.qq.com/card/get?access_token=%s";// 查看卡券详情  调用该接口可查询卡券字段详情及卡券所处状态。建议开发者调用卡券更新信息接口后调用该接口验证是否更新成功。

    String BATCH_CARD_URL = "https://api.weixin.qq.com/card/batchget?access_token=%s";//批量查询卡列表

    String MESSAGE_MASS_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s";

    //微信token
    enum TokeyType {
        ACCESS_TOKEN,//回话tokenn
        OAUTH2_ACCESS_TOKEN;//网页回话token
    }

    //微信ticket类型
    enum TicketType {
        QR_CARD_TICKET,//卡券ticket
        QR_TICKET,//二维码token
        JSAPI_TICKET;//网页交互ticket
    }

    enum QrcodeType {
        QR_CARD,//投放卡券
        QR_SCENE,//临时二维码
        QR_LIMIT_SCENE;//永久二维码
    }

    enum SnsapiScope {
        snsapi_base,//临时二维码
        snsapi_userinfo;//永久二维码
    }
}
