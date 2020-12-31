package com.aurora.common.service;

import com.alibaba.fastjson.JSON;
import com.aurora.common.model.Global;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jwt token 工具类
 * @author PHQ
 * @create 2020-05-02 20:42
 **/
@Component
@Service
public class JwtService {

    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";
    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";
    //用来存token 后续换redis
    private Map<String, String> tokenMap = new ConcurrentHashMap<>(32);

    //@Value("${jwt.secret}")
    private String secret = Global.JWT_SECRET;

   // @Value("${jwt.expiration}")
    private Long access_token_expiration= Global.JWT_EXPIRATION;

   // @Value("${jwt.expiration}")
    private Long refresh_token_expiration = Global.JWT_EXPIRATION;

    //签名算法hs256
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**

    /**
     * 获取用户主键id
     * @param token
     * @return
     */
    public long getUserIdFromToken(String token) {
        long userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = Long.parseLong(String.valueOf(claims.get(CLAIM_KEY_USER_ID)));
        } catch (Exception e) {
            userId = 0;
        }
        return userId;
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }



    /**
     *  从token中获取Claims 载荷(Payload)
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    /**
     * 组装用户及jwt相关信息 生成token
     * @param subject
     * @param claims
     * @return
     */
    public String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, access_token_expiration);
    }

    /**
     *  生成token
     * @param subject   用户信息 名称
     * @param claims     载荷
     * @param expiration  过期时间
     * @return
     */
    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)   //创建payload的私有声明
                .setSubject(subject)  //jwt主体 可以理解为与用户唯一标识
                .setId(UUID.randomUUID().toString()) //token id
                .setIssuedAt(new Date()) //jwt的签发时间
                //.setExpiration(generateExpirationDate(expiration)) //过期时间
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret) //jwt使用的算法和秘钥
                .compact();
    }

}
