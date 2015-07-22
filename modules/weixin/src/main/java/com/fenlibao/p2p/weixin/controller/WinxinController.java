package com.fenlibao.p2p.weixin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.weixin.defines.CodeMsg;
import com.fenlibao.p2p.weixin.defines.OauthDefines;
import com.fenlibao.p2p.weixin.message.Status;
import com.fenlibao.p2p.weixin.message.card.req.ReqBatchCatch;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.service.WxApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/6/18.
 */
@RestController
@RequestMapping("/weixin")
public class WinxinController {
    private static final Logger log = LoggerFactory.getLogger(WinxinController.class);
    @Inject
    private WxApi wxApi;

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public
    @ResponseBody
    Serializable process(
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr) {
        if (wxApi.checkSignature(signature, timestamp, nonce,
                echostr)) {
            return echostr;
        } else {
            return "";
        }
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST, headers = "Accept=application/xml")
    public
    @ResponseBody
    Serializable process(@RequestBody String requestbody, HttpServletRequest request) {
        String host = request.getScheme() + "://" + request.getServerName(); //服务器地址request.getServerName()
        if (log.isInfoEnabled()) {
            log.info("获取fans发送到微信后台的信息", requestbody);
        }
        Serializable result = this.wxApi.process(requestbody, host);
        if (log.isInfoEnabled()) {
            log.info("后台返回给微信fans的用户信息", result);
        }
        return result;
    }

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public List<Map<String, Object>> cards(@RequestParam(value = "openid", required = false) String openid, @RequestParam(value = "code", required = false) String code) {

        ModelAndView modelAndView = new ModelAndView();
        ReqBatchCatch batch = new ReqBatchCatch(0, 10);
        List<Status> statuses = new ArrayList<>();
        statuses.add(Status.CARD_STATUS_NOT_VERIFY);
        statuses.add(Status.CARD_STATUS_VERIFY_OK);
        batch.setStatusList(statuses);
        List<Map<String, Object>> result = this.wxApi.signature(batch, openid, code);
        if(log.isInfoEnabled()) {
            log.info("获取卡券列表信息：\n{}",JSON.toJSONString(result,SerializerFeature.PrettyFormat));
        }
        modelAndView.addObject("cardList", JSON.toJSON(result));

        return result;
    }

    /**
     * 网页授权
     *
     * @param code
     * @param viewName
     * @param state
     * @param request
     * @return
     */
    @RequestMapping(value = "/oauthsns")
    public ModelAndView oauthsns(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "view") String viewName, @RequestParam(value = "state") String state, HttpServletRequest request) {
        if (log.isInfoEnabled()) {
            log.info("微信授权");
            log.info("签名code:\n{}",code);
        }
        String url = request.getScheme() + "://" + request.getServerName() + request.getServletPath() + "?" + request.getQueryString();
        // js_sdk签名
        ModelAndView modelAndView = new ModelAndView();

        String[] params = state.split(Constants.separator);
        for (String param : params) {
            String[] singleParams = param.split(Constants.assignment);
            modelAndView.addObject(singleParams[0], singleParams[1]);
        }

        //页面授权获取网页信息
        OauthDefines oauthDefines = this.wxApi.oauth2(code, state);
        if (log.isInfoEnabled()) {
            log.info("微信授权结果{}", JSON.toJSONString(oauthDefines, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        //授权成功
        if (oauthDefines.getCode() == CodeMsg.SUCCESS) {
            Map<String, String> jsSdkSignature = wxApi.signature(url);
            log.info("js_sdk签名:{}\n",JSON.toJSONString(jsSdkSignature,SerializerFeature.PrettyFormat));

            modelAndView.addObject("js_sdk_signature", jsSdkSignature);
            //成功
            modelAndView.setViewName(viewName);
            String regex = Constants.FROM_USER_NAME + Constants.assignment + "\\w*" + Constants.separator;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(state);
            String replace = Constants.FROM_USER_NAME + Constants.assignment + oauthDefines.getTarget().toString() + Constants.separator;
            String newState = matcher.replaceAll(replace);
            String host = request.getScheme() + "://" + request.getServerName() + WxApi.TOW_LEVEL_DOMAIN_URL;
            String redirectUrl = String.format(host, "zhaopin/zhaopin");
            redirectUrl = wxApi.generateOauth2Url(redirectUrl, newState);
            modelAndView.addObject("link", redirectUrl);
            if (log.isInfoEnabled()) {
                log.info("微信授权成功返回信息：{}", JSON.toJSONString(modelAndView, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
            }
            return modelAndView;
        }
        //授权失败，重新封装url，跳转到微信授权，通过微信重新跳转到本方法进行授权
        int lastIndex = url.lastIndexOf("&code=");
        if (lastIndex > 0) {
            url = url.substring(0, lastIndex);
        }
        String redirect = this.wxApi.generateOauth2Url(url, state);
        modelAndView.setViewName("redirect:" + redirect);
        if (log.isInfoEnabled()) {
            log.info("微信授权失败返回信息：{}", JSON.toJSONString(modelAndView, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return modelAndView;
    }
}
