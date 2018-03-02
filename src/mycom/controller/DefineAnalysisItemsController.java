package mycom.controller;

import java.net.URLDecoder;
import java.util.List;

import mycom.dao.DefineAnalysisItemsMapper;
import mycom.pojo.DefineAnalysisItems;
import mycom.util.dbutil;


import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefineAnalysisItemsController
{
	
	@ResponseBody
	@RequestMapping(value = "/getAnalysisItemsTableName", produces = "text/plain; charset=utf-8")
	public String getAnalysisItemsTableName(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";

		JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();
		
		JSONArray analysisItemsId = (JSONArray) req.get("analysisItemsId");
		List idList = analysisItemsId.toList();
		
		
		SqlSession session = dbutil.getMybatisSqlSession();
		DefineAnalysisItemsMapper obj = session.getMapper(DefineAnalysisItemsMapper.class);

		List result = obj.selectTableNameById(idList);
		
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
	
	
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
		DefineAnalysisItemsMapper obj = session.getMapper(DefineAnalysisItemsMapper.class);

		List<DefineAnalysisItems> result = obj.selectAll();
		
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAnalysisItemsById", produces = "text/plain; charset=utf-8")
	public String getAnalysisItemsById(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";

		JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();	
		JSONArray idsJSONArray = (JSONArray) req.get("analysisItemId");
		//org.json.JSONArray没有toArray()方法，但是有toList()方法
		List idsList = (List) idsJSONArray.toList();

		int[] ids = new int[idsList.size()];  
		for(int i = 0;i<idsList.size();i++){
			ids[i] = (Integer) idsList.get(i);  
		}
		//System.out.println(idsList);

			
		SqlSession session = dbutil.getMybatisSqlSession();
		
		DefineAnalysisItemsMapper obj = session.getMapper(DefineAnalysisItemsMapper.class);
		List<DefineAnalysisItemsMapper> result = obj.selectByIds(ids);
	
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
	
	public static int StrToInt(String str)
	{
		return Integer.parseInt(str);
	}
	
	
}
