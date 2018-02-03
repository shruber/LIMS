package mycom.test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Log4jTest2Test
{
	private static Log4jTest2 log4j = new Log4jTest2();
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testLog4jTest()
	{

		log4j.log4jTest();
	}

}
