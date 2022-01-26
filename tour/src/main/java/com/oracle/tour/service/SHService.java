package com.oracle.tour.service;

import java.util.List;

import com.oracle.tour.dto.Contents;

public interface SHService {

	List<Contents> getConList(Contents con);

	List<Contents> getTourList(Contents con);

	List<Contents> getHotelList(Contents con);

	List<Contents> getFoodList(Contents con);

	List<Contents> getlocalTourList(String c_local);

	List<Contents> getlocalHotelList(String c_local);

	List<Contents> getlocalFoodList(String c_local);

}
