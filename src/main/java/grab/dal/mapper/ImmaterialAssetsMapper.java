package grab.dal.mapper;

import grab.dal.model.ImmaterialAssets;

public interface ImmaterialAssetsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImmaterialAssets record);

    int insertSelective(ImmaterialAssets record);

    ImmaterialAssets selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImmaterialAssets record);

    int updateByPrimaryKeyWithBLOBs(ImmaterialAssets record);

    int updateByPrimaryKey(ImmaterialAssets record);
}