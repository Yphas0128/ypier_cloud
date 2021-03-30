package cn.ypier.service;


import cn.ypier.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Ypier
 */
@FeignClient(value = "ypier-sqlserver",path = "/hrmdepartment")
public interface SeverSqlService {
    @GetMapping(value = "/selectAll")
    CommonResult selectAll();
}
