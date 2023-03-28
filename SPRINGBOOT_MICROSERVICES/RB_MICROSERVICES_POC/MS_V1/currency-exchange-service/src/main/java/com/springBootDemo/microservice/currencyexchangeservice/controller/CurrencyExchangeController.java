package com.springBootDemo.microservice.currencyexchangeservice.controller;

import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springBootDemo.microservice.currencyexchangeservice.entity.ExchangeValue;
import com.springBootDemo.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	Environment enviorenment;
	
	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;
	
	/*@GetMapping("/currency-exchange/{from}/to/{to}") --hardcode implementation without JPA
	public ExchangeValue sendCurrencyExchangeValue(@PathVariable("from") String from , @PathVariable("to") String to)
	{		
		return new ExchangeValue(1001 , "USD" , "INR" ,65);
	}*/
	
	@GetMapping("/currency-exchange/{from}/to/{to}")
	public ExchangeValue sendCurrencyExchangeValue(@PathVariable("from") String from , @PathVariable("to") String to)
	{
		ExchangeValue currencyExchangeValue = currencyExchangeRepository.findByFromAndTo(from, to);
		currencyExchangeValue.setPort(Integer.parseInt(enviorenment.getProperty("local.server.port")));
		
		logger.info("{} -> " , currencyExchangeValue);
		
		return currencyExchangeValue;
	}
	
	
}
