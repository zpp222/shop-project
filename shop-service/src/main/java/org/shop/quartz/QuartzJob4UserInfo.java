package org.shop.quartz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJob4UserInfo extends QuartzJobBean {

	private Logger logger = LoggerFactory.getLogger(QuartzJob4UserInfo.class);

	private String targetMethod;

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	public void dealUserInfo() {
		logger.info("QuartzJob4UserInfo dealUserInfo...");
	}

	public void dealShopInfo() {
		logger.info("QuartzJob4UserInfo dealShopInfo...");
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Method method = this.getClass().getMethod(this.targetMethod, null);
			method.invoke(this, null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
