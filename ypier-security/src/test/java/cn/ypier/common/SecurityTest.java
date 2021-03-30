package cn.ypier.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author Ypier
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SecurityTest {
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Test
    public void  test(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    class Goods{
        private String goodsAddress;

        public Goods(String goodsAddress) {
            this.goodsAddress = goodsAddress;
        }

        public void setGoodsAddress(String goodsAddress) {
            this.goodsAddress = goodsAddress;
        }

        public String getGoodsAddress() {
            return goodsAddress;
        }
    }

    @Test
    public void test1(){

    }
}
