package top.devildyw.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.devildyw.common.domain.user.Permission;

import java.util.List;

/**
 * @author Devil
 * @since 2022-12-14-18:32
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> selectPermissionsByUsername(String username,String telephone,String email);
}
