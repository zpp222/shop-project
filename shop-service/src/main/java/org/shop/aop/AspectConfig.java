package org.shop.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectConfig {

	private static Logger logger = LoggerFactory.getLogger(AspectConfig.class);

	ThreadLocal<Date> begin = new ThreadLocal<Date>();
	ThreadLocal<Date> end = new ThreadLocal<Date>();
	ThreadLocal<Signature> methodInfo = new ThreadLocal<Signature>();

	@Pointcut(value = "@annotation(org.shop.aop.ServiceLog)")
	public void logPointcut() {
		logger.info("logPointcut");
	}

	@Before("logPointcut()")
	public void logBefore() {
		begin.set(new Date());
		logger.info("{} begin at {}", methodInfo.get(), begin.get());
	}

	@After("logPointcut()")
	public void logAfter() {
		end.set(new Date());
		logger.info("{} end at {}, used {} ms.", methodInfo.get(), end.get(),
				end.get().getTime() - begin.get().getTime());

	}

	@Around("logPointcut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		methodInfo.set(pjp.getSignature());
		Object retVal = pjp.proceed();
		return retVal;
	}

}
