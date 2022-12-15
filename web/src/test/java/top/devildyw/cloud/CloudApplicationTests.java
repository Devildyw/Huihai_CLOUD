package top.devildyw.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.devildyw.core.mapper.PermissionMapper;
import top.devildyw.core.mapper.RoleMapper;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CloudApplicationTests {

    @Resource
    RoleMapper roleMapper;
    @Resource
    PermissionMapper permissionMapper;

    @Resource
    RedisTemplate<String,String> redisTemplate;
    @Test
    void contextLoads() {
//        redisTemplate.opsForValue().set("key","value",100, TimeUnit.MINUTES);
        System.out.println(redisTemplate.opsForValue().get("key"));
    }

}
