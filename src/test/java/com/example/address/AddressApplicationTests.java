package com.example.address;

import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.address.entity.UserInfo;
import com.example.address.service.AddressService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressApplicationTests {
	
	@Autowired
	private AddressService addressService;

	@Test
	public void contextLoads() {
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setHomeAddress1("上海市虹口区男11");
		
		System.out.println("ss");
		
		String aa = "11";
		String bb ="aa";
		String cc = "221";
		cc = "22234";
		
		System.out.println(addressService.addressResolution(userInfo));
		
		
	}

}

