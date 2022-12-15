package top.devildyw.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.devildyw.common.domain.user.Role;

import java.util.List;

/**
 * @author Devil
 * @since 2022-12-14-18:34
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRolesByUsername(String username,String telephone,String email);

    void updateRoleForUser(Long userId, int roleId);


}
