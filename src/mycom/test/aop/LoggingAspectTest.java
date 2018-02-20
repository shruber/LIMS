package mycom.test.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//把这个类声明为一个切面：1.需要将该类放入到IOC容器中，2.在声明为一个切面
@Aspect    //2
@Component  //1
public class LoggingAspectTest
{
   //声明该方法是一个前置通知：在目标方法开始之前执行
   @Before("execution(public int mycom.test.aop.ArithmeticCalculatorImpl.*(int, int))")
   public void beforeMethod(JoinPoint joinPoint)
   {
         String methodName = joinPoint.getSignature().getName();
         List<Object> args = Arrays.asList(joinPoint.getArgs());
         System.out.println("The method "+methodName+" begins with : "+args);
   }

}
