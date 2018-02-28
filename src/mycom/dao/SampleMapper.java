package mycom.dao;


import java.util.List;
import java.util.Map;

import mycom.pojo.Sample;

public interface SampleMapper {

	
    int deleteByPrimaryKey(Integer id);

    int insert(Sample record);

    int insertSelective(Sample record);

    Sample selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sample record);

    int updateByPrimaryKey(Sample record);

	List<Sample> selectAll();


	List<Sample> selectSampleByDeparmentIdAndStatus(Map<String, Object> map);
}