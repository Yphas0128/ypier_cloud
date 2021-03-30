package cn.ypier.tbclient;/*
 * @Author Ypier
 */

import cn.ypier.tbpojo.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ypier-system",path = "/tbuser")
public interface TbUser {
    @PostMapping(value = "/insertTbuser")
    void  insertTbuser(@RequestBody UserBean userBean);
}
