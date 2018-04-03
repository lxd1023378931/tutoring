package com.uzak.tutoring.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.common.UZToken;

/**
 * 清除过期token定时器
 * 
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年4月3日
 */
@Component
public class DestoryTokenTask {
	@Autowired
	private IDaoUtil<UZToken> dao;

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void execute() {
		long time = System.currentTimeMillis();
		System.out.println("定时任务：清除过期token。开始执行！");
		dao.executeSql("delete from uztoken where expire_time < ?",time);
		System.out.println("定时任务：清除过期token。执行成功！");
	}
}
