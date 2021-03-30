package cn.ypier;

import cn.ypier.pojo.SysUser;
import cn.ypier.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * @Author Ypier
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class SeataTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("zhangshao");
        userService.insert(sysUser);
    }
}
