package io.renren.modules.express.service.impl;

import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.express.dao.TaskDao;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.TaskService;


@Service("taskService")
public class TaskServiceImpl extends ServiceImpl<TaskDao, TaskEntity> implements TaskService {

    @Override
    public PageUtils queryPageWeChat(Map<String, Object> params) {

        String userId = (String)params.get("userId");

        IPage<TaskEntity> page = this.page(
                new Query<TaskEntity>().getPage(params),
                new QueryWrapper<TaskEntity>()
                        .eq(StringUtils.isNotBlank(userId),"user_id", params.get("userId"))
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String userId = (String)params.get("userId");
        String taskType = (String)params.get("taskType");

        IPage<TaskEntity> page = this.page(
                new Query<TaskEntity>().getPage(params),
                new QueryWrapper<TaskEntity>()
                .eq(StringUtils.isNotBlank(userId),"user_id", params.get("userId"))
                .eq(StringUtils.isNotBlank(taskType),"task_type",params.get("taskType"))
        );

        return new PageUtils(page);
    }

}