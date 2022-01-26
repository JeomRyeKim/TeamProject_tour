package com.oracle.tour.dao;

import java.util.List;

import com.oracle.tour.dto.Contents;

public interface SHDao {

	List<Contents> getConList(Contents con);

	List<Contents> getTourList(Contents con);

	List<Contents> getHotelList(Contents con);

	List<Contents> getFoodList(Contents con);

	List<Contents> getLocalList(Contents con, String c_local);

	List<Contents> getLocalTourList(String c_local);

	List<Contents> getLocalHotelList(String c_local);

	List<Contents> getLocalFoodList(String c_local);

}
