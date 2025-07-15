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
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressPoiModel implements Serializable {

    private Double longitude;

    private Double latitude;

    private Double confidence;

    private String source;

    private String address;

    private String poi;

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
        setPoi();
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
        setPoi();
    }

    public void setPoi() {
        if (this.longitude != null && this.latitude != null) {
            this.poi = String.join(",", this.latitude.toString(), this.longitude.toString());
        }
    }
}
