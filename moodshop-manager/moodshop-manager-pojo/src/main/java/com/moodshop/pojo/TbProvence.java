package com.moodshop.pojo;

public class TbProvence {
    private Integer provenceId;

    private String provenceName;

    public Integer getProvenceId() {
        return provenceId;
    }

    public void setProvenceId(Integer provenceId) {
        this.provenceId = provenceId;
    }

    public String getProvenceName() {
        return provenceName;
    }

    public void setProvenceName(String provenceName) {
        this.provenceName = provenceName == null ? null : provenceName.trim();
    }
}