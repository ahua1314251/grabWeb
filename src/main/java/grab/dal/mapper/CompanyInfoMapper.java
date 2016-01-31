package grab.dal.mapper;

import grab.dal.model.CompanyInfo;
import grab.dal.model.CompanyInfoWithBLOBs;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyInfoWithBLOBs record);

    int insertSelective(CompanyInfoWithBLOBs record);

    CompanyInfoWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CompanyInfoWithBLOBs record);

    int updateByPrimaryKey(CompanyInfo record);
}