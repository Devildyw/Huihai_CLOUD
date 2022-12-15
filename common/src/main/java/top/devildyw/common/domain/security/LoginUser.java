package top.devildyw.common.domain.security;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import top.devildyw.common.domain.user.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Devil
 * @since 2022-12-13-22:52
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;


    @JSONField(serialize = false)
    public String getPassword() {
        return user.getPassword();
    }


    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    public boolean isEnabled() {
        return user.getAvailable()==1;
    }

    public LoginUser(User user) {
        this.user = user;
    }
}
