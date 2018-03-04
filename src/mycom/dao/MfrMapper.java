package mycom.dao;

import mycom.pojo.Mfr;

public interface MfrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mfr record);

    int insertSelective(Mfr record);

    Mfr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mfr record);

    int updateByPrimaryKey(Mfr record);

	int updateBySampleIdSelective(Mfr record);
}