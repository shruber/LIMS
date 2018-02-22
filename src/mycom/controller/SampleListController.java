package mycom.controller;


import java.util.ArrayList;
import java.util.List;

import mycom.dao.SampleMapper;
import mycom.pojo.Sample;
import mycom.test.mybatis.domain.LimsTest;
import mycom.util.dbutil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleListController
{
	@RequestMapping("/getSampleList")
	public ModelAndView getSampleList()
	{
		SqlSession session = dbutil.getMybatisSqlSession();
        SampleMapper sample = session.getMapper(SampleMapper.class);
        
        List<Sample> result = sample.selectAll();
        System.out.println(result.get(2).getName());
		
		return new ModelAndView ("index", "result", result);
	}
}




