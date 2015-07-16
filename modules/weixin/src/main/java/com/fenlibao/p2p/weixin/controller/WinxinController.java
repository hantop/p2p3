package com.fenlibao.p2p.weixin.controller;

import com.fenlibao.p2p.weixin.defines.CodeMsg;
import com.fenlibao.p2p.weixin.defines.OauthDefines;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.service.WxApi;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/6/18.
 */
@RestController
@RequestMapping("/weixin")
public class WinxinController {

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
        Serializable result = this.wxApi.process(requestbody, host);
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
        String url = request.getScheme() + "://" + request.getServerName() + request.getServletPath() + "?" + request.getQueryString();
        // js_sdk签名
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> signature = wxApi.signature(url);
        modelAndView.addObject("ret", signature);
        String[] params = state.split(Constants.separator);
        for (String param : params) {
            String[] singleParams = param.split(Constants.assignment);
            modelAndView.addObject(singleParams[0], singleParams[1]);
        }
        //页面授权获取网页信息
        OauthDefines oauthDefines = this.wxApi.oauth2(code, state);
        //授权成功
        if (oauthDefines.getCode() == CodeMsg.SUCCESS) {
            //成功
            modelAndView.setViewName(viewName);

            String regex = Constants.FROM_USER_NAME + Constants.assignment + "\\w*" + Constants.separator;

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(state);
//            String replace = "fromUserName" + Constants.assignment + oauthDefines.getTarget().toString() + Constants.separator;
            String replace = Constants.FROM_USER_NAME + Constants.assignment + oauthDefines.getTarget().toString() + Constants.separator;
            String newState = matcher.replaceAll(replace);
            String host = request.getScheme() + "://" + request.getServerName() + WxApi.TOW_LEVEL_DOMAIN_URL;
            String redirectUrl = String.format(host, "zhaopin/zhaopin");
            redirectUrl = wxApi.generateOauth2Url(redirectUrl, newState);
            modelAndView.addObject("link", redirectUrl);


            return modelAndView;
        }
        //授权失败，重新封装url，跳转到微信授权，通过微信重新跳转到本方法进行授权
        int lastIndex = url.lastIndexOf("&code=");
        if (lastIndex > 0) {
            url = url.substring(0, lastIndex);
        }
        String redirect = this.wxApi.generateOauth2Url(url, state);
        modelAndView.setViewName("redirect:" + redirect);
        return modelAndView;
    }
}
