package cn.exrick.xboot.modules.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.exrick.xboot.modules.base.mpbase.mapper.BaseDao;
import cn.exrick.xboot.modules.web.entity.##ClassEntityName#;
import cn.exrick.xboot.modules.web.mapper.##ClassDaoName#;
import cn.exrick.xboot.modules.web.service.##ClassServiceName#;

@Service
public class ##ClassServiceImplName# implements ##ClassServiceName# {

    @Autowired
    ##ClassDaoName# dao;

    @Override
    public BaseDao<##ClassEntityName#> getDao() {
        return dao;
    }

}
