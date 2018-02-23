package mycom.controller;

import java.net.URLDecoder;
import java.util.Collection;
import java.util.List;

import mycom.dao.DepartmentMapper;
import mycom.dao.SampleMapper;
import mycom.pojo.Department;
import mycom.pojo.Sample;
import mycom.util.dbutil;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ApartmentListController
{
    @ResponseBody
    @RequestMapping(value = "/getDepartmentList", produces = "text/plain; charset=utf-8")
    public String getDepartmentList(@RequestBody String str) throws Exception
    {
        int  errorCode = 0;
        String reason = "";
    	
        //JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));//这个地方为何要用URLDecoder.decode(str, "UTF-8")，这个方法，不懂不懂：答：是为了对URL的参数进行解码转码；
          JSONObject resp = new JSONObject();
          
          SqlSession session = dbutil.getMybatisSqlSession();
          DepartmentMapper depart = session.getMapper(DepartmentMapper.class);
        
          List<Department> result = depart.selectAll();
          System.out.println(result.get(1).getName());
          

		  resp.put("errorCode", errorCode );
		  resp.put("reason", reason);
          resp.put("result", result);
          return resp.toString();
    }

	
	

}
