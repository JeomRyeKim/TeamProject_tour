package com.oracle.tour.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LJDaoImpl implements LJDao{
	private final SqlSession sqlSession;
	@Autowired
	public LJDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
}
