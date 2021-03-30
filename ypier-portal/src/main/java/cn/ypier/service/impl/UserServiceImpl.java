package cn.ypier.service.impl;/*
 * @Author Ypier
 */

import cn.ypier.client.MenuClient;
import cn.ypier.mapper.SysUserMapper;
import cn.ypier.pojo.SysMenu;
import cn.ypier.pojo.SysUser;
import cn.ypier.result.CommonResult;
import cn.ypier.service.UserService;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.callback.Callback;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private MenuClient menuClient;

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void test(){
        SysMenu menu = new SysMenu();
        menu.setName("aaa");
        CommonResult commonResult = menuClient.menu_test(menu);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void insert(SysUser sysUser) {
        try {
            sysUser.setAccount("zhan");
           // sysUserMapper.insert(sysUser);
            SysMenu menu = new SysMenu();
            menu.setName("aaa");
            CommonResult commonResult = menuClient.menu_test(menu);
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
    }
}
