package cn.ypier;

import cn.hutool.core.convert.Convert;
import cn.ypier.constant.RedisConstant;
import cn.ypier.dto.PermissionRoleDto;
import cn.ypier.mapper.SysPermissionMapper;
import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysRole;
import cn.ypier.service.RedisService;
import cn.ypier.service.SysPermissionService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Ypier
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysPermissionTest {

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private RedisService redisService;

    @Test
    public void demo1(){
        Object object = redisService.hGet(RedisConstant.RESOURCE_ROLES_MAP, "/user/add");
        // 对象转化成 List
        List<String> stringList = Convert.toList(String.class, object);
        System.out.println(stringList);
    }


    @Test
    public void  demo(){
        List<PermissionRoleDto> permissiionList = sysPermissionService.permissionListRole();
        System.out.println(permissiionList);
    }


//    @Test
//    public void  demo(){
//        List<SysPermission> permissionList = sysPermissionMapper.permissionListRole();
//        permissionList.stream()
//                .map(permission -> role_to_list(permission))
//                .collect(Collectors.toList());
//
//        //存放在 redis
//
//        System.out.println(permissionList);
//    }
//
//    private Map<String,List<String>> role_to_list(SysPermission permission) {
//        Map<String, List<String>> map = new HashMap<>();
//
//        List<String> stringList = permission.getRoles()
//                .stream()
//                .map(SysRole::getName)
//                .collect(Collectors.toList());
//
//        map.put(permission.getUrl(),stringList);
//
//        return map;
//    }

}
