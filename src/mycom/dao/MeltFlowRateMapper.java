package mycom.dao;

import mycom.pojo.MeltFlowRate;

public interface MeltFlowRateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeltFlowRate record);

    int insertSelective(MeltFlowRate record);

    MeltFlowRate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeltFlowRate record);

    int updateByPrimaryKey(MeltFlowRate record);
}