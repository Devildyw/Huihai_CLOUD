package top.devildyw.core.service;

import top.devildyw.common.domain.user.User;

/**
 * @author Devil
 * @since 2022-12-14-18:44
 */
public interface UserService {
    User findUserByUsername(String username);

    User findUserByTelephone(String telephone);
}
