package com.antexploration.ant.InteractiveUtils.Interceptor;

/**
 * @author Licoy
 * @version 2018/4/18/10:54
 */

public enum ResponseCode {

    OK(1, "操作成功"),
    OkLOGIN(2, "登陆成功"),
    FAIL(-1, "操作失败"),
    APPID(-2, "APPID有误"),
    APPIDKEY(-3, "签名有误,请检查key"),
    TIME(-4, "请求已超时"),
    NONCE(-5, "nonce已存在"),
    ERRORPARAMETER(-6, "请核对请求参数"),
    ERROECODE(-7, "验证码错误"),
    Custom(-8, "自定义"),
    NoToken(-9, "自定义"),
    ;

    /**
     * 自定义返回值
     *
     * @param smg
     * @return
     */
    public static ResponseCode RetuenValue(String smg) {
        ResponseCode.Custom.msg = smg;
        return ResponseCode.Custom;
    }

    /**
     * 自定义返回值
     *
     * @param smg
     * @return
     */
    public static ResponseCode NoToken(String smg) {
        ResponseCode.NoToken.msg = smg;
        return ResponseCode.NoToken;
    }

    public Integer code;
    public String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
