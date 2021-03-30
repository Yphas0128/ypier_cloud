package cn.ypier.service.impl;
import cn.ypier.mapper.HrmdepartmentMapper;
import cn.ypier.service.HrmdepartmentService;
import cn.ypier.tbpojo.Hrmdepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * @Author Ypier
 */

@Component
public class HrmdepartmentServiceImpl implements HrmdepartmentService {


    @Autowired
    private HrmdepartmentMapper hrmdepartmentMapper;

    @Override
    public List<Hrmdepartment> selectAll(){

        return hrmdepartmentMapper.selectAll();
    }
}
