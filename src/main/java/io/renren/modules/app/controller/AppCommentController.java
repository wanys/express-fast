package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.CommentEntity;
import io.renren.modules.express.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@Api("快递员评论接口")
public class AppCommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 保存评论
     */

    @Login
    @ApiOperation("保存评论")
    @PostMapping("comment/save")
    public R saveComm(@RequestBody CommentEntity commentEntity, @LoginUser UserEntity userEntity){
        commentService.saveComment(commentEntity,userEntity);

        return R.ok();
    }

    /**
     * 获取快递员评价
     */

    @Login
    @ApiOperation("获取快递员星级")
    @GetMapping("comment/getComment")
    public R getComment(@LoginUser UserEntity user){
        List<CommentEntity> getCommentList=commentService.queryListComment(user.getUserId());
        return R.ok().put("commentList",getCommentList);
    }
}
