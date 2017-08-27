package hessianService.Impl;

import com.shcem.hessian.HessianService;
import hessianService.IHelloService;

/**
 * Created by judysen on 2017/8/20.
 */
@HessianService
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
