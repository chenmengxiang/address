package com.example.address.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.address.entity.AreaInfo;
import com.example.address.service.impl.AddressServiceImpl;


//@Component
public class InitAreaDate implements CommandLineRunner {
	
	 private static Logger log = LoggerFactory.getLogger(InitAreaDate.class);

	@Override
	public void run(String... args) throws Exception {
		
		AreaInfo areaInfo1 = new AreaInfo();
		
		areaInfo1.setId("1");
		
		areaInfo1.setName("北京");
		
		AreaInfo areaInfo2 = new AreaInfo();
		
		areaInfo2.setId("2");
		
		areaInfo2.setName("上海");
		
		AddressServiceImpl.provinces.add(areaInfo1);
		
		AddressServiceImpl.provinces.add(areaInfo2);
		
		
		
		log.info("InitAreaDate end...........");
		
		
	}

}
