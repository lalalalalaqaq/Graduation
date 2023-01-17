package com.orange.graduation.web.Controller;

import com.github.pagehelper.PageInfo;
import com.orange.graduation.annotation.JwtValidate;
import com.orange.graduation.annotation.RateLimit;
import com.orange.graduation.beans.model.dto.User;
import com.orange.graduation.beans.request.HeartRequest;
import com.orange.graduation.beans.response.RespResult;
import com.orange.graduation.mapper.UserMapper;
import com.orange.graduation.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author orange.zhang
 * @date 2022/11/17 19:40
 */

@Api(tags = "Web服务")
@RestController
public class WebController implements WebOperation{


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public WebController(UserRepository userRepository,
                         UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @RateLimit
    @Override
    public RespResult<String> hello(HeartRequest heartRequest){
        String call = heartRequest.getContext() != null ? ("Hello " + heartRequest.getContext()) : ("Hello world");
        return RespResult.success(call);
    }

    @Override
    public RespResult<String> helloPost(HeartRequest heartRequest) {

        String call = heartRequest.getContext() != null ? ("Hello " + heartRequest.getContext()) : ("Hello world");
        return RespResult.success(call);
    }

    @JwtValidate
    @Override
    public RespResult<PageInfo<User>> findAll(Integer page, Integer pageSize) {
        return RespResult.success(userRepository.findAll(page, pageSize));
    }

    @Override
    public RespResult<List<User>> findAll1(Integer page, Integer pageSize) {
        List<User> all = userMapper.findAll();
        return RespResult.success(all);
    }

}
