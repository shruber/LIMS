package mycom.dao;

import java.util.Map;


public interface CommonMapper {

	int insertSample(Map<String, Object> map);

	Map selectAnalysisItemsMap(Map<String, Object> map);

	Object updateSampleAnalysisItems(Map<String, Object> map);

	Object updateCommon(Map<String, Object> map);


    
	
	
}