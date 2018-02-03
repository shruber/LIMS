package mycom.test;

import org.apache.log4j.Logger;

public class Log4jTest
{
	static Logger logger = Logger.getLogger(Log4jTest.class);


	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		String m = "test1";
		logger.debug("line 15: " + m);
		
		
	}

}
