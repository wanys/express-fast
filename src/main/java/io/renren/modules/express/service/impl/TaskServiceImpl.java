package io.renren.modules.express.service.impl;

import cn.hutool.core.date.DateTime;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.service.OrderService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.express.dao.TaskDao;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;
import org.springframework.transaction.annotation.Transactional;


@Service("taskService")
public class TaskServiceImpl extends ServiceImpl<TaskDao, TaskEntity> implements TaskService {

    @Autowired
    private OrderService orderService;
    @Override
    public PageUtils queryPageWeChat(Map<String, Object> params) {

        String taskId = (String)params.get("taskId");
        String taskReceiverId = (String)params.get("taskReceiverId");
        String orderId = (String)params.get("orderId");
        String taskStatus = (String)params.get("taskStatus");
        String taskType = (String)params.get("taskType");
        String transportNo = (String)params.get("transportNo");
        String phoneNum = (String)params.get("phoneNum");
        String province = (String)params.get("province");
        String city = (String)params.get("city");
        String area = (String)params.get("area");
        String detaileAddr = (String)params.get("detaileAddr");
        String allocationBy = (String)params.get("allocationBy");
        String allocationTime = (String)params.get("allocationTime");
        String createBy = (String)params.get("createBy");
        String createTime = (String)params.get("createTime");
        String modifyBy = (String)params.get("modifyBy");
        String modifyTime = (String)params.get("modifyTime");

        IPage<TaskEntity> page = this.page(
                new Query<TaskEntity>().getPage(params),
                new QueryWrapper<TaskEntity>()
                        .eq(StringUtils.isNotBlank(taskId),"task_id", params.get("taskId"))
                        .eq(StringUtils.isNotBlank(taskReceiverId),"task_receiver_id", params.get("taskReceiverId"))
                        .eq(StringUtils.isNotBlank(orderId),"order_id", params.get("orderId"))
                        .eq(StringUtils.isNotBlank(transportNo),"transport_no", params.get("transportNo"))
                        .eq(StringUtils.isNotBlank(phoneNum),"phone_num", params.get("phoneNum"))
                        .eq(StringUtils.isNotBlank(province),"province", params.get("province"))
                        .eq(StringUtils.isNotBlank(city),"city", params.get("city"))
                        .eq(StringUtils.isNotBlank(area),"area", params.get("area"))
                        .eq(StringUtils.isNotBlank(detaileAddr),"detaile_addr", params.get("detaileAddr"))
                        .eq(StringUtils.isNotBlank(taskStatus),"task_status", params.get("taskStatus"))
                        .eq(StringUtils.isNotBlank(allocationBy),"allocation_by", params.get("allocationBy"))
                        .eq(StringUtils.isNotBlank(allocationTime),"allocation_time", params.get("allocationTime"))
                        .eq(StringUtils.isNotBlank(createBy),"create_by", params.get("createBy"))
                        .eq(StringUtils.isNotBlank(createTime),"create_time", params.get("createTime"))
                        .eq(StringUtils.isNotBlank(modifyBy),"modify_by", params.get("modifyBy"))
                        .eq(StringUtils.isNotBlank(modifyTime),"modify_time", params.get("modifyTime"))
                        .eq(StringUtils.isNotBlank(taskType),"task_type",params.get("taskType"))
                        .orderByAsc("allocation_time")
                        .orderByAsc("create_time")
        );

        return new PageUtils(page);
    }

    @Override
    public int allocationBatch(String[] taskIds,String taskReceiverId) {

        SysUserEntity sysUserEntity =(SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> map = new HashMap<>(4);
        map.put("list", taskIds);
        map.put("taskStatus", "20");
        map.put("taskReceiverId", taskReceiverId);
        map.put("userId", sysUserEntity.getUserId());
        return baseMapper.updateBatch(map);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String taskId = (String)params.get("taskId");
        String taskReceiverId = (String)params.get("taskReceiverId");
        String orderId = (String)params.get("orderId");
        String taskStatus = (String)params.get("taskStatus");
        String taskType = (String)params.get("taskType");
        String transportNo = (String)params.get("transportNo");
        String phoneNum = (String)params.get("phoneNum");
        String province = (String)params.get("province");
        String city = (String)params.get("city");
        String area = (String)params.get("area");
        String detaileAddr = (String)params.get("detaileAddr");
        String allocationBy = (String)params.get("allocationBy");
        String allocationTime = (String)params.get("allocationTime");
        String createBy = (String)params.get("createBy");
        String createTime = (String)params.get("createTime");
        String modifyBy = (String)params.get("modifyBy");
        String modifyTime = (String)params.get("modifyTime");

        IPage<TaskEntity> page = this.page(
                new Query<TaskEntity>().getPage(params),
                new QueryWrapper<TaskEntity>()
                .eq(StringUtils.isNotBlank(taskId),"task_id", params.get("taskId"))
                .eq(StringUtils.isNotBlank(taskReceiverId),"task_receiver_id", params.get("taskReceiverId"))
                .eq(StringUtils.isNotBlank(orderId),"order_id", params.get("orderId"))
                .eq(StringUtils.isNotBlank(transportNo),"transport_no", params.get("transportNo"))
                .eq(StringUtils.isNotBlank(phoneNum),"phone_num", params.get("phoneNum"))
                .eq(StringUtils.isNotBlank(province),"province", params.get("province"))
                .eq(StringUtils.isNotBlank(city),"city", params.get("city"))
                .eq(StringUtils.isNotBlank(area),"area", params.get("area"))
                .eq(StringUtils.isNotBlank(detaileAddr),"detaile_addr", params.get("detaileAddr"))
                .eq(StringUtils.isNotBlank(taskStatus),"task_status", params.get("taskStatus"))
                .eq(StringUtils.isNotBlank(allocationBy),"allocation_by", params.get("allocationBy"))
                .eq(StringUtils.isNotBlank(allocationTime),"allocation_time", params.get("allocationTime"))
                .eq(StringUtils.isNotBlank(createBy),"create_by", params.get("createBy"))
                .eq(StringUtils.isNotBlank(createTime),"create_time", params.get("createTime"))
                .eq(StringUtils.isNotBlank(modifyBy),"modify_by", params.get("modifyBy"))
                .eq(StringUtils.isNotBlank(modifyTime),"modify_time", params.get("modifyTime"))
                .eq(StringUtils.isNotBlank(taskType),"task_type",params.get("taskType"))
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public boolean WeChatBindWayBill(TaskEntity taskEntity,UserEntity userEntity) {

        //修改任务状态，绑定运单号
        taskEntity.setTaskId(taskEntity.getTaskId());
        taskEntity.setTaskStatus("30");
        taskEntity.setTransportNo(taskEntity.getTransportNo());
        taskEntity.setModifyBy(userEntity.getUserId().toString());
        taskEntity.setModifyTime(new Date());
        this.saveOrUpdate(taskEntity);

        //查询出刚修改的任务，为了拿到对应的订单号
        TaskEntity newTaskEntity= this.getById(taskEntity.getTaskId());

        //修改订单状态30，变为揽件完成，并绑定运单号
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderId(newTaskEntity.getOrderId());
        orderEntity.setTransportNo(newTaskEntity.getTransportNo());
        orderEntity.setOrderStatus("30");
        orderEntity.setModifyBy(userEntity.getUserId().toString());
        orderEntity.setModifyTime(new Date());

        orderService.saveOrUpdate(orderEntity);

        return true;
    }

}