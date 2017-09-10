package mianshitiTest;

import org.junit.Test;

/**
 * Created by judysen on 2017/9/10.
 */
public class mianshi {
    @Test
    public void ti1(){
        Integer a = 128,b=128;
        Integer c = 127,d=127;

        System.out.println(a==b);
        System.out.println(c==d);
    }
    @Test
    public void ti2(){
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
    @Test
    public void ti3(){
        Integer a = new Integer(128);
        int b = 128;
        Integer c = new Integer(6);
        Integer d = new Integer(6);
        System.out.println(a == b);
        System.out.println(c == d);
    }
}
