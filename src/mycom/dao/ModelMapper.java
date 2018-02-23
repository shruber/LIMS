package mycom.dao;

import java.util.List;

import mycom.pojo.Model;

public interface ModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);

	List<Model> selectByProductId(int productId);
}