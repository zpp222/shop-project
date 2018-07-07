package org.shop.service.asy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service("org.shop.service.asy.ShopAsyJob")
public class ShopAsyJob implements Runnable {

	private Logger logger = LoggerFactory.getLogger(ShopAsyJob.class);

	private Object data;

	public void setData(Object data) {
		this.data = data;
	}

	public void run() {
		logger.info("异步处理 {}", data);
	}

}
