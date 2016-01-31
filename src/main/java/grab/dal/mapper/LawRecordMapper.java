package grab.dal.mapper;

import grab.dal.model.LawRecord;

public interface LawRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LawRecord record);

    int insertSelective(LawRecord record);

    LawRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LawRecord record);

    int updateByPrimaryKeyWithBLOBs(LawRecord record);

    int updateByPrimaryKey(LawRecord record);
}