package cn.ypier;

import cn.ypier.pojo.SysUser;
import jnr.ffi.annotations.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.*;

/*
 * @Author Ypier
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class OptionalTest {

    /**
     * 关于
     * optional 
     */
    @Test
    public void test(){
    //  Optional<SysUser> opt = Optional.empty();
    //  SysUser sysUser = opt.get();会抛出NoSuchElementException 异常

        SysUser user = null;
        // Optional<SysUser> opt = Optional.of(user); // 传入null值 抛NullPointerException 异常 stream

        String d = Optional.ofNullable(user).map(SysUser::getAccount).orElse("D");// 传入null值 不会抛NullPointerException 异常
    }



    @Test
    public void  test1(){
        int[]  ints = {1,2,3,4,5,6};
        Integer integer = testA(ints);
        System.out.println(integer);
    }

    private Integer testA(int[] ints) {
        TreeSet<Integer> treeSet = new TreeSet();
        for (int i : ints) {
            if((i&1) == 1)
                treeSet.add(i*2);
            else
                treeSet.add(i);
        }
        Integer res = treeSet.last() - treeSet.first();
        //
        while ((treeSet.last()&1) != 1){
            treeSet.add(treeSet.last() >> 1);
            treeSet.remove(treeSet.last());
            res = Math.min(res,treeSet.last() - treeSet.first());
        }

        return res;
    }
}
