package com.orange.graduation.web.Controller;

import com.github.pagehelper.PageInfo;
import com.orange.graduation.annotation.JwtValidate;
import com.orange.graduation.beans.model.dto.User;
import com.orange.graduation.beans.request.HeartRequest;
import com.orange.graduation.annotation.BodyParams;
import com.orange.graduation.annotation.QueryParams;
import com.orange.graduation.beans.response.RespResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author orange.zhang
 * @date 2022/11/17 19:40
 */


public interface WebOperation {

    @ApiOperation(value = "HeartGetRequest")
    @GetMapping("/hello")
    RespResult<String> hello(@QueryParams HeartRequest heartRequest);


    @ApiOperation(value = "HeartPostRequest")
    @PostMapping("/hello")
    RespResult<String> helloPost(@BodyParams HeartRequest heartRequest);



    @ApiOperation(value = "findAll")
    @GetMapping("/findAll")
    RespResult<PageInfo<User>> findAll(@RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer pageSize);

}
