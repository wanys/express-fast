package io.renren.modules.express.dao;

import io.renren.modules.express.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.express.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 快递员评价表
 * 
 * @author wys
 * @email sunlightcs@gmail.com
 * @date 2019-09-15 15:35:37
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

    List<CommentEntity> queryListComment();
}
