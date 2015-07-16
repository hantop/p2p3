package com.fenlibao.p2p.weixin.config;

import com.fenlibao.p2p.constant.annotation.PropMap;
import com.fenlibao.p2p.constant.domain.Constant;
import com.fenlibao.p2p.constant.service.ConstantService;
import com.fenlibao.p2p.constant.util.Utils;
import com.fenlibao.p2p.weixin.variable.WxVariable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lenovo on 2015/5/31.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WeixinConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Inject
    private ConstantService constantService;//常量service

    @PropMap("WX_APP_ID")
    @NotNull(message = "应用ID不能为空")
    private String appId;//应用ID

    @PropMap("WX_APP_SECRET")
    @NotNull(message = "应用密钥不能为空")
    private String appSecret;//应用密钥

    @PropMap("WX_TOKEN_KEY")
    @NotNull(message = "微信token不能为空")
    private String tokenKey;//微信token

    @PropMap("WX_NAME")
    @NotNull(message = "微信二维码名称不能为空")
    private String name = "微信二维码";//

    @PropMap("WX_SUFFIX")
    @NotNull(message = "微信二维码后缀名不能为空")
    private String suffix = ".jpg";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //防止重复执行。
        if (event.getApplicationContext().getParent() == null) {
            List<Constant> constants = this.constantService.selectByType(WxVariable.WEIXIN);
            //获取数据库配置的常量值，初始化配置
            Utils.setMapValue(constants, this);
        }
    }

}
