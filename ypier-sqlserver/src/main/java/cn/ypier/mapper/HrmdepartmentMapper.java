package cn.ypier.mapper;

import cn.ypier.tbpojo.Hrmdepartment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrmdepartmentMapper {

    List<Hrmdepartment> selectAll();
}
