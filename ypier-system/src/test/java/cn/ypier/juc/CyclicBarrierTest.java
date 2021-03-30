package cn.ypier.juc;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 障栅 测试
 */
public class CyclicBarrierTest {
    private CountDownLatch latch = new CountDownLatch(5);
    /**
     * 一次只可以通过
     * 简单测试
     */
    @Test
    public void test(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        //
        for (int i= 0; i<11;i++){
           new Thread(()->{
               try {
                   TimeUnit.SECONDS.sleep(100);
                   long startTime = System.currentTimeMillis();
                   cyclicBarrier.await();
                   long endTime = System.currentTimeMillis();
                   System.out.println(Thread.currentThread().getName()+",等待  "+Math.floor((endTime-startTime))+"一起开始出发");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (BrokenBarrierException e) {
                   e.printStackTrace();
               }
           },i+"线程").start();
        }

        try {
            latch.await(); // 主线程等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }







}
