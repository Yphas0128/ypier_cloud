package cn.ypier.service.impl;
/**
 * @Author Ypier
 */

import cn.ypier.mapper.HrmdepartmentMapper;
import cn.ypier.service.HrmdepartmentService;
import cn.ypier.tbpojo.Hrmdepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HrmdepartmentServiceImpl implements HrmdepartmentService {

    @Autowired
    private HrmdepartmentMapper hrmdepartmentMapper;

    @Override
    public void insertAll(List<Hrmdepartment> hrmdepartmentList) {
        hrmdepartmentMapper.insertAll(hrmdepartmentList);
    }
}
