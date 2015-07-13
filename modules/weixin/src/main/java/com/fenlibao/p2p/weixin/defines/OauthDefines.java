package com.fenlibao.p2p.weixin.defines;

/**
 * Created by Administrator on 2015/7/8.
 */
public class OauthDefines {

    private Object target;
    private CodeMsg code;

    public OauthDefines(CodeMsg code) {
        this.code = code;
    }

    public OauthDefines(CodeMsg code,Object target) {
        this.target = target;
        this.code = code;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public CodeMsg getCode() {
        return code;
    }

    public void setCode(CodeMsg code) {
        this.code = code;
    }
}
