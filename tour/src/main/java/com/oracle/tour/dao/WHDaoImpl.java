package com.oracle.tour.dao;

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
	
}