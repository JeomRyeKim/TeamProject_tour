package com.oracle.tour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.LJDao;

@Service
@Transactional
public class LJServiceImpl implements LJService {
	private final LJDao ljDao;
	@Autowired
	public LJServiceImpl(LJDao ljDao) {
		this.ljDao = ljDao;
	}
	
	
}
