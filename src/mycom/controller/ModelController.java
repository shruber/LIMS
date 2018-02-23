package mycom.controller;

import java.net.URLDecoder;
import java.util.List;

import mycom.dao.ModelMapper;
import mycom.dao.ProductMapper;
import mycom.dao.SampleMapper;
import mycom.pojo.Department;
import mycom.pojo.Model;
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
public class ModelController
{
	@ResponseBody
	@RequestMapping(value = "/getProductModelList", produces = "text/plain; charset=utf-8")
	public String getProductModelList(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";

		JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();
		int productId = req.getInt("productId");
		SqlSession session = dbutil.getMybatisSqlSession();
		ModelMapper obj = session.getMapper(ModelMapper.class);

		List<Model> result = obj.selectByProductId(productId);

		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}

}
