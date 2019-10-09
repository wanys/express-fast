package io.renren.modules.express.dao;

import io.renren.modules.express.entity.TaskEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 任务表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Mapper
public interface TaskDao extends BaseMapper<TaskEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
