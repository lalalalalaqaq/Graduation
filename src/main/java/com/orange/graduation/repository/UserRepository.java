package com.orange.graduation.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orange.graduation.annotation.RedisCache;
import com.orange.graduation.beans.model.dto.User;
import com.orange.graduation.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author orange.zhang
 * @date 2022/11/18 21:09
 */
@Repository
public class UserRepository {

    private final UserMapper userMapper;


    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Integer insert(User user) {
        return userMapper.insert(user);
    }

//    @RedisCache(key = "user", seconds = -1)
    public PageInfo<User> findAll(Integer page, Integer pageSize) {
        page = (page == null || page <= 0) ? 1 : page;
        pageSize = (pageSize == null || page <= 0) ? 5 : pageSize;
        PageInfo userPageInfo = PageHelper.startPage(page, pageSize)
                .doSelectPageInfo(() -> userMapper.findAll());
        return userPageInfo;
    }

}
