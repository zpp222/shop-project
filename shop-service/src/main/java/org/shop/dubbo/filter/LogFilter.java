package org.shop.dubbo.filter;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;

public class LogFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(LogFilter.class);

	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		try {
			MDC.put("ip", InetAddress.getLocalHost().getHostAddress());
		} catch (IllegalArgumentException e1) {
			logger.error("service name: {} , exception : {}", invocation.getMethodName(), e1);
		} catch (UnknownHostException e1) {
			logger.error("service name: {} , exception : {}", invocation.getMethodName(), e1);
		}

		logger.info("service name: {}", invocation.getMethodName());
		Result result = new RpcResult();

		try {
			result = invoker.invoke(invocation);
		} catch (Exception e) {
			logger.info("service {} , exception : {}", invocation.getMethodName(), e);
		}

		return result;
	}

}
