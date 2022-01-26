package com.oracle.tour.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.tour.dto.Contents;
import com.oracle.tour.service.SHService;

@Controller
public class SHController {
	private static final Logger logger = LoggerFactory.getLogger(SHController.class);
	private final SHService shS;
	@Autowired
	public SHController(SHService shS) {
		this.shS = shS;
	}

	@RequestMapping("/")
	public String goIndex(Contents con,Model model) {
		System.out.println("controller index Start");
		//관광지 리스트 뽑기
		List<Contents> tourList = shS.getTourList(con);
		System.out.println("SHController tourList.size->"+tourList.size());
		model.addAttribute("tourList",tourList);
		//숙소 리스트 뽑기
		List<Contents> hotelList = shS.getHotelList(con);
		System.out.println("SHController hotelList.size->"+hotelList.size());
		model.addAttribute("hotelList",hotelList);
		//음식점 리스트 뽑기
		List<Contents> foodList = shS.getFoodList(con);
		System.out.println("SHController foodList.size->"+foodList.size());
		model.addAttribute("foodList",foodList);

		return "index";
	}
	
	@RequestMapping("/local")
	public String goIocal(Model model, String c_local ) {
		System.out.println("controller local Start");
		//지역 리스트 뽑기
		List<Contents> localTourList = shS.getlocalTourList(c_local);
		System.out.println("SHController localTourList.size->"+localTourList.size());
		model.addAttribute("localTourList",localTourList);
		
		List<Contents> localHotelList = shS.getlocalHotelList(c_local);
		System.out.println("SHController localHotelList.size->"+localHotelList.size());
		model.addAttribute("localHotelList",localHotelList);
		
		List<Contents> localFoodList = shS.getlocalFoodList(c_local);
		System.out.println("SHController localFoodList.size->"+localFoodList.size());
		model.addAttribute("localFoodList",localFoodList);
		
		return "SHview/local";
	}
	
	@RequestMapping("/detail")
	public String godetail() {
		System.out.println("controller detail Start");
		return "SHview/detail";
	}
	
	
	
}
