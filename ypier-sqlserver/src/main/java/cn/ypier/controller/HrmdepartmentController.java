package cn.ypier.controller;

import cn.ypier.result.CommonResult;
import cn.ypier.service.HrmdepartmentService;
import cn.ypier.tbpojo.Hrmdepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Author Ypier
 */
@RestController
@RequestMapping(value = "/hrmdepartment")
public class HrmdepartmentController {
    @Autowired
    private HrmdepartmentService hrmdepartmentService;

    @GetMapping(value = "/selectAll")
    public CommonResult selectAll(){
        List<Hrmdepartment> hrmdepartmentList = hrmdepartmentService.selectAll();
        return CommonResult.success(hrmdepartmentList);
    }
}
