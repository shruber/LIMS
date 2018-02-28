package mycom.dao;

import mycom.pojo.SampleAnalysisItems;

public interface SampleAnalysisItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleAnalysisItems record);

    int insertSelective(SampleAnalysisItems record);

    SampleAnalysisItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleAnalysisItems record);

    int updateByPrimaryKey(SampleAnalysisItems record);

	String selectTableNameBySampleId(int sampleId);
}