package top.devildyw.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import top.devildyw.common.domain.user.UserRole;
import top.devildyw.core.mapper.RoleMapper;
import top.devildyw.core.mapper.UserRoleMapper;
import top.devildyw.core.service.RoleService;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-12-15-15:22
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserRoleMapper userRoleMapper;
    @Override
    public void addRoleForUserByUserId(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleMapper.insert(userRole);
    }
}
