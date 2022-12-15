package top.devildyw.cloud.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.devildyw.common.domain.dto.LoginDTO;
import top.devildyw.common.result.RestResult;
import top.devildyw.core.service.SysLoginService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Devil
 * @since 2022-12-14-19:45
 */
@RestController
public class SysLoginController {

    @Resource
    private SysLoginService sysLoginService;

    @PostMapping("/login")
    public RestResult login(@Valid @RequestBody LoginDTO loginDTO){
        return sysLoginService.login(loginDTO);
    }

    @GetMapping("/info")
    public void getInfo(){
        System.out.println(StpUtil.getTokenInfo());
        System.out.println(StpUtil.getSession());
        System.out.println(StpUtil.isLogin());
        System.out.println(StpUtil.getTokenSession());
        System.out.println(StpUtil.getRoleList());
        System.out.println(StpUtil.getPermissionList());
    }
}
