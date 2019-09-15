package io.renren.modules.express.dao;

import io.renren.modules.express.entity.SuserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Mapper
public interface SuserDao extends BaseMapper<SuserEntity> {
	
}
