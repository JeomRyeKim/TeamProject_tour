package com.oracle.tour.service;

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
	public Member login(Member member) {
		System.out.println("WHServiceImpl login start..");
		return member=whDao.login(member);
	}
	@Override
	public void memberJoin(Member member) {
		System.out.println("WHServiceImpl memberJoin start...");
		whDao.memberJoin(member);
		
	}
	@Override
	public int idCheck(String m_id) {
		System.out.println("WHServiceImpl idCheck start...");
		int result=0;
		result=whDao.idCheck(m_id);
		return result;
	}
	@Override
	public Member myInfo(Member member) {
		System.out.println("WHServiceImpl myInfo start..");
		member=whDao.myInfo(member);
		return member;
	}
	@Override
	public void pwChange(Member member) {
		System.out.println("WHServiceImpl pwChange start... ");
		whDao.pwChange(member);
	}

	
}
