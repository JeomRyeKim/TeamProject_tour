package com.oracle.tour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.SHDao;
import com.oracle.tour.dto.Contents;

@Service
@Transactional
public class SHServiceImpl implements SHService {
	private final SHDao shDao;
	@Autowired
	public SHServiceImpl(SHDao shDao) {
		this.shDao = shDao;
	}
	@Override
	public List<Contents> getConList(Contents con) {
		List<Contents> conList = null;
		System.out.println("SHServiceImpl getConList Start..");
		conList = shDao.getConList(con);
		return conList;
	}
	@Override
	public List<Contents> getTourList(Contents con) {
		List<Contents> tourList = null;
		System.out.println("SHServiceImpl getTourList Start..");
		tourList =shDao.getTourList(con);
		return tourList;
	}
	@Override
	public List<Contents> getHotelList(Contents con) {
		List<Contents> hotelList = null;
		System.out.println("SHServiceImpl getHotelList Start..");
		hotelList =shDao.getHotelList(con);
		return hotelList;
	}
	@Override
	public List<Contents> getFoodList(Contents con) {
		List<Contents> foodList = null;
		System.out.println("SHServiceImpl getFoodList Start..");
		foodList =shDao.getFoodList(con);
		return foodList;
	}
	@Override
	public List<Contents> getlocalTourList(String c_local) {
		List<Contents> localTourList = null;
		System.out.println("SHServiceImpl getlocalList Start..");
		localTourList = shDao.getLocalTourList(c_local);
		return localTourList;
	}
	@Override
	public List<Contents> getlocalHotelList(String c_local) {
		List<Contents> localHotelList = null;
		System.out.println("SHServiceImpl getlocalList Start..");
		localHotelList = shDao.getLocalHotelList(c_local);
		return localHotelList;
	}
	@Override
	public List<Contents> getlocalFoodList(String c_local) {
		List<Contents> localFoodList = null;
		System.out.println("SHServiceImpl getlocalList Start..");
		localFoodList = shDao.getLocalFoodList(c_local);
		return localFoodList;
	}
	

}
