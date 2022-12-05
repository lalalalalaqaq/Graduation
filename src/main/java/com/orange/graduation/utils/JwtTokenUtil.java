package com.orange.graduation.utils;

import io.jsonwebtoken.*;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author orange.zhang
 * @date 2022/12/5 20:24
 */
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final String JWT_HEAD_NAME = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    private static Long expiration = 10*365*24*3600L;

    private static String md5Key = "randomKey";

    public static String rsaPublicSecretPath = "app.rsa.pub";
    public static String rsaPrivateSecretPath = "app.rsa";

    private static String rsaPublicSecret = null;
    private static String rsaPrivateSecret = null;


    /**
     * 获取用户名从token中
     */
    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接收者
     */
    public static String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public static String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key从token中
     */
    public static String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, md5Key);
    }

    /**
     * 获取jwt的payload部分
     */
    public static Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getPubKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析token是否正确,不正确会报异常<br>
     */
    public static Map<String, Object> parseToken2(String token) throws JwtException {
        return Jwts.parser().setSigningKey(getPubKey()).parseClaimsJws(token).getBody();
    }

    /**
     * 解析token是否正确,不正确会报异常<br>
     */
    public static Map<String, Object> parseToken(String token) throws JwtException {
        if (jwtParser == null) {
            jwtParser = Jwts.parser().setSigningKey(getPubKey());
        }
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private static JwtParser jwtParser = null;

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public static Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * 生成token(通过用户名和签名时候用的随机数)
     */
    public static String generateToken(Map<String, Object> claims, String randomKey) {
        claims.put("iss", "ATOMINTL");
        claims.put(md5Key, randomKey);
        return doGenerateToken(claims);
    }

    /**
     * RsA 加密 生成token
     */
    private static String doGenerateToken(Map<String, Object> claims) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        // 生成签名密钥
        String secret = rsaPrivateSecret;
        if (secret == null || secret.length() < 1) {
            try {
                secret = readResourceKey(System.getProperty("user.dir") + rsaPrivateSecretPath);
            } catch (FileNotFoundException | MalformedURLException e) {
                secret = readClassResourceKey(rsaPrivateSecretPath);
            }
            if (secret != null) {
                rsaPrivateSecret = secret;
            }
        }

        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return Jwts.builder()
                    .setClaims(claims)
                    // .setSubject(subject)
                    .setIssuedAt(createdDate)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 读取资源文件
     *
     * @param fileName
     * @return
     * @throws MalformedURLException
     */
    public static String readResourceKey(String fileName) throws FileNotFoundException, MalformedURLException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(fileName);
        }
        String key = null;
        try (Stream<String> streamLines = Files.lines(Paths.get(fileName), Charset.defaultCharset())) {
            List<String> collect = streamLines.collect(Collectors.toList());
            key = String.join("", collect.subList(1, collect.size() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String readClassResourceKey(String fileName) {
        String key = null;
        try {
            List<String> lines = new ArrayList<>();
            String row = null;
            try (InputStream in = JwtTokenUtil.class.getClassLoader().getResourceAsStream(fileName);
                 BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                while ((row = br.readLine()) != null) {
                    lines.add(row);
                }
            }

            if (lines.size() > 2) {
                lines = lines.subList(1, lines.size() - 1);
                key = lines.stream().collect(Collectors.joining());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    private static PublicKey getPubKey() {
        String secret = rsaPublicSecret;
        if (secret == null || secret.length() < 1) {
            try {
                secret = readResourceKey(System.getProperty("user.dir") + rsaPublicSecretPath);
            } catch (FileNotFoundException | MalformedURLException e) {
                secret = readClassResourceKey(rsaPublicSecretPath);
            }
            if (secret != null) {
                rsaPublicSecret = secret;
            }
        }
        // 生成签名公钥
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secret);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


