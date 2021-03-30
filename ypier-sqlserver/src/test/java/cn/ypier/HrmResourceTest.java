package cn.ypier;

import cn.ypier.domain.HrmResource;
import cn.ypier.service.HrmResourceService;
import cn.ypier.tbclient.TbUser;
import cn.ypier.tbpojo.UserBean;
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
public class HrmResourceTest {

    @Autowired
    private HrmResourceService hrmResourceService;
    @Autowired
    private TbUser tbUser;

    @Test
    public void  test(){
        List<HrmResource> hrmResources = hrmResourceService.selectAll();
        hrmResources.forEach(hrmResource->{
            if(hrmResource.getLoginid() !=null && !hrmResource.getLoginid().isEmpty() && hrmResource.getLastname()!=null && !hrmResource.getLastname().equals("")){
                UserBean userBean = new UserBean();
                userBean.setName_chinese(hrmResource.getLastname()); // 中文名称

                userBean.setJob_num(hrmResource.getLoginid());
                userBean.setPassword("123");
                tbUser.insertTbuser(userBean);
            }

        });

    }
}
