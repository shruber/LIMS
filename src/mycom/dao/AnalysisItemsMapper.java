package mycom.dao;

import java.util.List;

import mycom.pojo.AnalysisItems;

public interface AnalysisItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalysisItems record);

    int insertSelective(AnalysisItems record);

    AnalysisItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalysisItems record);

    int updateByPrimaryKey(AnalysisItems record);

	List<AnalysisItems> selectAll();

	List<AnalysisItems> selectByIds(int[] ids);
}