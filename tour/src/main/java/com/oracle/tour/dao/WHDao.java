package com.oracle.tour.dao;

import com.oracle.tour.dto.Member;

public interface WHDao {

	Member login(Member member);
	void memberJoin(Member member);
	int idCheck(String m_id);
	Member myInfo(Member member);
	void pwChange(Member member);
	void memberFind(Member member);

}
