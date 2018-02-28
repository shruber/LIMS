package mycom.dao;

import java.util.List;

import mycom.pojo.DefineAnalysisItems;

public interface DefineAnalysisItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DefineAnalysisItems record);

    int insertSelective(DefineAnalysisItems record);

    DefineAnalysisItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DefineAnalysisItems record);

    int updateByPrimaryKey(DefineAnalysisItems record);

	List<DefineAnalysisItemsMapper> selectByIds(int[] ids);

	List<DefineAnalysisItems> selectAll();

	List selectTableNameById(List idList);
}