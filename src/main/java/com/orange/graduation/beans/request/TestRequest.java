package com.orange.graduation.beans.request;

import lombok.Data;

/**
 * @author orange.zhang
 * @date 2022/11/18 15:20
 */
@Data
public class TestRequest {

    private Long id;
    private String name;
    private Long page;
    private Long pageSize;
}
