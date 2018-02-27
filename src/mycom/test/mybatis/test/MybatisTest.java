package mycom.test.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;


import mycom.dao.AnalysisItemsMapper;
import mycom.dao.ModelAnalysisItemsMapper;
import mycom.dao.SampleMapper;
import mycom.pojo.AnalysisItems;
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
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "mycom.test.mybatis.mapping.limsTestMapper.getTest";//映射sql的标识字符串
        LimsTest test = session.selectOne(statement, 1);
        System.out.println(test);


        String statement2 = "mycom.dao.SampleMapper.selectByPrimaryKey";//映射sql的标识字符串
        Sample result = session.selectOne(statement2, 2);
        System.out.println(result.getName());
  
        String statement3 = "mycom.dao.SampleMapper.selectAll";//映射sql的标识字符串
        List<Sample> result3 = session.selectList(statement3);
        System.out.println(result3.get(0).getName());
        
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
		System.out.println(result5.get(0).getName());*/
		
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

       
        session.commit();
        session.close();
    }
}
