package com.oracle.tour.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.tour.dto.Contents;

@Repository
public class SHDaoImpl implements SHDao {
	private final SqlSession session;
	@Autowired
	public SHDaoImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	//전체 리스트 뽑기
	public List<Contents> getConList(Contents con) {
		List<Contents> conList = null;
		try {
			conList = session.selectList("SHGetConList",con);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getConList Error->"+e.getStackTrace());
		}
		return conList;
	}
	
	@Override
	// 관광지 리스트 뽑기
	public List<Contents> getTourList(Contents con) {
		List<Contents> tourList = null;
		try {
			tourList = session.selectList("SHTourList",con);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getTourList Error->"+e.getStackTrace());
		}
		return tourList;
	}

	@Override
	//숙소 리스트 뽑기
	public List<Contents> getHotelList(Contents con) {
		List<Contents> hotelList = null;
		try {
			hotelList = session.selectList("SHHotelList",con);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getHotelList Error->"+e.getStackTrace());
		}
		return hotelList;
	}

	@Override
	//음식점 리스트 뽑기
	public List<Contents> getFoodList(Contents con) {
		List<Contents> foodList = null;
		try {
			foodList = session.selectList("SHFoodList",con);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getHotelList Error->"+e.getStackTrace());
		}
		return foodList;
	}

	@Override
	public List<Contents> getLocalList(Contents con,String c_local) {
		List<Contents> localList = null;
		try {
			localList = session.selectList("SHLocalList",c_local);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getLocalList Error->"+e.getStackTrace());
		}
		return localList;
	}

	@Override
	public List<Contents> getLocalTourList(String c_local) {
		List<Contents> localTourList = null;
		try {
			localTourList = session.selectList("SHLocalTourList",c_local);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getLocalTourList Error->"+e.getStackTrace());
		}
		return localTourList;
	}

	@Override
	public List<Contents> getLocalHotelList(String c_local) {
		List<Contents> localHotelList = null;
		try {
			localHotelList = session.selectList("SHLocalHotelList",c_local);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getLocalHotelList Error->"+e.getStackTrace());
		}
		return localHotelList;
	}

	@Override
	public List<Contents> getLocalFoodList(String c_local) {
		List<Contents> localFoodList = null;
		try {
			localFoodList = session.selectList("SHLocalFoodList",c_local);
		} catch (Exception e) {
			System.out.println("SHDaoImpl getLocalFoodList Error->"+e.getStackTrace());
		}
		return localFoodList;
	}
	
}
