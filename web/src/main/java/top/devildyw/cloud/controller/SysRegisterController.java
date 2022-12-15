package top.devildyw.cloud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.devildyw.common.domain.dto.RegisterDTO;
import top.devildyw.common.result.RestResult;
import top.devildyw.core.service.RoleService;
import top.devildyw.core.service.SysRegisterService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Devil
 * @since 2022-12-14-22:50
 */
@RestController
public class SysRegisterController {

    @Resource
    private SysRegisterService registerService;

    @PostMapping("/register")
    public RestResult register(@Valid @RequestBody RegisterDTO registerDTO){
        return registerService.register(registerDTO);
    }
}
