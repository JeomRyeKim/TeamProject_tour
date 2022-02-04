package com.oracle.tour.service;

import com.oracle.tour.dto.Member;

public interface WHService {
	Member login(Member member);
	void memberJoin(Member member);
	int idCheck(String m_id);
	Member myInfo(Member member);
	void pwChange(Member member);
}
