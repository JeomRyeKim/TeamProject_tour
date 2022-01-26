package com.oracle.tour.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oracle.tour.service.LJService;

@Controller
public class LJController {
	private static final Logger logger = LoggerFactory.getLogger(LJController.class);
	private final LJService ljService;
	@Autowired
	public LJController(LJService ljService) {
		super();
		this.ljService = ljService;
	}

	
}
