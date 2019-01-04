package com.moodshop.pojo;

public class TbCity {
    private Integer cityId;

    private String cityName;

    private Integer provenceId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getProvenceId() {
        return provenceId;
    }

    public void setProvenceId(Integer provenceId) {
        this.provenceId = provenceId;
    }
}