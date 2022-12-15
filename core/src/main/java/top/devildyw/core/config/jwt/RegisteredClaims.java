package top.devildyw.core.config.jwt;

import lombok.Data;

/**
 * @author Devil
 * @since 2022-12-14-15:26
 */
@Data
public class RegisteredClaims {

    /**
     * 签发者
     */
    private String iss;

    /**
     * 过期时间
     */
    private String exp;

    /**
     *
     */
    private String sub;

    /**
     * 接收者
     */
    private String aud;
}
