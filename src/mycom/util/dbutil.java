package mycom.util;

import java.io.InputStream;

import mycom.test.mybatis.test.MybatisTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class dbutil
{
	// TODO:此处配置文件的加载应该只加载一次，而不是每次都加载，sqlSession工程的构建应该只构建一次，看看是否可以使用单例；
	public static SqlSession getMybatisSqlSession()
	{
        //mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
		
		return session;
	}
	
	
	
}
