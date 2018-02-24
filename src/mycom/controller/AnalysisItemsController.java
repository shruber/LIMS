package mycom.controller;

import java.util.List;

import mycom.dao.AnalysisItemsMapper;
import mycom.pojo.AnalysisItems;
import mycom.util.dbutil;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnalysisItemsController
{
	@ResponseBody
	@RequestMapping(value = "/getAnalysisItemsList", produces = "text/plain; charset=utf-8")
	public String getAnalysisItemsList(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";

		//JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();

		SqlSession session = dbutil.getMybatisSqlSession();
		AnalysisItemsMapper obj = session.getMapper(AnalysisItemsMapper.class);

		List<AnalysisItems> result = obj.selectAll();

		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
}
