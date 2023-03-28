package com.springbootDemo.microservices.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootDemo.microservices.limitservice.config.ConfigurationLimtService;
import com.springbootDemo.microservices.limitservice.entity.LimitEntity;

@RestController
@RequestMapping("/api")
public class LimitController {
	
	@Autowired
	private ConfigurationLimtService configurationLimtService;

	@GetMapping("/limits-hardcode")
	public LimitEntity shareLimits()
	{
		return new LimitEntity(1,999);
	}
	
	@GetMapping("/limits-frm-config")
	public LimitEntity sendLimits()
	{
		return new LimitEntity(configurationLimtService.getMinimum() , configurationLimtService.getMaximum());
	}
	
}
