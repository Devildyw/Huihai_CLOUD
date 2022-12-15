package top.devildyw.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import top.devildyw.common.domain.StorageBean;
import top.devildyw.common.domain.SysConfig;
import top.devildyw.core.mapper.StorageMapper;
import top.devildyw.core.mapper.SysConfigMapper;
import top.devildyw.core.service.StorageService;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-12-15-15:38
 */
@Service
public class StorageServiceImpl implements StorageService {

    private final static String DEFAULT_INIT_STORAGE_SIZE = "totalStorageSize";

    @Resource
    StorageMapper storageMapper;
    @Resource
    SysConfigMapper sysConfigMapper;
    @Override
    public void init(Long userId) {
        LambdaQueryWrapper<SysConfig> qw = new LambdaQueryWrapper<>();
        qw.eq(SysConfig::getSysParamKey,"totalStorageSize");
        SysConfig sysConfig = sysConfigMapper.selectOne(qw);

        StorageBean storageBean = new StorageBean(userId, Long.valueOf(sysConfig.getSysParamValue()));
        storageMapper.insert(storageBean);

    }
}
