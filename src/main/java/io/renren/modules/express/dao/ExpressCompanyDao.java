package io.renren.modules.express.dao;

import io.renren.modules.express.entity.ExpressCompanyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 快递公司表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Mapper
public interface ExpressCompanyDao extends BaseMapper<ExpressCompanyEntity> {
	
}
