package mycom.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import mycom.dao.AnalysisItemsMapper;
import mycom.pojo.AnalysisItems;
import mycom.util.dbutil;


import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnalysisItemsController
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
		AnalysisItemsMapper obj = session.getMapper(AnalysisItemsMapper.class);

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
		AnalysisItemsMapper obj = session.getMapper(AnalysisItemsMapper.class);

		List<AnalysisItems> result = obj.selectAll();
		
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
			
/*	注释中也是org.json.JSONArray转化为int数组的方法，和上方代码效果相同
		//org.json.JSONArray不能直接转化为数组，尝试先转string，再转array；
		String str1 = req.get("analysisItemId").toString();
		//正常情况下，JSONArray很少转化为数组，
		//此处字符串比正常数组多出一对[],要先去除；
		String str2 = str1.substring(1);
		String idsStr = str2.substring(0,str2.length()-1);
		
		String[] idsStrArray = idsStr.split(",");

		int[] ids = new int[idsStrArray.length];  
		for(int i=0; i < idsStrArray.length; i++)
		{  
			ids[i] = Integer.parseInt(idsStrArray[i]);  
		} */
		
		
		SqlSession session = dbutil.getMybatisSqlSession();
		AnalysisItemsMapper obj = session.getMapper(AnalysisItemsMapper.class);
		List<AnalysisItems> result = obj.selectByIds(ids);
	
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
