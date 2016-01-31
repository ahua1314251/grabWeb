package grab.dal.mapper;

import grab.dal.model.Investment;

public interface InvestmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Investment record);

    int insertSelective(Investment record);

    Investment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Investment record);

    int updateByPrimaryKeyWithBLOBs(Investment record);

    int updateByPrimaryKey(Investment record);
}