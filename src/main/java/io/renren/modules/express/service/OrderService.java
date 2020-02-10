package io.renren.modules.express.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.entity.OrderCharLine;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean saveOrder(OrderEntity orderEntity);

    boolean WeChatSaveOrder(OrderEntity orderEntity, UserEntity userEntity);

    List<OrderEntity> queryListOrder(Long userId);

    List<OrderEntity> queryListGetOrder(Long userId);

    OrderCharLine queryPageEcharsLine();//折线统计图

    boolean rukuOrder(String transportNo,UserEntity userEntity);
    //取件
    boolean qujian(String transportNo,UserEntity userEntity);
}

