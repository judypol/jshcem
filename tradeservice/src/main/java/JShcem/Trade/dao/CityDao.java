package JShcem.Trade.dao;

import JShcem.Trade.dao.model.City;
import com.shcem.mybatis.query.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by judysen on 2017/8/27.
 */
@Repository
public interface CityDao {
    City selectCity(int id);
    List<City> selectCities(Pageable pageable);
}
