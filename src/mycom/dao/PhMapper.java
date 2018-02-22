package mycom.dao;

import mycom.pojo.Ph;

public interface PhMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ph record);

    int insertSelective(Ph record);

    Ph selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ph record);

    int updateByPrimaryKey(Ph record);
}