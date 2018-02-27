package mycom.controller;

import java.net.URLDecoder;
import java.util.List;

import mycom.dao.ModelAnalysisItemsMapper;
import mycom.pojo.ModelAnalysisItems;
import mycom.util.dbutil;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ModelAnalysisItemsController
{
	@ResponseBody
	@RequestMapping(value = "/getModelAnalysisItemsList", produces = "text/plain; charset=utf-8")
	public String getModelAnalysisItemsList(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";

		JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();
		int modelId = req.getInt("modelId");
		
		SqlSession session = dbutil.getMybatisSqlSession();
		ModelAnalysisItemsMapper obj = session.getMapper(ModelAnalysisItemsMapper.class);

		List<ModelAnalysisItems> result = obj.selectByModelId(modelId);
		
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
}
