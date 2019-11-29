package io.renren.modules.express.service.impl;

import cn.hutool.core.date.DateTime;
import com.google.zxing.WriterException;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import io.renren.common.utils.*;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.service.OrderService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.express.dao.TaskDao;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;


@Service("taskService")
public class TaskServiceImpl extends ServiceImpl<TaskDao, TaskEntity> implements TaskService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private IdWorker idWorker;

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

    @Override
    public String PrintQRCode() throws IOException, DocumentException {

        String content= String.valueOf(idWorker.nextId());

        int[]  size=new int[]{430,430};

        BufferedImage bufferedImage=null;
        try {
            bufferedImage=new QRCodeFactory().CreatQrImage1(content, "jpg", "", "",size);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        RandomUtil.getRandomFileName();

        // 模板文件路径
        String templatePath = "E:\\Git\\express-fast\\src\\main\\resources\\static\\template\\QRCode.pdf";
        // 生成的文件路径
        String newFileName="QRCode"+RandomUtil.getRandomFileName();

        String targetPath = "E:\\Git\\express-fast\\src\\main\\resources\\static\\template\\"+newFileName+".pdf";
        // 读取模板文件
        InputStream input = new FileInputStream(new File(templatePath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));

        Image image= null;
        try {
            image = getImage(bufferedImage,99);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AcroFields form = stamper.getAcroFields();
        //设置二维码下的文字
        form.setField("Title","快递单后四位："+content.substring(content.length()-4));
        //二维码填充
        AcroFields.FieldPosition fieldPosition = form.getFieldPositions("QRCode").get(0);

        float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();

        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(1);
        // 根据域的大小缩放图片
        image.scaleToFit(width, width);
        // 添加图片
        //左边距(居中处理)
        float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image.getWidth()) / 2;
        //条码位置
        image.setAbsolutePosition(fieldPosition.position.getLeft(), fieldPosition.position.getBottom());

        under.addImage(image);

        stamper.close();
        reader.close();

        return targetPath;
    }

    public static Image getImage(BufferedImage bufferedImage, float percent) throws Exception{
        Image itextImage=null;
        itextImage=Image.getInstance(bufferedImage,new Color(255,255,255));
        itextImage.setWidthPercentage(0.1f);
        itextImage.setSpacingAfter(0.1f);
        itextImage.scalePercent(percent);
        itextImage.setBorder(Rectangle.NO_BORDER);
        return itextImage;
    }
}