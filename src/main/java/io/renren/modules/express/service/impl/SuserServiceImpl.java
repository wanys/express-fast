package io.renren.modules.express.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.express.dao.SuserDao;
import io.renren.modules.express.entity.SuserEntity;
import io.renren.modules.express.service.SuserService;


@Service("suserService")
public class SuserServiceImpl extends ServiceImpl<SuserDao, SuserEntity> implements SuserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SuserEntity> page = this.page(
                new Query<SuserEntity>().getPage(params),
                new QueryWrapper<SuserEntity>()
        );

        return new PageUtils(page);
    }

}