package com.springDemo.microservice.currencyconversionservice.FeignProxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springDemo.microservice.currencyconversionservice.entity.CurrencyConversionBean;

//@FeignClient(name="currency-exchange-service" , url = "localhost:8000") --without Ribbon
//below is with Ribbon LB
//@FeignClient(name="currency-exchange-service") //currency-conversion-service connects with currency-exchange-service
@FeignClient(name="netflix-zuul-apigateway-server") //with api gateway zuul
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	//@GetMapping("/currency-exchange/{from}/to/{to}") //without api gateway-zuul
	@GetMapping("/currency-exchange-service/currency-exchange/{from}/to/{to}") //with api gateway zuul --http:8765/{appname}/uri
	public CurrencyConversionBean sendCurrencyExchangeValue(@PathVariable("from") String from , @PathVariable("to") String to);
	
	}


