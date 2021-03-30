package cn.ypier;/*
 * @Author Ypier
 */

import cn.ypier.mapper.SysUserMapper;
import cn.ypier.pojo.SysUser;
import cn.ypier.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserModelTest {

    @Autowired
    private SysUserMapper sysUserMapper;



    @Test
    public void testSelect(){
//        List<SysUser> users = sysUserMapper.selectList(null);
//        System.out.println(users);
//        List<SysUser> users = sysUserMapper.selectSysuser();
//        System.out.println(users);
        SysUser sysUser = sysUserMapper.selectById(1);
        System.out.println(sysUser);
        Map<String, Object> map = new HashMap<>();
        map.put("username","user1");
        List<SysUser> users = sysUserMapper.selectByMap(map);
        System.out.println(users);
    }


    @Test
    public void selectAndPage(){
        IPage<SysUser> page = new Page<>(0, 1);
        IPage<SysUser> sysUserIPage = sysUserMapper.selectPage(page, null);
        System.out.println(sysUserIPage);
    }







}
