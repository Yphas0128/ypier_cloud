package cn.ypier.mapper;

import cn.ypier.domain.HrmResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrmResourceMapper {
    List<HrmResource> selectAll();
}
