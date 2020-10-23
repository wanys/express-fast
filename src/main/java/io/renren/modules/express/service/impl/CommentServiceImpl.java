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

import java.lang.reflect.Field;
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
        //commentEntity.setCommentId(idWorker.nextId());
        commentEntity.setCreateTime(new DateTime());
        commentEntity.setCreateBy(userEntity.getUserId().toString());
        commentEntity.setUserId(userEntity.getUserId().intValue());
        this.save(commentEntity);

        /*OrderEntity orderEntity=orderService.getById(commentEntity.getOrderId());
        orderEntity.setComment(true);
        orderEntity.setModifyTime(new DateTime());
        orderEntity.setModifyBy(userEntity.getUserId().toString());
        orderService.saveOrUpdate(orderEntity);*/

        return true;
    }

    @Override
    public List<CommentEntity> queryListComment(Long userId) {
        QueryWrapper<CommentEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<CommentEntity> commentEntityList=baseMapper.selectList(queryWrapper);
        return commentEntityList;
    }

    @Override
    public List<CommentEntity> queryListComment() {

        int[] serviceArr=new int[]{0,0,0,0,0};
        int[] professionalArr=new int[]{0,0,0,0,0};
        int[] spendingArr=new int[]{0,0,0,0,0};

        List<CommentEntity> listComment1 =  baseMapper.queryListComment();

        for (CommentEntity commentEntity:listComment1) {
            //1、根据字段区分
            //2、取出每个字段的名称
            int serviceScore=commentEntity.getServiceAttitude();

            int spendScore= commentEntity.getSpending();

            int personalScore= commentEntity.getProfessional();



            Field[] fields=commentEntity.getClass().getDeclaredFields();
            for (Field field: fields) {
                String fieldName= field.getName();
                switch (fieldName)
                {
                    case "serviceAttitude":
                        switch (serviceScore)
                        {
                            case 1:
                                serviceArr[0]+=1;
                                break;
                            case 2:
                                serviceArr[1]+=1;
                                break;
                            case 3:
                                serviceArr[2]+=1;
                                break;
                            case 4:
                                serviceArr[3]+=1;
                                break;
                            case 5:
                                serviceArr[4]+=1;
                                break;
                            default:
                                break;
                        }
                        break;
                    case "professional":
                        switch (personalScore)
                        {
                            case 1:
                                professionalArr[0]+=1;
                                break;
                            case 2:
                                professionalArr[1]+=1;
                                break;
                            case 3:
                                professionalArr[2]+=1;
                                break;
                            case 4:
                                professionalArr[3]+=1;
                                break;
                            case 5:
                                professionalArr[4]+=1;
                                break;
                            default:
                                break;
                        }
                        break;
                    case "spending":
                        switch (spendScore)
                        {
                            case 1:
                                spendingArr[0]+=1;
                                break;
                            case 2:
                                spendingArr[1]+=1;
                                break;
                            case 3:
                                spendingArr[2]+=1;
                                break;
                            case 4:
                                spendingArr[3]+=1;
                                break;
                            case 5:
                                spendingArr[4]+=1;
                                break;
                            default:
                                    break;
                        }
                        break;

                    default:
                            break;

                }
            }
        }

        int[][] total=new int[3][serviceArr.length];
        for (int i = 0; i < serviceArr.length; i++) {

            total[0][i]=serviceArr[i];
            total[1][i]=professionalArr[i];
            total[2][i]=spendingArr[i];
        }

        System.out.print("         ");

        for (int j = 0; j < 5; j++) {
            System.out.print("L"+(j+1)+"  ");
        }
        System.out.println("");
        for (int i = 0; i < 3; i++) {
            switch (i)
            {
                case 0:
                    System.out.print("  服务态度:");
                    break;
                case 1:
                    System.out.print("   专业性:");
                    break;
                case 2:
                    System.out.print("费用合理性:");
                    break;
            }


            for (int j = 0; j < 5; j++) {
                System.out.print(total[i][j]+"   ");
            }
            System.out.println("");
        }
        // 捞取数据库数据
        /*QueryWrapper<CommentEntity> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("order_status",10);

        List<CommentEntity> listComment   = baseMapper.selectList(queryWrapper);

        for (CommentEntity orderEntity:listComment){
            listComment.add(orderEntity);
        }*/
        return listComment1;
    }

}