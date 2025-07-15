/*
 * Copyright © 2020 http://www.sto.cn All rights reserved.
 */
package com.json.extract.dto;

/*
 * Created with IntelliJ IDEA.
 * @Author: guoyw
 * @Date: 2023-01-30 19:39
 * @Description：
 * @Version: 1.0
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class GeoMetaModel implements Serializable {
    private static final long serialVersionUID = -3486659630857565368L;
    private String address;
    /**
     * aoi 名称
     */
    private String aoiName;
    // POI-R
    private AddressPoiModel sendAddressPoi;
    // Segment
    private AddressSegmentModel sendAddressSegment;
    // Aoi_10
    private AddressDzkAoiModel sendAddressAoi_10;
    // Aoi_50
    private AddressDzkAoiModel sendAddressAoi_50;

}
