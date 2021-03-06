package io.renren.modules.express.dao;

import io.renren.modules.express.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

    List<OrderEntity> queryListOrder(@Param("userId")Long userId);
}
