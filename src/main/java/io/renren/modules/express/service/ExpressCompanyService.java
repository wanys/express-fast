package io.renren.modules.express.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.express.entity.ExpressCompanyEntity;

import java.util.Map;

/**
 * 快递公司表
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
public interface ExpressCompanyService extends IService<ExpressCompanyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

