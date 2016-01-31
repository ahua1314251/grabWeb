package grab.dal.mapper;

import grab.dal.model.Result;

public interface ResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKeyWithBLOBs(Result record);

    int updateByPrimaryKey(Result record);
}