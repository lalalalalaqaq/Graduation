package com.orange.graduation.utils;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author orange.zhang
 * @date 2022/12/5 22:17
 */
public class JwtTokenUtilTest {

    @Test
    public void test() {
        JwtTokenUtil jt = new JwtTokenUtil();

        JwtTokenUtil.rsaPrivateSecretPath = "app.rsa";
        JwtTokenUtil.rsaPublicSecretPath  = "app.rsa.pub";

        Map<String, Object> data = new HashMap<>();
        data.put("uid", "1000");
        String token = jt.generateToken(data, "abc");
        System.out.println(token);
        Map<String, Object> data2 = jt.parseToken(token);
        String uid = data2.get("uid").toString();

        assertEquals("1000", uid);

        data.put("color", "red");
        token = jt.generateToken(data, "abc");

        data2 = jt.parseToken(token);
        String color = data2.get("color").toString();

        assertEquals("red", color);
    }

}