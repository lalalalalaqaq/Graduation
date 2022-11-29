package com.orange.graduation.beans.model.dto;

import lombok.Data;

/**
 * @author orange.zhang
 * @date 2022/11/18 21:04
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String hobby;
}
