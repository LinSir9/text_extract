package com.json.extract.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuqiang@sto.cn
 * @date 2021-06-10 14:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MdcDomain {
    /**
     * 应用ID
     */
    public String appId;

    /**
     * 业务请求串联ID
     */
    public String requestId;

    /**
     * 用户ID
     */
    public String userId;

    /**
     * 客户端IP
     */
    public String clientIp;

//    /**
//     * 操作时间
//     */
//    public String operateTime;
//
//    /**
//     * 操作URL
//     */
//    public String operateUrl;
//
//    /**
//     * 操作名称
//     */
//    public String operateName;
}
