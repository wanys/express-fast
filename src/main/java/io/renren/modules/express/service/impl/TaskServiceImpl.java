package io.renren.modules.express.service.impl;

import com.google.zxing.WriterException;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import io.renren.common.utils.*;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.entity.TaskCharBar;
import io.renren.modules.express.entity.OrderCharLine;
import io.renren.modules.express.service.OrderService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.express.dao.TaskDao;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;
import org.springframework.transaction.annotation.Transactional;


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

       // String content= String.valueOf(idWorker.nextId());
        String content= "内部信息无权访问";
        String targetPath = "src/main/resources/static/template/setWatermark.pdf";

        // 设置中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 9);

        //页面大小
        Rectangle rect = new Rectangle(PageSize.A8);
        //页面背景色
        rect.setBackgroundColor(BaseColor.WHITE);
        //Step 1—Create a Document.
        Document document = new Document(rect);

        //Document 文件写出到硬盘
        FileOutputStream out=new FileOutputStream(targetPath);

        try {
            //生成 二维码
            int[]  size=new int[]{430,430};
            BufferedImage bufferedImage=new QRCodeFactory().CreatQrImage1(content, "jpg", "", "",size);

            PdfWriter.getInstance(document,out);
            //打开 Document 文件
            document.open();

            // 图片设置
            Image image= getImage(bufferedImage,99);
            image.setAlignment(Image.LEFT | Image.TEXTWRAP);
            image.setBorder(Image.BOX);
            image.setBorderWidth(5);
            image.setBorderColor(BaseColor.WHITE);
            image.scaleToFit(148, 148);
            image.setAbsolutePosition(1, 60);
            image.setRotationDegrees(0);//旋转
            // Document 文件插入图片
            document.add(image);

            //字体一为空，用来撑开图片的位置
            Paragraph paragraph1 = new Paragraph();
            //居中显示
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph1.add("");

            //字体二为我们要显示的内容
            Paragraph paragraph2 = new Paragraph();
            //居中显示
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            paragraph2.setFont(font);
           // paragraph2.add("快递单后四位:"+content.substring(content.length()-4));
            paragraph2.add("快递单后四位:"+"xxxx");
            //段落2与段落1的间距加大100个单位
            paragraph2.setSpacingBefore(120);

            document.add(paragraph1);
            document.add(paragraph2);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            out.close();
        }
        return targetPath;
    }

    @Override
    public TaskCharBar queryPageEcharsBar() {

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

        LinkedHashMap<String,Integer> collectHashMap=new LinkedHashMap<>();
        lstEntry.forEach(o->{
            collectHashMap.put(o.getKey(),o.getValue());
        });

        LinkedHashMap<String,Integer> sendHashMap=new LinkedHashMap<>();
        lstEntry.forEach(o->{
            sendHashMap.put(o.getKey(),o.getValue());
        });

        QueryWrapper<TaskEntity> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("task_status", "30");
        List<TaskEntity> entityList= baseMapper.selectList(queryWrapper);

        // 数据库分组
        for (TaskEntity taskEntity:entityList ) {

            if(taskEntity.getTaskType().equals("collect")){

                for (String month:collectHashMap.keySet()) {

                    if(month.equals(simpleFormat.format(taskEntity.getCreateTime()))){

                        collectHashMap.put(month,collectHashMap.get(month)+1);
                    }
                }
            }

            if(taskEntity.getTaskType().equals("send")){
                for (String month:sendHashMap.keySet()) {

                    if(month.equals(simpleFormat.format(taskEntity.getCreateTime()))){

                        sendHashMap.put(month,sendHashMap.get(month)+1);
                    }
                }
            }
        }


        TaskCharBar taskCharBar=new TaskCharBar();
        taskCharBar.setCollect(collectHashMap.values().toArray(new Integer[collectHashMap.size()]));
        taskCharBar.setSend(sendHashMap.values().toArray(new Integer[sendHashMap.size()]));
        taskCharBar.setMonth(collectHashMap.keySet().toArray(new String[collectHashMap.size()]));

        return taskCharBar;
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

    //废弃 代码
    public String PrintQRCode1() throws IOException, DocumentException {

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

        // 模板文件 绝对路径
        //String templatePath = "E:\\Git\\express-fast\\src\\main\\resources\\static\\template\\QRCode.pdf";
        // 模板文件 相对路径
        //String templatePath = "src/main/resources/static/template/QRCode.pdf";
        // 模板文件 ClassPathResource类读取
        ClassPathResource templatePath=new ClassPathResource("static/template/QRCode.pdf");
        // 生成的文件路径
        String newFileName="QRCode"+RandomUtil.getRandomFileName();

        //String targetPath = "E:\\Git\\express-fast\\src\\main\\resources\\static\\template\\"+newFileName+".pdf";
        String targetPath = "src/main/resources/static/template/"+newFileName+".pdf";
        // 读取模板文件
        //InputStream input = new FileInputStream(new File(templatePath));
        InputStream input = templatePath.getInputStream();

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

        //stamper.setFormFlattening(true);
        stamper.close();
        reader.close();

        return targetPath;
    }
    // 废弃


}