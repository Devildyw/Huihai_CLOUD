package top.devildyw.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import top.devildyw.common.domain.user.User;
import top.devildyw.core.mapper.UserMapper;
import top.devildyw.core.service.UserService;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-12-14-22:09
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername,username);
        return userMapper.selectOne(qw);
    }

    @Override
    public User findUserByTelephone(String telephone) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getTelephone,telephone);
        return userMapper.selectOne(qw);
    }
}
