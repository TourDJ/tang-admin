/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yellowsneakers.core.secure;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.yellowsneakers.core.http.HttpHelper;
import org.yellowsneakers.core.tools.DateTimes;
import org.yellowsneakers.generic.utils.DateUtils;
import org.yellowsneakers.generic.utils.Func;
import org.yellowsneakers.generic.utils.date.DateField;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author tang
 * @since  1.0
 */
public class TokenSecurer {
	
	public final static String header = "Authorization";
	
    public final static String bearer = "bearer";
    
    public final static String account = "account";
    
    public final static String userId = "userId";
    
    public final static String roleId = "roleId";
    
    public final static String username = "username";
    
    public final static String roleName = "roleName";
    
    
    private static String base64Security = DatatypeConverter.printBase64Binary("RubberJF".getBytes());

    /**
     * 获取用户信息
     *
     * @return
     */
    public static RubberUser getUser() {
        return getUser(HttpHelper.getRequest());
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static RubberUser getUser(HttpServletRequest request) {
        Claims claims = getClaims(request);
        if (claims == null) {
            return new RubberUser();
        }
        Integer userId = Func.toInt(claims.get(TokenSecurer.userId));
        String roleId = Func.toStr(claims.get(TokenSecurer.roleId));
        String account = Func.toStr(claims.get(TokenSecurer.account));
        String roleName = Func.toStr(claims.get(TokenSecurer.roleName));
        RubberUser rubberUser = new RubberUser();
        rubberUser.setAccount(account);
        rubberUser.setUserId(userId);
        rubberUser.setRoleId(roleId);
        rubberUser.setRoleName(roleName);
        return rubberUser;
    }

    /**
     * 获取Claims
     *
     * @return
     */
    public static Claims getClaims(HttpServletRequest request) {
        String auth = request.getHeader(TokenSecurer.header);
        if ((auth != null) && (auth.length() > 7)) {
            String HeadStr = auth.substring(0, 6).toLowerCase();
            if (HeadStr.compareTo(TokenSecurer.bearer) == 0) {
                auth = auth.substring(7);
                return parseJWT(auth);
            }
        }
        return null;
    }

    /**
     * 获取请求头
     *
     * @return
     */
    public static String getHeader() {
        return getHeader(HttpHelper.getRequest());
    }

    /**
     * 获取请求头
     *
     * @param request
     * @return
     */
    public static String getHeader(HttpServletRequest request) {
        return request.getHeader(header);
    }

    /**
     * 解析jsonWebToken
     *
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 创建jwt
     *
     * @param user     用户
     * @param audience audience
     * @param issuer   issuer
     * @param isExpire isExpire
     * @return
     */
    public static String createJWT(Map<String, String> user, String audience, String issuer, boolean isExpire) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的类
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken")
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);

        //设置JWT参数
        for (String key : user.keySet()) {
            builder.claim(key, user.get(key));
        }

        //添加Token过期时间
        if (isExpire) {
            long expMillis = nowMillis + getExpire();
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    /**
     * 获取过期时间(次日凌晨3点)
     *
     * @return
     */
    public static long getExpire() {
        DateTimes dateTime = DateUtils.endOfDay(new Date());
        DateTimes offset = DateUtils.offset(dateTime, DateField.HOUR, 3);

        return offset.getTime() - System.currentTimeMillis();
    }
}
