package cn.ypier.mapper;

import cn.ypier.tbpojo.Hrmdepartment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrmdepartmentMapper {
    void insertAll(@Param("hrmdepartmentList") List<Hrmdepartment> hrmdepartmentList);
}
