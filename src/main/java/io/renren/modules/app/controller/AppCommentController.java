package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.express.entity.CommentEntity;
import io.renren.modules.express.entity.OrderEntity;
import io.renren.modules.express.service.CommentService;
import io.renren.modules.express.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@Api("APP任务接口")
public class AppCommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 保存
     */
    @Login
    @ApiOperation("保存评论")
    @PostMapping("comment/save")
    public R save(@RequestBody CommentEntity commentEntity, @LoginUser UserEntity user){
        commentService.saveComment(commentEntity,user);
        return R.ok();
    }
}
