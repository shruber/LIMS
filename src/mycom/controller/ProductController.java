package mycom.controller;

import java.net.URLDecoder;
import java.util.List;

import mycom.dao.ProductMapper;
import mycom.dao.SampleMapper;
import mycom.pojo.Department;
import mycom.pojo.Product;
import mycom.pojo.Sample;
import mycom.util.dbutil;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController
{
	@ResponseBody
	@RequestMapping(value = "/getDepartProductList", produces = "text/plain; charset=utf-8")
	public String getDepartProductList(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";

		JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();
		int departmentId = req.getInt("departmentId");
		SqlSession session = dbutil.getMybatisSqlSession();
		ProductMapper obj = session.getMapper(ProductMapper.class);

		List<Product> result = obj.selectByDepartmentId(departmentId);
		// System.out.println(result.get(0).getName());

		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}

}
