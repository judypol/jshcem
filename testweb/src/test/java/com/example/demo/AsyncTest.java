package com.example.demo;

import org.springframework.scheduling.annotation.Async;

/**
 * Created by judysen on 2017/9/10.
 */
public class AsyncTest {
    @Async("mySimpleAsync")
    public String asyncMethod1(){
        Thread current= Thread.currentThread();
        System.out.println("threadID:"+current.getId()+";threadName:"+current.getName());
        try{
            Thread.sleep(1000);
        }catch (Exception ex){
            System.out.println(ex);
        }finally {

        }

        return current.getName();
    }
}
