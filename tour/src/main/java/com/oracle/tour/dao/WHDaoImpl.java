package com.oracle.tour.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.tour.dto.Member;

@Repository
public class WHDaoImpl implements WHDao {
	private final SqlSession sqlSession;
	@Autowired
	public WHDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public int login(Member member) {
		System.out.println("WHODaolmpl login start...");
		int result = sqlSession.selectOne("WHLogin",member);
		return result;
	}
//	@Override
//	public List<Member> getMemberList(String m_id) {
//		List<Member> memberList = sqlSession.selectList("whMemberSelect", m_id);
//		
//		return memberList;
//	}
	
}
