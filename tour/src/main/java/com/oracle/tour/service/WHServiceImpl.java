package com.oracle.tour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.WHDao;
import com.oracle.tour.dto.Member;

@Service
@Transactional
public class WHServiceImpl implements WHService {
	private final WHDao whDao;
	@Autowired
	public WHServiceImpl(WHDao whDao) {
		this.whDao = whDao;
	}
	@Override
	public int login(Member member) {
		System.out.println("WHServiceImpl login start..");
		int result;
		return result=whDao.login(member);
	}
	
//	@Override
//	public List<Member> getMemberList(String m_id) {
//		List<Member> memberList = whDao.getMemberList(m_id);
//		
//		return memberList;
//	}

	
}
