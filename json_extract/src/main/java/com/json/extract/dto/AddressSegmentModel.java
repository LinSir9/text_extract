package com.json.extract.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by lusen on 2020/2/29.
 */
@Data
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class AddressSegmentModel implements Serializable {
    public String printMark;
    /**
     * 一段码
     */
    public String sortation;

    /**
     * 二段码
     */
    public String secondSortationCode;

    /**
     * 三段码
     */
    public String thirdSortationCode;

    /**
     * 四段码
     */
    public String fourSortationCode;

    /**
     * 驿站码
     */
    public String stageCode;

    /**
     * 预测派件网点编码
     */
    public String receiveBranchCode;

}
