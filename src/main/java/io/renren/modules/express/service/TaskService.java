package io.renren.modules.express.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itextpdf.text.DocumentException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.TaskCharBar;
import io.renren.modules.express.entity.OrderCharLine;
import io.renren.modules.express.entity.TaskEntity;

import java.io.IOException;
import java.util.Map;

/**
 * 任务表
 *
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
public interface TaskService extends IService<TaskEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageWeChat(Map<String, Object> params);

    /**
     * 批量更新定时任务状态
     */
    int allocationBatch(String [] taskIds,String taskReceiverId);

    boolean WeChatBindWayBill(TaskEntity taskEntity,UserEntity userEntity);

    String PrintQRCode() throws IOException, DocumentException;

    TaskCharBar queryPageEcharsBar();//柱状图
}

