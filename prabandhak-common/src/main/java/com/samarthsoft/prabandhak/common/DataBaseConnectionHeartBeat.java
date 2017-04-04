package com.samarthsoft.prabandhak.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.samarthsoft.prabandhak.connector.DBCommunicator;

@Service
public class DataBaseConnectionHeartBeat {

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void connectionHeartBeat() {
		try {
			DBCommunicator.getApiServices().getDataBaseHeartBeatApi().beatDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}