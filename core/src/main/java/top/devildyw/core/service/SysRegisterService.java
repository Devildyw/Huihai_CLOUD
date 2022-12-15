package top.devildyw.core.service;

import org.springframework.stereotype.Service;
import top.devildyw.common.domain.dto.RegisterDTO;
import top.devildyw.common.domain.user.User;
import top.devildyw.common.result.RestResult;
import top.devildyw.common.util.HashUtils;
import top.devildyw.common.util.PasswordUtil;
import top.devildyw.core.mapper.UserMapper;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-12-14-19:40
 */
@Service
public class SysRegisterService {

    @Resource
    UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private StorageService storageService;
    public RestResult register(RegisterDTO registerDTO) {
        String password = registerDTO.getPassword();

        String salt = PasswordUtil.getSaltValue();
        password = HashUtils.hashHex(password,salt);

        User user = new User(registerDTO.getTelephone(),password,salt);
        userMapper.insert(user);
        roleService.addRoleForUserByUserId(user.getUserId(),2L);
        storageService.init(user.getUserId());
        return RestResult.success();
    }
}
