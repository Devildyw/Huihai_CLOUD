package top.devildyw.core.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.devildyw.core.mapper.PermissionMapper;
import top.devildyw.core.mapper.RoleMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Devil
 * @since 2022-12-14-19:38
 */
@Component
@Slf4j
public class AuthenticationImpl implements StpInterface {
    @Resource
    RoleMapper roleMapper;

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String telephone = (String) loginId;
        List<String> permissions = permissionMapper.selectPermissionsByUsername(null,telephone,null);
        log.info("permissions:{}",permissions);
        return permissions;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String telephone = (String) loginId;
        List<String> roles = roleMapper.selectRolesByUsername(null,telephone,null);
        log.info("roles:{}",roles);
        return roles;
    }
}
