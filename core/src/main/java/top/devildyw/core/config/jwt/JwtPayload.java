package top.devildyw.core.config.jwt;

import lombok.Data;

/**
 * @author Devil
 * @since 2022-12-14-15:25
 */
@Data
public class JwtPayload {
    private RegisteredClaims registeredClaims;
}
