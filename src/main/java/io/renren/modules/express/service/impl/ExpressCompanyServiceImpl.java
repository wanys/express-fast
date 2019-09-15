package io.renren.modules.express.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.express.dao.ExpressCompanyDao;
import io.renren.modules.express.entity.ExpressCompanyEntity;
import io.renren.modules.express.service.ExpressCompanyService;


@Service("expressCompanyService")
public class ExpressCompanyServiceImpl extends ServiceImpl<ExpressCompanyDao, ExpressCompanyEntity> implements ExpressCompanyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExpressCompanyEntity> page = this.page(
                new Query<ExpressCompanyEntity>().getPage(params),
                new QueryWrapper<ExpressCompanyEntity>()
        );

        return new PageUtils(page);
    }

}