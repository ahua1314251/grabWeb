package grab.dal.mapper;

import grab.dal.model.YearReport;

public interface YearReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YearReport record);

    int insertSelective(YearReport record);

    YearReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YearReport record);

    int updateByPrimaryKeyWithBLOBs(YearReport record);

    int updateByPrimaryKey(YearReport record);
}