package top.devildyw.core.service;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Service;
import top.devildyw.common.domain.dto.LoginDTO;
import top.devildyw.common.domain.security.LoginUser;
import top.devildyw.common.domain.user.User;
import top.devildyw.common.result.RestResult;
import top.devildyw.common.util.HashUtils;
import top.devildyw.core.component.AuthComp;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author Devil
 * @since 2022-12-14-19:40
 */
@Service
public class SysLoginService {

    @Resource
    private UserService userService;

    @Resource
    private AuthComp authComp;

    /**
     * 登录接口
     * @param loginDTO
     */
    public RestResult login(@Valid LoginDTO loginDTO) {
        //todo: 验证码比对
//        User user = userService.findUserByUsername(username);
        User user = userService.findUserByTelephone(loginDTO.getTelephone());
        String password = loginDTO.getPassword();
        //检查用户名有效性
        if (user==null){
            return RestResult.fail().message("用户名错误");
        }

        //密码比对
        if (!matches(user.getPassword(),user.getSalt(),password)){
            return RestResult.fail().message("密码错误");
        }

        //通过验证生成jwts
        LoginUser loginUser = new LoginUser(user);

        if (!loginUser.isEnabled()){
            return RestResult.fail().message("该账户已被禁用");
        }

        String token = authComp.createToken(loginUser);
        HashMap<String, String> map = new HashMap<>();

        StpUtil.login(user.getTelephone(),new SaLoginModel()
                .setToken(token));
        authComp.setLoginUser(loginUser);
        map.put(StpUtil.getTokenName(),token);
        return RestResult.success().data(map);

    }

    private boolean matches(String password, String salt, String source) {
        String digest = HashUtils.hashHex(source, salt);
        return Boolean.TRUE.equals(digest.equals(password));
    }
}
