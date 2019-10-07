package io.renren.modules.express.service.impl;

import cn.hutool.core.date.DateTime;
import io.renren.common.utils.*;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.redis.SysConfigRedis;
import io.renren.modules.sys.service.SysUserRoleService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.express.dao.OrderDao;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdWorker idWorker;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public boolean saveOrder(OrderEntity orderEntity) {

        SysUserEntity sysUserEntity =(SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        orderEntity.setOrderId(Long.toString(idWorker.nextId()));
        orderEntity.setCreateTime(new DateTime());
        this.save(orderEntity);

        TaskEntity taskEntity=new TaskEntity();
        taskEntity.setTaskId(Long.toString(idWorker.nextId()));
        taskEntity.setCreateTime(new DateTime());
        taskEntity.setCreateBy(sysUserEntity.getUserId().toString());
        taskService.saveOrUpdate(taskEntity);

        return true;
    }

    @Override
    @Transactional
    public boolean WeChatSaveOrder(OrderEntity orderEntity,UserEntity userEntity) {

        orderEntity.setOrderId(Long.toString(idWorker.nextId()));
        orderEntity.setCreateTime(new DateTime());
        orderEntity.setCreateBy(userEntity.getUserId().toString());
        orderEntity.setOrderStatus("10");
        orderEntity.setUserId(userEntity.getUserId().toString());
        this.save(orderEntity);

        TaskEntity taskEntity=new TaskEntity();
        taskEntity.setTaskId(Long.toString(idWorker.nextId()));
        taskEntity.setOrderId(orderEntity.getOrderId());
        taskEntity.setTaskStatus("10");
        taskEntity.setPhoneNum(orderEntity.getSenderPhone());
        taskEntity.setProvince(orderEntity.getSenderProvince());
        taskEntity.setCity(orderEntity.getSenderCity());
        taskEntity.setArea(orderEntity.getSenderArea());
        taskEntity.setDetaileAddr(orderEntity.getSenderDetaileAddr());
        taskEntity.setCreateTime(new DateTime());
        taskEntity.setCreateBy(userEntity.getUserId().toString());
        taskService.saveOrUpdate(taskEntity);

        return true;
    }

}