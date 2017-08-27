package JShcem.Trade.dao;

import JShcem.Trade.dao.model.City;
import org.springframework.stereotype.Repository;

/**
 * Created by judysen on 2017/8/27.
 */
@Repository
public interface CityDao {
    City selectCity(int id);
}
