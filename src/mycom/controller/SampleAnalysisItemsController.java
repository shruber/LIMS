package mycom.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import mycom.dao.AnalysisItemsMapper;
import mycom.dao.CommonMapper;
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
		
		//遍历数组；
		
		//使用分析项目表名和sampleId，获取分析项目字段名和数值，没有数值的，表示为null；交给前端显示；
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "`" + tableNameArray[1] + "`");
		map.put("sampleId", sampleId);

		CommonMapper analysisItems = session.getMapper(CommonMapper.class);
		Map analysisItemsMap = analysisItems.selectAnalysisItemsMap(map);
		//现在遇到一个问题，只有数据库有值的时候，才能返回字段的map，下次尝试的时候，可以在select后加参数试一试，把需要的几个字段都写上；
		//因为事先不确定该表中的字段，所以没办法把字段加上；只能在存储的时候，给每个字段加个初始值了；
		//

		
		
		
		
		
		
		
		
		String result = "";
		
		
		session.close();
		resp.put("errorCode", errorCode);
		resp.put("reason", reason);
		resp.put("result", result);
		return resp.toString();
	}
	
	
	
	
}
