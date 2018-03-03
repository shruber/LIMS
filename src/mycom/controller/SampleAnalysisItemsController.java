package mycom.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mycom.dao.CommonMapper;
import mycom.dao.DefineAnalysisItemsMapper;
import mycom.dao.SampleAnalysisItemsMapper;
import mycom.util.dbutil;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleAnalysisItemsController
{
	
	@ResponseBody
	@RequestMapping(value = "/patchSampleAnalysisItems", produces = "text/plain; charset=utf-8")
	public String 	patchSampleAnalysisItems(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";
		
		JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();
		
		SqlSession session = dbutil.getMybatisSqlSession();
		CommonMapper obj = session.getMapper(CommonMapper.class);
		
		Iterator<String> sIterator = req.keys();  
		while(sIterator.hasNext())
		{  
		    String key = sIterator.next();  
		    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
		    JSONObject item = (JSONObject) req.get(key);  
		    System.out.println("key: " + key + ", value" + item);  

			int sampleId = (Integer) item.get("sampleId");
			String tableName = key; 
			
			Map<String, Object> map = (Map) item;
			map.put("tableName", "`" + tableName + "`");
			map.put("sampleId", sampleId);
			//这里尝试一下直接把上边的item转化为map，然后在加上tableName和sampleId；
			
			
			
			Object result = obj.updateSampleAnalysisItems(map);
			
			
			
		}  
		
		
		
		
		

		
		
			
		
		
		session.commit();
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
	
	
	
	//目的：返回分析项目的左值（defineAnalysisItems）和右值（分析项目分表）
	//流程：已知sampleId,从sampleAnalysisItems表中获取分析项目分表的名称（tableName），然后使用tableName，
	//从defineAnalysisItems表中获取左值，结合sampleId，从分表中获取右值；	
	@ResponseBody
	@RequestMapping(value = "/getAnalysisItemsBySampleId", produces = "text/plain; charset=utf-8")
	public String getAnalysisItemsBySampleId(@RequestBody String str)
			throws Exception
	{
		int errorCode = 0;
		String reason = "";
		
		JSONObject req = new JSONObject(URLDecoder.decode(str, "UTF-8"));
		JSONObject resp = new JSONObject();
		
		int sampleId = req.getInt("sampleId");
		
		SqlSession session = dbutil.getMybatisSqlSession();
		SampleAnalysisItemsMapper obj = session.getMapper(SampleAnalysisItemsMapper.class);

		String tableName = obj.selectTableNameBySampleId(sampleId);
		
		String[] tableNameArray = tableName.split("\\|");
		
		Map<String, Object> analysisItemsValueMap = new HashMap<String, Object>();
		Map<String, Object> analysisItemsDetailMap = new HashMap<String, Object>();
		//遍历数组；
		for(int i = 1;i < tableNameArray.length; i++)
		{
			//使用分析项目分表的表名和sampleId，获取该分析项目数值，没有数值的，表示为null；交给前端显示；
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", "`" + tableNameArray[i] + "`");
			map.put("sampleId", sampleId);

			CommonMapper analysisItems = session.getMapper(CommonMapper.class);
			analysisItemsValueMap.put(tableNameArray[i], analysisItems.selectAnalysisItemsMap(map));
			//这个地方获取到的analysisItemsMap是右值；

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("tableName", tableNameArray[i]);
			//现在获取左值；也就是使用tableName，从defineAnalysisItems表中分析项目的详细信息；
			DefineAnalysisItemsMapper analysisItemDetail = session.getMapper(DefineAnalysisItemsMapper.class);
			analysisItemsDetailMap.put(tableNameArray[i], analysisItemDetail.selectBytableName(map1));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("analysisItemsDetailMap", analysisItemsDetailMap);
		result.put("analysisItemsValueMap", analysisItemsValueMap);
		
		
		
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
	
	
	
	
}
