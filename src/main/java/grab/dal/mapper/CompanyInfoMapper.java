package grab.dal.mapper;

import grab.dal.model.CompanyInfo;
import grab.dal.model.CompanyInfoWithBLOBs;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Integer keyId);

    int insert(CompanyInfoWithBLOBs record);

    int insertSelective(CompanyInfoWithBLOBs record);

    CompanyInfoWithBLOBs selectByPrimaryKey(Integer keyId);

    int updateByPrimaryKeySelective(CompanyInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CompanyInfoWithBLOBs record);

    int updateByPrimaryKey(CompanyInfo record);
}