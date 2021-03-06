package io.renren.modules.express.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.express.entity.SuserEntity;

import java.util.Map;

/**
 * 用户表
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
public interface SuserService extends IService<SuserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

