package com.json.extract.util;
import com.alibaba.fastjson2.JSON;
import com.json.extract.dto.GeoMetaModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Optional;
import java.util.UUID;

/**
 * @author mingwei
 */
public final class MdcUtil {

    /**
     * 添加MDC log变量
     * @param logDomain
     */
    public static void putLogMdc(MdcDomain logDomain) {
        Optional.ofNullable(logDomain).ifPresent(domain -> {
            notBlankOptionalDefault(MdcUtil.REQUEST_ID, domain.getRequestId(), UUID.randomUUID().toString().replace("-", ""));
            notBlankOptional(MdcUtil.USER_ID, domain.getUserId());
            notBlankOptional(MdcUtil.CLIENT_IP, domain.getClientIp());
            notBlankOptional(MdcUtil.APP_ID, domain.getAppId());
        });
    }

    /**
     * 清理MDC变量
     */
    public static void removeMdcParams() {
        MDC.remove(MdcUtil.REQUEST_ID);
        MDC.remove(MdcUtil.APP_ID);
        MDC.remove(MdcUtil.USER_ID);
        MDC.remove(MdcUtil.CLIENT_IP);
        MDC.remove(MdcUtil.ORDER_GEO_META);
    }

    public static Long startTime() {
        return System.currentTimeMillis();
    }

    public static Long cost(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * 应用ID
     */
    public static final String APP_ID = "appId";

    /**
     * 业务请求串联ID
     */
    public static final String REQUEST_ID = "requestId";

    /**
     * 用户ID
     */
    public static final String USER_ID = "userId";

    /**
     * 客户端IP
     */
    public static final String CLIENT_IP = "clientIp";

    /**
     * 客户端IP
     */
    public static final String ORDER_GEO_META = "orderGeoMeta";

//    /**
//     * 操作时间
//     */
//    public static final String OPERATE_TIME = "operateTime";
//
//    /**
//     * 操作URL
//     */
//    public static final String OPERATE_URL = "operateUrl";
//
//    /**
//     * 操作名称
//     */
//    public static final String OPERATE_NAME = "operateName";

    private static void notBlankOptionalDefault(String key, String val, String defaultValue){
        if (StringUtils.isNotBlank(val)) {
            MDC.put(key, val);
        } else {
            MDC.put(key, defaultValue);
        }
    }

    private static void notBlankOptional(String key, String val){
        if (StringUtils.isNotBlank(val)) {
            MDC.put(key, val);
        }
    }

    public static String getRequestId() {
        return MDC.get(REQUEST_ID);
    }

    /**
     * 清理MDC变量
     */
    public static void putOrderGeoMeta(GeoMetaModel geoMetaUpdate) {
        if (geoMetaUpdate == null) {
            return;
        }

        String geoMetaStr = MDC.get(ORDER_GEO_META);
        GeoMetaModel geoMetaCurrent = geoMetaStr != null ? JSON.parseObject(geoMetaStr, GeoMetaModel.class) : new GeoMetaModel();

        if (geoMetaUpdate.getSendAddressPoi() != null) {
            geoMetaCurrent.setSendAddressPoi(geoMetaUpdate.getSendAddressPoi());
        }

        if (geoMetaUpdate.getSendAddressSegment() != null) {
            geoMetaCurrent.setSendAddressSegment(geoMetaUpdate.getSendAddressSegment());
        }

        if (geoMetaUpdate.getSendAddressAoi_10() != null || geoMetaUpdate.getSendAddressAoi_50() != null) {
            geoMetaCurrent.setSendAddressAoi_10(geoMetaUpdate.getSendAddressAoi_10());
            geoMetaCurrent.setSendAddressAoi_50(geoMetaUpdate.getSendAddressAoi_50());
        }

        MDC.put(ORDER_GEO_META, JSON.toJSONString(geoMetaCurrent));
    }

    public static GeoMetaModel getOrderGeoMeta() {
        return JSON.parseObject(MDC.get(ORDER_GEO_META), GeoMetaModel.class);
    }

    public static void put(String key, String val) {
        if (StringUtils.isBlank(MDC.get(key))) {
            MDC.put(key, val);
        }
    }

    public static String getId() {
        return MDC.get(REQUEST_ID);
    }

    public static void clear() {
        MDC.clear();
    }

    public static void putMDCId(String mdcId ) {
        put(REQUEST_ID, mdcId);
    }
}
