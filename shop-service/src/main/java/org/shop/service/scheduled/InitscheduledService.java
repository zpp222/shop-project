package org.shop.service.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service("org.shop.service.scheduled")
public class InitscheduledService {

	private Logger logger = LoggerFactory.getLogger(InitscheduledService.class);

	@Scheduled(cron = "0 */30 * * * ?")
	public void schedulingSome() {
		logger.info("schedulingSome ...");
	}

}
