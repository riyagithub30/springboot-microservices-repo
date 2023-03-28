package com.springDemo.micriservice.netflixzuulapigatewayserver.loggingFilter;

import javax.servlet.http.HttpServletRequest;

import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ZuulLoggingFilter extends ZuulFilter{
	
	//logger
	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public boolean shouldFilter() {
		// should need a filter or not , initiallay set to false
		return true;
	}
	
	@Override
	public String filterType() {
		// pre = every service call should be at first go through via api-gateway
		//post , error
		return "pre";
	}

	public Object run() throws ZuulException {
		// run() method should contains the main business logic
		
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		
		logger.info("request {} -> request uri {}" , request , request.getRequestURI());
		
		
		return null;
	}
	
	@Override
	public int filterOrder() {
		// set filter order to 1, if we have multiple filters i.e. security filter , logging filter etc, then we can set filter according to that
		return 1;
	}

	

}
