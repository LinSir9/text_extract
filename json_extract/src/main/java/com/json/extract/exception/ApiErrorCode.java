package com.json.extract.exception;


public enum ApiErrorCode {

    SYSTEM_001("系统错误"),
    ERROR_ARGUMENT("参数错误"),
    ERROR_CANCEL("取消失败"),
    PUSH_KSSJ_FAIL("推送重量信息失败"),
    PUSH_WEIGHT_FAIL("推送平台重量信息失败"),
    SITE_ABNORMAL("寄件地址上门取件人员不足，暂不支持在线下单。"),
    EPIDEMIC("寄件地址受疫情影响，暂不支持在线下单。"),
    ORDER_INTERCEPTION_UNKNOWN("订单被拦截，原因未知"),
    ERROR_ADD_INSURANCE("新增保价失败"),
    ERROR_UPDATE_INSURANCE("更新保价信息失败"),
    ERROR_QUERY_CAN_INSURANCE("查询是否能保价失败"),
    ERROR_QUERY_INSURANCE_RATE("查询保价费率失败"),
    ERROR_QUERY_INSURANCE_BLACK_LIST("查询不可保价物品名称列表失败"),
    ERROR_BUY_INSURANCE("投保失败"),
    ERROR_CANCEL_INSURANCE("取消投保失败"),
    ERROR_CALCULATE_INSURANCE("计算保价费失败"),

    ERROR_INSURANCE_FEE_RULE("请联系管理员维护保价费率信息"),

    ERROR_ORDER_NOT_FOUND("无订单记录"),

    ERROR_CANCEL_UPDATE_FAILED("取消更新订单失败"),
    ERROR_FETCH_TIME_UPDATE_FAILED("改约更新订单失败"),

    ERROR_ORDER_NOT_INSURANCE("订单无保价申请"),

    ERROR_INSURANCE_CANNOT_DEL("保价申请不可删除"),

    ERROR_INSURANCE_CHECK("保价申请校验不通过"),

    ERROR_INSURANCE_NOT_SUPPORT("暂不支持投保申请"),

    PARAM_ERROR("参数校验失败"),
    PARAM_NULL("请求参数不可为空"),
    ERROR_PROHIBIT_GOODS("此物品类型为禁寄物品"),

    ERROR_GET_GEOGRAPHIC_LOCATION("根据地址，获取地理位置失败"),
    ORDER_EXPORT_ERROR("添加下载任务失败"),
    FILE_SIZE_LIMIT_ERROR("文件大小不能超过1M"),
    ALIPAY_ORDER_SYNC_ERROR("支付宝订单中心订单同步失败"),
    // 运费报价错误相关
    FREIGHT_QUOTE_UPLOAD_ERROR("运费报价上传失败"),
    FREIGHT_QUOTE_UPLOAD_OPERATOR_ERROR("运费报价上传用户信息为空"),
    FREIGHT_QUOTE_UPLOAD_EMPTY_ERROR("运费报价上传数据为空"),
    FREIGHT_QUOTE_QUERY_EMPTY_ERROR("运费报价查询数据为空"),
    FREIGHT_QUOTE_UPLOAD_YKG_ERROR("上传的首重重量不能为字符串"),
    FREIGHT_QUOTE_UPLOAD_FIRST_WEIGHT_PRICE_ERROR("上传的首重运费必须为数字"),
    FREIGHT_QUOTE_UPLOAD_CONTINUED_WEIGHT_PRICE_ERROR("上传的续重运费必须为数字"),
    FREIGHT_VERSION_EXIST_ERROR("版本不存在"),
    // 抽佣配置
    ERROR_COMMISSION_EFFECT_DATE("生效时间不能在今天之前"),
    ERROR_COMMISSION_PRODUCT("产品类型不正确"),
    ERROR_COMMISSION_DATA_EXIST("抽佣配置不存在"),
    ERROR_COMMISSION_PROV_BENEFIT("省区分润金额/占比不能大于总部抽佣金额/占比"),
    ERROR_COMMISSION_UPLOAD_EMPTY("抽佣线路上传文件不能为空"),
    ERROR_COMMISSION_LINE_UPLOAD_OPERATOR("抽佣线路上传用户信息为空"),
    ERROR_COMMISSION_LINE_UPLOAD_EMPTY("抽佣线路上传数据为空"),
    ERROR_COMMISSION_LINE_UPLOAD_MODE_EMPTY("上传线路数据抽佣模式配置为空"),
    ERROR_COMMISSION_LINE_UPLOAD_FIRST_EMPTY("上传线路数据首重抽佣配置必须为数字"),
    ERROR_COMMISSION_LINE_UPLOAD_CONTINUE_EMPTY("上传线路数据续重抽佣配置必须为数字"),
    ERROR_COMMISSION_LINE_UPLOAD_PROV_EMPTY("上传线路数据省区分润抽佣配置必须为数字"),
    ERROR_COMMISSION_LINE_UPLOAD("抽佣线路上传失败"),
    ERROR_COMMISSION_LINE_VERSION_EXIST("版本不存在"),
    ERROR_COMMISSION_RULE_QUERY_EXIST("抽佣规则配置查询不存在"),

    ERROR_NOT_FOUND("没有找到记录"),
    ERROR_DO_FAIL("处理失败，请稍后重试"),

    PRE_DISPATCH_FAIL("很遗憾，该订单已被其他快递员先接走~"),
    PRE_DISPATCH_ORDER_STATUS_NOT_SUPPORT_FAIL("很遗憾，该订单已被其他快递员先接走~"),

    INVALID_REQUEST("请求处理拒绝"),
    ERROR_NO_VALID_CONTENT("无有效内容"),
    ERROR_NEED_RETRY("服务繁忙请重试"),
    TIPS_HAS_REPEAT_ORDER("请注意，已存在相同收寄件人信息的订单"),
    ERROR_MAP_NO_SERVICE("寄件地址暂不支持上门取件"),
    REPEAT_MAN_CODE("接单小件员不允许重复"),
    MAN_CODE_EMPTY("接单小件员编号不能为空"),
    MAN_NAME_EMPTY("接单小件员名称不能为空"),
    QUERY_BIG_WORD_ERROR("获取段码信息失败"),
    QUERY_ROOKIE_EXCEPTION("地区管控"),
    PRINT_CODE_CHECK_FAIL("取件码验证失败"),
    PRINT_CODE_IS_BLANK_CHECK_FAIL("取件码不能为空, 请输入正确的取件码"),
    PRINT_CODE_CHECK_TIME_RESTRICTED("未到取件时间无法取件，请在预约时间内取件！\n如已取件，请记下取件码，并在预约时间内输入取件码完成操作；或引导客户修改上门时间\n如遇问题请钉钉联系：吴潇（1259400）"),
    CHECK_WEIGHT_FAIL("核重校验失败"),
    CHECK_WEIGHT_EXIST_FAIL("本次核重失败，平台已有核价重量: %skg，请进行下一步操作"),
    ERROR_NEED_RETRY_LATER("服务繁忙请稍后重试"),
    ERROR_CANCEL_ORDER_FINISHED("订单已完成不可取消"),
    ERROR_CANCEL_ORDER_CANCELED("订单已取消，请勿重复取消"),
    ORDER_CANCELED_NEED_SUCCESS("订单已成功取消"),
    ERROR_CANCEL_ORDER_TRANS("订单运输中不可取消"),
    ERROR_CANCEL_ORDER_DELIVERY("订单派件中不可取消"),
    ERROR_CANCEL_ORDER_SIGNED("订单已签收不可取消"),
    ERROR_CANCEL_NOT_SUPPORT_BATCH_CANCEL("不支持批量取消"),
    ERROR_CANCEL_NOT_OWN_ORDER("不允许取消其他来源的订单"),
    ERROR_REAL_NAME_CONFIRM("实名确认失败,请稍后重试"),
    ERROR_REAL_NAME_CONFIRMED("实名已确认，请勿反复提交"),
    ERROR_REAL_NAME_NOT_FOUND("实名信息不存在"),
    ERROR_PUSH_WEIGHT_TTQJ_CHECK("监控到重量偏差过大，请重新核重！"),
    ERROR_BILL_CODE_TOO_LONG("运单号不能超过20位"),
    ERROR_BILL_CODE_EMPTY("运单号不可为空"),
    ERROR_BILL_CODE_REPLACE_MAN_NOT_CORRECT("换单小件员不一致"),
    ERROR_NO_PERMISSION("无权限操纵"),
    ERROR_BILL_CODE_IS_USED("运单号已使用"),
    ERROR_CP_CODE_IS_NULL("快递公司编码不可为空"),
    ERROR_BILL_CODE_IS_EXIST("已绑定运单号"),

    ERROR_CANCEL_ORDER_TTQJ_TRANS("包裹已在运输中，不支持取消"),

    ORDER_TOP_MAX_ERROR("置顶超最大上限:"),
    ORDER_TOP_NOT_ERROR("置顶单号不存在"),
    ORDER_TOP_PROCESS_ERROR("部分置顶失败"),
    ORDER_TOP_ACTION_ERROR("操作类型异常"),

    ORDER_TOP_QUERY_ES_ERROR("查询失败,请稍后重试"),


    AOI_ERROR("系统繁忙,AOI小区分组暂不可用,请稍后重试"),

    NOT_SUPPORT_VIRTUAL_NUMBER("暂不支持虚拟号实名"),

    REAL_NAME_SYSTEM_CHECK_FAILED("实名系统校验失败"),
    REAL_NAME_CHECK_ID_CARD_FAILED("实名失败！请使用用户真实实名信息。"),

    ORDER_SOURCE_CODE_EMPTY("订单来源编码不能为空"),
    ORDER_SOURCE_NOT_EXIST("订单来源配置不存在"),
    ERROR_BATCH_GET_PERFORMANCE("批量获取履约配置异常"),
    ORDER_QUERY_GRAY_CONFIG_NOT_EXIST("加载失败，将自动跳转首页，请跳转后再次进入"),
    UPDATE_EBAY_CHILD_ORDER_FAIL("更新Ebay子订单失败"),
    UPDATE_PLATFORM_CHILD_ORDER_FAIL("更新平台子订单失败"),
    QUERY_ORDER_IDS_ERROR("查询订单号异常请稍后重试"),
    CHECK_ID_CARD_FAILED("二要素校验失败，请稍后再试"),
    CHECK_ID_CARD_REAL_NAME_FAILED("实名失败！应管局要求，用户证件姓名与号码需真实对应！请重新实名"),
    ADD_MATCH_LOG_ING("match_log日志正在添加中"),
    ORDER_STATUS_IS_ERROR("订单状态异常"),
    ;

    private String desc;

    ApiErrorCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
