package com.xiaoyao.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * Demo案例
 */
@Data
public class Demo implements Serializable {
    /**主键ID*/
    private Integer id;
    /**测试姓名*/
    private String name;

}
