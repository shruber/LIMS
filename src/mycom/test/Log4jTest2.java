package mycom.test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest2
{
	   static Logger logger = Logger.getLogger(Log4jTest2.class);

	   public void log4jTest()
	   {
		   String m = "test1";
		   logger.debug("line 15: " + m);
	   }	   
	   
	   public void log4jTest2()
	   {
		   String m = "test1";
		   logger.debug("line 19: " + m);
	   }
	   
		@Test
		public void testLog4jTest2()
		{

			log4jTest2();
		}
	   

}
