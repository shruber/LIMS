package mycom.dao;

import java.util.List;

import mycom.pojo.ModelAnalysisItems;

public interface ModelAnalysisItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelAnalysisItems record);

    int insertSelective(ModelAnalysisItems record);

    ModelAnalysisItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelAnalysisItems record);

    int updateByPrimaryKey(ModelAnalysisItems record);

	List<ModelAnalysisItems> selectByModelId(int modelId);
}