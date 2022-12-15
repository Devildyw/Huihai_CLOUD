package top.devildyw.core.component;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.devildyw.common.domain.security.LoginUser;
import top.devildyw.common.util.cache.RedisCache;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author Devil
 * @since 2022-12-14-15:30
 */
@Component
@ConfigurationProperties(prefix = "token")
public class AuthComp {

    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private int expireTime;

    @Resource
    private RedisCache redisCache;

    public static final String LOGIN_USER_KEY = "login_user_key";

    final static String TOKEN_PREFIX = "Bearer ";

    final static String LOGIN_TOKEN_KEY = "login_tokens:";

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;


    /**
     * 获取用户身份信息
     * @return
     */
    public LoginUser getLoginUser(){
//        String token = getToken(request);
//        if (StringUtils.isNotEmpty(token)){
//            try {
//                Claims claims = parseToken(token);
//                //解析对应的权限以及用户信息
//                String userKey = (String)claims.get(LOGIN_USER_KEY);
//                LoginUser loginUser = redisCache.getCacheObject(userKey);
//                return loginUser;
//            }catch (Exception e){
//
//            }
//        }
        LoginUser loginUser = (LoginUser) StpUtil.getSession().get("loginUser");
        return loginUser;
    }

    /**
     * 设置用户身份信息
     * @param loginUser
     */
    public void setLoginUser(LoginUser loginUser){
        StpUtil.getSession().set("loginUser",loginUser);
    }

    public void delLoginUser(){
        StpUtil.getSession().delete("loginUser");
    }

    /**
     * 由密钥字符串生产加密key
     * @return
     */
    public SecretKey generalKey(){
        //本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(secret);

        //根据给定的字节数组使用AES对称加密算法构成一个密钥
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 获取请求token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token)&&token.startsWith(TOKEN_PREFIX)){
            token = token.replace(TOKEN_PREFIX,"");
        }
        return token;
    }

    /**
     * 创建令牌
     * @param loginUser 用户信息
     * @return token
     */
    public String createToken(LoginUser loginUser){
        String token = IdUtil.fastUUID();
//        setUserAgent(loginUser);
//        refreshToken(loginUser);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_USER_KEY,token);
        return createToken(claims);
    }

//    /**
//     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
//     * @param loginUser
//     */
//    public void verifyToken(LoginUser loginUser){
//        long expireTime = loginUser.getExpireTime();
//        long currentTime = System.currentTimeMillis();
//
//        if (expireTime - currentTime <= MILLIS_MINUTE_TEN){
//            refreshToken(loginUser);
//        }
//    }

//    /**
//     * 刷新令牌的有效期
//     * @param loginUser 登录信息
//     */
//    private void refreshToken(LoginUser loginUser) {
//        loginUser.setLoginTime(System.currentTimeMillis());
//        loginUser.setExpireTime(loginUser.getLoginTime()+expireTime*MILLIS_MINUTE);
//
//        //根据uuid将loginUser缓存
//        String userKey = getTokenKey(loginUser.getToken());
//        redisCache.setCacheObject(userKey,loginUser,expireTime,TimeUnit.MINUTES);
//    }


    /**
     * 从令牌中获取数据声明
     * @param token
     * @return
     */
    private Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(secret) //只能借助密钥才能解密
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 从数据声明生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(HashMap<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

//    private String getTokenKey(String uuid){
//        return LOGIN_TOKEN_KEY + uuid;
//    }
}
