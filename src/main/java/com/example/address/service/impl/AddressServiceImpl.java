package com.example.address.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.example.address.entity.AreaCity;
import com.example.address.entity.AreaInfo;
import com.example.address.entity.UserInfo;
import com.example.address.service.AddressService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Component
public class AddressServiceImpl implements AddressService {

	/**
	 * 省级信息缓存
	 */
	public static List<AreaInfo> provinces = new ArrayList<AreaInfo>();
	
	/**
	 * 市级信息缓存
	 */
	public static List<AreaInfo> cities = new ArrayList<AreaInfo>();
	
	/**
	 * 区级级信息缓存
	 */
	public static List<AreaInfo> districts = new ArrayList<AreaInfo>();
	
	private static Cache<String, List<AreaInfo>> cacheFormCallable = AddressServiceImpl.callableCached();
	
	
	private static <K, V> Cache<K, V> callableCached() {
		
		Cache<K, V> cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(1, TimeUnit.DAYS).build();
		
		return cache;

	}
	
	private List<AreaInfo> initaa(){
		
		List<AreaInfo> provinces = new ArrayList<AreaInfo>();
		
		AreaInfo areaInfo1 = new AreaInfo();
		
		areaInfo1.setId("1");
		
		areaInfo1.setName("北京");
		
		AreaInfo areaInfo2 = new AreaInfo();
		
		areaInfo2.setId("2");
		
		areaInfo2.setName("上海");
		
		provinces.add(areaInfo1);
		
		provinces.add(areaInfo2);
		
		return provinces;
		
	}
	
	
	private List<AreaInfo> findProvinces() throws ExecutionException {
		
		String type = "provinces";
		
		return cacheFormCallable.get(type, new Callable<List<AreaInfo>>() {
			@Override
			public List<AreaInfo> call() throws Exception {
				return initaa();
			}

		});
		
	}
	

	@Override
	public AreaCity addressResolution(UserInfo userInfo) {
		
		if(valDateError(userInfo)) {
			
			return null;
			
		}
		
		String homeAddress = userInfo.getHomeAddress1().replaceAll("\\s", "");
		
		AreaCity areaCity = new AreaCity();
	
		
		try {	
			
			List aa = findProvinces();
			
			List bb = findProvinces();
			
			findProvinces().forEach(p->{
				
				if(homeAddress.contains(p.getName())) {
					
					areaCity.setProvince(p.getName());
					
					return;
					
				}
				
			});
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return areaCity;
		
	}
	
	private boolean valDateError(UserInfo userInfo) {
		
		if(userInfo == null) {
			
			return true;
			
		}
		
		if(StringUtils.isEmpty(userInfo.getHomeAddress1())) {
			
			return true;
			
		}
		
		return false;
		
	}

	

}
