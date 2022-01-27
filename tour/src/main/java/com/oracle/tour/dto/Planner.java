package com.oracle.tour.dto;



import java.util.Date;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Planner {
	private int    p_no;			// 플랜 번호
	private String m_id;		    // 회원 번호
	private int    c_no;			// 컨텐츠 번호
	private String p_title;			// 플랜 제목
	//
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
	private Date   p_start_day;		// 플랜 여행 시작일
	//
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
	private Date   p_end_day;		// 플랜 여행 종료일
	//
	private String p_comment;		// 플랜 소개
	private Date   p_date;			// 플랜 작성일
	
	
	
	// SimpleDateFormat으로 Date타입 날짜는 따로 Set
	public void setFDate(String p_start_day) throws ParseException {
        SimpleDateFormat fm  = new SimpleDateFormat("yyyy-MM-dd");
        Date first_date = fm.parse(p_start_day);
        this.p_start_day = first_date;
    }

    public void setLDate(String p_end_day) throws ParseException {
        SimpleDateFormat fm  = new SimpleDateFormat("yyyy-MM-dd");
        Date last_date = fm.parse(p_end_day);
        this.p_end_day = last_date;
    }

    public void setWDate(String p_date) throws ParseException {
        SimpleDateFormat fm  = new SimpleDateFormat("yyyy-MM-dd");
        Date write_date = fm.parse(p_date);
        this.p_date = write_date;
    }
	
	
}
