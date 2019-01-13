package com.example.address.service;

import com.example.address.entity.AreaCity;
import com.example.address.entity.UserInfo;

public interface AddressService {
	
	public AreaCity addressResolution(UserInfo userInfo);

}
