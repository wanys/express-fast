package io.renren.modules.express.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.express.entity.AddressEntity;

import java.util.Map;

/**
 * 
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-12-26 20:27:45
 */
public interface AddressService extends IService<AddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPagepc(Map<String, Object> params);
}

