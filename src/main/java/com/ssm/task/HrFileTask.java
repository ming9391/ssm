package com.ssm.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ssm.service.HrFileService;

@Component
@Configurable
@EnableScheduling
public class HrFileTask {

	@Autowired
	private HrFileService hrFileService;
	
	@Scheduled(cron = "0/10 * * * * ?")
    public void hrFileTk() throws Exception {
		hrFileService.hrFile();
	}
}
