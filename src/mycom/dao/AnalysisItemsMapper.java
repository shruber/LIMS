package mycom.dao;

import mycom.pojo.AnalysisItems;

public interface AnalysisItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalysisItems record);

    int insertSelective(AnalysisItems record);

    AnalysisItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalysisItems record);

    int updateByPrimaryKey(AnalysisItems record);
}