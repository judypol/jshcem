package JShcem.Trade.service.impl;

import JShcem.Trade.dao.CityDao;
import JShcem.Trade.dao.model.City;
import JShcem.Trade.service.IHelloService;
import com.alibaba.fastjson.JSON;
import com.shcem.hessian.HessianService;
import com.shcem.mybatis.query.Page;
import com.shcem.mybatis.query.Pageable;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public String sayAllHello() {
        Pageable pageable=new Pageable();
        pageable.setPageIndex(2);
        pageable.setPageSize(2);
        List<City> cityPage=cityDao.selectCities(pageable);
        Page<City> page=new Page<>();
        page.setTotalPages(pageable.getTotalPages());
        page.setPageIndex(pageable.getPageIndex());
        page.setPageSize(pageable.getPageSize());
        page.setTotalRecords(pageable.getTotalRecords());
        page.setContent(cityPage);
        return JSON.toJSONString(page);
    }

}
