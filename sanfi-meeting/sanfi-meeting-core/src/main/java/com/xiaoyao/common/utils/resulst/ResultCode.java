package com.xiaoyao.common.utils.resulst;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 19:32
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
public enum  ResultCode {
    //##########TODO 请求成功 2**
    /** 成功 */
    SUCCESS("2000", "成功"),

    /** 操作失败 */
    FAIL("2001", "操作失败"),

    /** 数据已存在 */
    SUCCESS_IS_HAVE("2002", "数据已存在"),
    /** 没有结果 */
    NOT_DATA("2003", "没有结果"),

    //##########TODO 客户端错误 4**
    /** 没有登录 */
    NOT_LOGIN("4000", "没有登录"),

    /** 发生异常 */
    EXCEPTION("4001", "发生异常"),

    /** 系统错误 */
    SYS_ERROR("4002", "系统错误"),

    /** 参数错误 */
    PARAMS_ERROR("4003", "参数错误 "),

    /** 不支持或已经废弃 */
    NOT_SUPPORTED("4004", "不支持或已经废弃"),

    /** AuthCode错误 */
    INVALID_AUTHCODE("4005", "无效的AuthCode"),

    /** 太频繁的调用 */
    TOO_FREQUENT("4006", "太频繁的调用"),

    /** 未知的错误 */
    UNKNOWN_ERROR("4007", "未知错误"),

    /** 未设置方法 */
    NOT_METHOD("4008", "未设置方法");

    //##########TODO 服务器错误 5**

    private ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    /**对应状态码*/
    private String code;
    /**返回内容*/
    private String msg;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
