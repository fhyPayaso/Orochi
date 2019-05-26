package com.fhypayaso.orochi.service;


import com.fhypayaso.orochi.bean.User;
import com.fhypayaso.orochi.config.ConfigKey;
import com.fhypayaso.orochi.model.exception.TokenExceotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    // token过期时间一个小时
    private static final long EXPIRATION_TIME = 15 * 24 * 60 * 60 * 1000;

    // 自定义的秘钥
    private static final String SECREET_KET = "aafje9890312hkaf";

    @Autowired
    RedisService redisService;


    /**
     * 生成用户token
     *
     * @param user 用户
     * @return 加密后的token字符串
     */
    public String createToken(User user) {
        // 生成秘钥，用于签名
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECREET_KET);
        Key signingKey = new SecretKeySpec(secretBytes, algorithm.getJcaName());
        long curTimeStamp = System.currentTimeMillis();
        // 生成token
        return Jwts.builder()
                .claim(ConfigKey.KEY_UID, user.getId())
                .setIssuedAt(new Date(curTimeStamp)) //生成时间
                .setExpiration(new Date(curTimeStamp + EXPIRATION_TIME)) // 过期时间
                .signWith(algorithm, signingKey) // 签名
                .compact();
    }

    /**
     * 解析token
     *
     * @param token 加密后的token
     * @return Claims
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECREET_KET))
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 检查token是否过期, 所有涉及权限的接口都应检查
     *
     * @param token 客户端存放的token
     * @return token所对应的用户
     * @throws
     */
    public int checkToken(String token) throws Exception {
        try {

            if (token == null) {
                throw new TokenExceotion("token校验失败");
            }
            Claims claims = parseToken(token);
            int userId = (int) claims.get(ConfigKey.KEY_UID);
            String oldToken = (String) redisService.getData(ConfigKey.KEY_UID + userId);
            if (!token.equals(oldToken)) {
                throw new TokenExceotion("token无效");
            }
            return userId;
        } catch (ExpiredJwtException e) {
            throw new TokenExceotion("token已过期");
        } catch (InvalidClaimException e) {
            throw new TokenExceotion("token无效");
        }
    }
}
