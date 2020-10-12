package io.renren.modules.express.service.impl;

import io.renren.common.utils.IdWorker;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.TaskEntity;
import io.renren.modules.express.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.date.DateTime;
import io.renren.modules.express.entity.OrderEntity;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.express.dao.CommentDao;
import io.renren.modules.express.entity.CommentEntity;
import io.renren.modules.express.service.CommentService;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderService orderService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String content = (String)params.get("content");

        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new QueryWrapper<CommentEntity>()
                        .like(StringUtils.isNotBlank(content),"content", params.get("content"))
                        .orderByAsc("create_time")
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveComment(CommentEntity commentEntity, UserEntity userEntity) {
        //评论ID
        // commentEntity.setCommentId(Long.toString(idWorker.nextId()));
        commentEntity.setCreateTime(new DateTime());
        commentEntity.setCreateBy(userEntity.getUserId().toString());
        //commentEntity.setUserId(userEntity.getUserId().intValue());
        this.save(commentEntity);

        OrderEntity orderEntity=orderService.getById(commentEntity.getOrderId());
        orderEntity.setComment(true);
        orderEntity.setModifyTime(new DateTime());
        orderEntity.setModifyBy(userEntity.getUserId().toString());
        orderService.saveOrUpdate(orderEntity);

        return true;
    }

    @Override
    public List<CommentEntity> queryListComment(Long userId) {
        QueryWrapper<CommentEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<CommentEntity> commentEntityList=baseMapper.selectList(queryWrapper);
        return commentEntityList;
    }

}