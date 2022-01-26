package com.oracle.tour.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planner_Day {
	private String m_id;		// 회원 번호
	private int plan_no;
	private int p_day;
	private String p_title;
	private String p_start_day;
	private String p_content;
	private int p_map_x;
	private int p_map_y;
	
}
