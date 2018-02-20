package mycom.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactoryTest
{
	/**
	 * @param args
	 */
    private static ApplicationContext context = null;

    public static void load() throws Exception
    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");   
    }

    public static Object getBean(String name)
    {
        return context.getBean(name);
    }

	
	
	
	
	public static void main(String[] args)
	{

        try
        {
             SpringBeanFactoryTest.load();
        } catch (Exception e)
        {
             // TODO Auto-generated catch block
             e.printStackTrace();
        }

        HelloWorld ssss = (HelloWorld)SpringBeanFactoryTest.getBean("hello");
        System.out.println(ssss.number);

	}
}
