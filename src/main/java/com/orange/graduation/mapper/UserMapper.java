package com.orange.graduation.mapper;

import com.orange.graduation.beans.model.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author orange.zhang
 * @date 2022/11/18 21:06
 */
@Mapper
public interface UserMapper {

    @Insert(" insert into user (username, password, hobby) VALUES (#{username},#{password},#{hobby})")
    int insert(User user);

    @Select(" select * from user ")
    List<User> findAll();
}
