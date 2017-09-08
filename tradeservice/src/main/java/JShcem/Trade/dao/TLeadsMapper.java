package JShcem.Trade.dao;

import JShcem.Trade.dao.model.TLeads;
import com.shcem.mybatis.query.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TLeadsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TLeads record);

    int insertSelective(TLeads record);

    TLeads selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TLeads record);

    int updateByPrimaryKey(TLeads record);

    List<TLeads> selectByPager(Pageable pageable);
}