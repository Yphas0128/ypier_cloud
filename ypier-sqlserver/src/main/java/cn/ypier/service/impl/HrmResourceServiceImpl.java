package cn.ypier.service.impl;
/*
 * @Author Ypier
 */

import cn.ypier.domain.HrmResource;
import cn.ypier.mapper.HrmResourceMapper;
import cn.ypier.service.HrmResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HrmResourceServiceImpl implements HrmResourceService {

    @Autowired
    private HrmResourceMapper hrmResourceMapper;

    @Override
    public List<HrmResource> selectAll() {
        return hrmResourceMapper.selectAll();
    }
}
