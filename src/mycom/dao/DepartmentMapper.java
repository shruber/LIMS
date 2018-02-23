package mycom.dao;

import java.util.List;

import mycom.pojo.Department;
import mycom.pojo.Sample;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

	List<Department> selectAll();
}