package cn.ypier;
/*
 * 锁测试
 * @Author Ypier
 */

import cn.hutool.http.HttpUtil;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.Max;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LockTest {


    @Test
    public void test2(){
        String s = HttpUtil.get("");
        System.out.println(s);
//        int[] A = new int[]{2,7,11,15};
//        int[] B = new int[]{1,10,4,11};
//        int[] ints = advantageCount(A, B);
//        System.out.println(ints);
    }


    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Map<Char,Integer> map = new HashMap<>();
        for (int end=0, start = 0;end< n;end++){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                start = Math.max(map.get(c),start);
            }


        }


        return 0;
    }

    class Node {

        public int value;
        public int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public int[] advantageCount(int[] A, int[] B) {
        // A 排序
        Arrays.sort(A);

        LinkedList<Node> list = new LinkedList();
        for (int i=0;i<B.length;i++){
            list.add(new Node(B[i],i));
        }

        //排序
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });

        for (int i=0;i< A.length;i++){
            if(A[i]>list.getFirst().value){
                B[list.removeFirst().index] = A[i];
            }else{
                B[list.removeLast().index] = A[i];
            }
        }
        return B;
    }

    @Test
    public void test1(){
        int[][] matrix =new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] transpose = transpose(matrix);
        for (int i =0; i< transpose.length;i++){
            for (int j=0;j< transpose[i].length;j++) {
                System.out.println(transpose[i][j]);
            }
        }
    }

    private int[][] transpose(int[][] matrix) {

        int[][] res = new int[matrix[0].length][matrix.length];

        for (int i =0; i< matrix.length;i++){
            for (int j=0;j< matrix[i].length;j++) {
                res[j][i]=matrix[i][j];
            }
        }
        return  res;
    }


    /**
     * 模拟抢票
     * lock 测试
     */

    @Test
    public  void  test(){
        for (int i=0; i<10; i++){
            Thread thread = new Thread(new T(),i + "");
            thread.start();
        }
    }

    private  volatile  int total = 1000;

    private  ReentrantLock lock = new ReentrantLock();

    class T implements Runnable{

        @Override
        public void run() {
            while (total > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"正在出售第"+(1000-total+1)+"张票");
                total--;
            }
        }

    }
}
