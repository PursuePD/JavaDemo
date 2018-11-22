package com.example.springtest.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author:cuijialei
 * @Date: 2018/8/8
 * @Describe
 */
public class PeopleListModel {
    @ApiModelProperty(value = "人员列表")
    private List<People> peoples;

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }
}
