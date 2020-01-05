package io.renren.modules.express.service.impl;

import cn.hutool.core.date.DateTime;
import com.beust.jcommander.internal.Lists;
import io.renren.common.utils.*;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.OrderCharLine;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.express.dao.OrderDao;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.service.OrderService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.xml.crypto.Data;

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

    @Override
    public List<OrderEntity> queryListOrder(Long userId) {

        List<OrderEntity> listOrder =  baseMapper.queryListOrder(userId);

        return listOrder;
    }

    @Override
    public OrderCharLine queryPageEcharsLine() {

        // 月份分组
        SimpleDateFormat simpleFormat=new SimpleDateFormat("yyMM");
        Calendar NewCal = Calendar.getInstance();// 获取当前的系统时间
        Map<String,Integer> orderMap=new HashMap<>();

        for (int i = 0; i < 12; i++) {

            int year = NewCal.get(Calendar.YEAR)%2000;       //获取年
            int month = NewCal.get(Calendar.MONTH) + 1; //获取月份，0表示1月份
            NewCal.add(Calendar.MONTH, -1);     //每次往前推进一个月

            String tempMonth;

            if(month<10){

                tempMonth="0"+month;

            }else {

                tempMonth=Integer.toString(month) ;
            }
            orderMap.put((year+tempMonth),0);
        }

        // Map排序
        List<Map.Entry<String,Integer>> lstEntry=new ArrayList<>(orderMap.entrySet());
        Collections.sort(lstEntry,((o1, o2) -> {
            return o1.getKey().compareTo(o2.getKey());
        }));

        LinkedHashMap<String,Integer> linkedHashMap=new LinkedHashMap<>();
        lstEntry.forEach(o->{
            linkedHashMap.put(o.getKey(),o.getValue());
        });

        // 捞取数据库数据
        QueryWrapper<OrderEntity> queryWrapper= new QueryWrapper<>();

        queryWrapper.eq("order_status", "30");

        List<OrderEntity> entityList= baseMapper.selectList(queryWrapper);

        // 数据库分组
        for (OrderEntity orderEntity:entityList ) {

            for (String month:linkedHashMap.keySet()) {

                if(month.equals(simpleFormat.format(orderEntity.getCreateTime()))){

                    linkedHashMap.put(month,linkedHashMap.get(month)+1);
                }
            }
        }

        // 数据组装
        OrderCharLine orderCharLine =new OrderCharLine();
        orderCharLine.setMonth(linkedHashMap.keySet().toArray(new String[linkedHashMap.size()]));
        orderCharLine.setOrderCount(linkedHashMap.values().toArray(new Integer[linkedHashMap.size()]));

        return orderCharLine;
    }

    public static void main(String[] args) {


    }
}