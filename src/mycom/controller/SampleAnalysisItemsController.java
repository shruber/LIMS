package mycom.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mycom.dao.CommonMapper;
import mycom.dao.DefineAnalysisItemsMapper;
import mycom.dao.MfrMapper;
import mycom.dao.PhMapper;
import mycom.dao.SampleAnalysisItemsMapper;
import mycom.pojo.Mfr;
import mycom.pojo.Ph;
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
		//CommonMapper obj = session.getMapper(CommonMapper.class);
		
		Object result = null;
		Iterator<String> sIterator = req.keys();  
		while(sIterator.hasNext())
		{
		    String key = sIterator.next();  
		    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
		    JSONObject item = (JSONObject) req.get(key);  
		    System.out.println("key: " + key + ", value" + item);  

			String tableName = key; 
			
			//org.json.JSONObject不能直接转map，以后考虑用另外一个包，或者是用阿里巴巴的包，这里手动赋值一下，写个函数；
			
			//Map<String, Object> map = JSONObjectToMap(item);
			//map.put("tableName", "`" + tableName + "`");
			

			/* 这里更新数据，有两种思路，一种是每个表用自己自动生成的updateByPrimaryKeySelective来写，另一种是使用同一个SQL来写；
			 * 所有的表都要使用同一个sql语句，应该怎么写呢？
			 * 感觉有些难度，但是我喜欢这个感觉； 
			 * 动态表名之前已经解决了，只要实现动态字段名，应该就差不多了；
			 * 因为不确定会传入多少个condition和data，那么在数据库保存的时候，就要使用循环语句进行判断，sql中的循环语句我还得找一下；
			 * 在写sql的时候，发现怎么写都是错的，但是因为着急找工作，不能花太多的时间研究这个，这里使用取巧的方法来做，等以后找到工作了再重新写；
			 *
			 * 先边使用每个表自动生成的updateByPrimaryKeySelective来写，这个没有普适性，只是拿来救急的；
			 * 
			 * */
			 System.out.println("tableName: " + tableName); 
			if(tableName.equals("mfr"))
			{
				MfrMapper mfr = session.getMapper(MfrMapper.class);
				
		        Mfr record = new Mfr();
		        
		        
		        record.setSampleid(item.getInt("sampleId"));
		        record.setAnalyser(item.getInt("analyser"));
		        record.setData1(item.getString("data1"));
		        record.setData2(item.getString("data2"));
		        record.setCondition1value(item.getString("condition1Value"));
		        record.setCondition2value(item.getString("condition2Value"));
		        record.setResult(item.getString("result"));
		        record.setStatus((byte) (((Integer) item.get("status")).intValue()));

		        mfr.updateBySampleIdSelective(record);

		        System.out.println("result " + result); 
			}
			if(tableName.equals("ph"))
			{
				PhMapper ph = session.getMapper(PhMapper.class);
				
		        Ph record = new Ph();
		        
		        
		        record.setSampleid(item.getInt("sampleId"));
		        record.setAnalyser(item.getInt("analyser"));
		        record.setData1(item.getString("data1"));
		        record.setData2(item.getString("data2"));
		        record.setResult(item.getString("result"));
		        record.setStatus((byte) (((Integer) item.get("status")).intValue()));

		        ph.updateBySampleIdSelective(record);

				
				
			}
			
			
		 
			
		}  
		
		
		session.commit();
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
	
	
	public Map<String, Object> JSONObjectToMap(JSONObject item)
	{
		Iterator<String> iterator = item.keys();
		Map<String, Object> map = new HashMap<String, Object>();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			
			map.put(key, item.get(key));
		}
		return map;
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
