package JShcem.Trade.service.impl;

import JShcem.Trade.dao.CityDao;
import JShcem.Trade.dao.model.City;
import JShcem.Trade.service.IHelloService;
import com.alibaba.fastjson.JSON;
import com.shcem.hessian.HessianService;

import javax.annotation.Resource;

/**
 * Created by judysen on 2017/8/20.
 */
@HessianService
public class HelloService implements IHelloService {
    @Resource()
    CityDao cityDao;
    @Override
    public String sayHello() {
        City city=cityDao.selectCity(1);

        return JSON.toJSONString(city);
    }
}
