package com.springDemo.microservice.currencyconversionservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springDemo.microservice.currencyconversionservice.FeignProxy.CurrencyExchangeServiceProxy;
import com.springDemo.microservice.currencyconversionservice.entity.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {
	
	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable("from") String from , @PathVariable("to") String to , @PathVariable("quantity") int quantity)
	{
		Map<String , String> uriVariables = new HashMap();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		
		ResponseEntity<CurrencyConversionBean> responseBody = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/{from}/to/{to}",
				CurrencyConversionBean.class, uriVariables);
		
		CurrencyConversionBean body = responseBody.getBody();
		
		return new CurrencyConversionBean(body.getId() ,from , to , body.getConversionMultiple() ,
				quantity ,quantity*body.getConversionMultiple() , body.getPort());
		
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable("from") String from , @PathVariable("to") String to 
			, @PathVariable("quantity") int quantity)
	{
		CurrencyConversionBean body = currencyExchangeServiceProxy.sendCurrencyExchangeValue(from, to);
		
		logger.info("{} -> " , body );
		
		return new CurrencyConversionBean(body.getId() ,from , to , body.getConversionMultiple() ,
				quantity ,quantity*body.getConversionMultiple() , body.getPort());
		
	}
	

}
