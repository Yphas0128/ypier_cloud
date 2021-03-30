package cn.ypier;

import cn.ypier.result.CommonResult;
import cn.ypier.service.HrmdepartmentService;
import cn.ypier.service.SeverSqlService;
import cn.ypier.tbpojo.Hrmdepartment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/*
 * @Author Ypier
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeverSqlTest {

    @Autowired
    private SeverSqlService severSqlService;

    @Autowired
    private HrmdepartmentService hrmdepartmentService;
    @Test
    public void  test(){
        CommonResult commonResult = severSqlService.selectAll();
        List<Hrmdepartment> hrmdepartmentList = (List<Hrmdepartment>) commonResult.getData();
        hrmdepartmentService.insertAll(hrmdepartmentList);
        System.out.println(hrmdepartmentList);
    }
}
