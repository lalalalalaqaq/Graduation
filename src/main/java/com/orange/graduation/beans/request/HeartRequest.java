package com.orange.graduation.beans.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author orange.zhang
 * @date 2022/11/17 20:45
 * heart test
 */
@Data
@ApiModel("HeartRequest")
public class HeartRequest {

    @ApiModelProperty(name = "context",value = "context", example = "orange")
    String context;
}
