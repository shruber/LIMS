package mycom.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mycom.dao.CommonMapper;
import mycom.dao.DefineAnalysisItemsMapper;
import mycom.dao.ModelAnalysisItemsMapper;
import mycom.dao.SampleAnalysisItemsMapper;
import mycom.dao.SampleMapper;
import mycom.pojo.ModelAnalysisItems;
import mycom.pojo.Sample;
import mycom.test.mybatis.domain.LimsTest;
import mycom.util.dbutil;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest
{

    public static void main(String[] args) throws IOException {
        //mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        
        System.out.println("-------------------------");
       
        
        int sampleId = 28;
        String tableName = "mfr";
    	//使用分析项目分表的表名和sampleId，获取该分析项目数值，没有数值的，表示为null；交给前端显示；
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "`" + tableName + "`");
		map.put("sampleId", sampleId);

		CommonMapper analysisItems = session.getMapper(CommonMapper.class);
		Map analysisItemsMap = analysisItems.selectAnalysisItemsMap(map);
		//这个地方是从分析方法分表中获取到的analysisItemsMap是右值；
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("tableName", tableName);
		//现在获取左值；也就是使用tableName，从defineAnalysisItems表中分析项目的详细信息；
		DefineAnalysisItemsMapper analysisItemDetail = session.getMapper(DefineAnalysisItemsMapper.class);
		Map analysisItemDetailMap = analysisItemDetail.selectBytableName(map1);
		System.out.println("sssss");
        
        
        
        
	    /*
		int sampleId = 25;
	
		SampleAnalysisItemsMapper obj = session.getMapper(SampleAnalysisItemsMapper.class);

		String tableName = obj.selectTableNameBySampleId(sampleId);
		
		String[] tableNameArray = tableName.split("\\|");
		System.out.println(tableNameArray[1]);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "`" + tableNameArray[1] + "`");
		map.put("sampleId", sampleId);

		CommonMapper analysisItems = session.getMapper(CommonMapper.class);
		Map analysisItemsMap = analysisItems.selectAnalysisItemsMap(map);
		System.out.println(analysisItemsMap.toString());
		
 
        
        int departmentId = 1;
        byte status = 0;
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", departmentId);
		map.put("status", status);
		
        SampleMapper sample = session.getMapper(SampleMapper.class);
        
        List<Sample> result = sample.selectSampleByDeparmentIdAndStatus(map);
        System.out.println(result.get(0).getName());
        
   
        CommonMapper common = session.getMapper(CommonMapper.class);
		
		//在各个分析表中插入一条记录，只给表名和name(使用sample的值),sampleId属性；
        String tableName ="meltFlowRate";
        int sampleId = 21;
		
        
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "`" + tableName + "`");
		map.put("sampleId", sampleId);
		int res = common.insertSample(map);*/
        
        
        
        
        
        
        
        
        
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
 /*       String statement = "mycom.test.mybatis.mapping.limsTestMapper.getTest";//映射sql的标识字符串
        LimsTest test = session.selectOne(statement, 1);
        System.out.println(test);


        String statement2 = "mycom.dao.SampleMapper.selectByPrimaryKey";//映射sql的标识字符串
        Sample result = session.selectOne(statement2, 2);
        System.out.println(result.getName());
  
        String statement3 = "mycom.dao.SampleMapper.selectAll";//映射sql的标识字符串
        List<Sample> result3 = session.selectList(statement3);
        System.out.println(result3.get(0).getName());*/
        
        System.out.println("-------------------------");

        
        
        
        
        
        
        
        
        
        
        
/*        //使用动态代理的方式，直接调用方法，无需映射sql的标识字符串
        SampleMapper sample = session.getMapper(SampleMapper.class);
        Sample oneSample = sample.selectByPrimaryKey(1);
        System.out.println(oneSample.getName());
        
        List<Sample> sampleList= sample.selectAll();
        System.out.println(sampleList.get(2).getName());
        
        
		AnalysisItemsMapper obj = session.getMapper(AnalysisItemsMapper.class);

		List<AnalysisItems> result2 = obj.selectAll();
		 System.out.println(result2.get(0).getName());
        
		 
		 ModelAnalysisItemsMapper obj2 = session.getMapper(ModelAnalysisItemsMapper.class);

		 List<ModelAnalysisItems> result4 = obj2.selectByModelId(1);
		 System.out.println(result4.get(1).getAnalysisitemid());

		int[] ids = {1,2};
		AnalysisItemsMapper obj3 = session.getMapper(AnalysisItemsMapper.class);
		List<AnalysisItems> result5 = obj3.selectByIds(ids);
		System.out.println(result5.get(0).getName());
		
        SampleMapper sample = session.getMapper(SampleMapper.class);
		
		Sample sampleObj = new Sample();
		sampleObj.setCreater(11);
		sampleObj.setDepartmentid(1);
		sampleObj.setModelname("qqw");
		sampleObj.setName("qqq");
		
		sampleObj.setStatus((byte) 1);		
		
		//Date
		Timestamp samplingTime = new Timestamp(System.currentTimeMillis());  
		Timestamp creationTime = new Timestamp(System.currentTimeMillis());
	 
	    sampleObj.setSamplingtime(samplingTime);
		sampleObj.setCreationtime(creationTime );

		Object res = sample.insertSelective(sampleObj);
		System.out.println(res);
		
        List<Sample> res2 = sample.selectAll();
        System.out.println(res2.get(2).getName());
*/
       
        session.commit();
        session.close();
    }
}
