package com.site.framework;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartCheckChargeListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(StartCheckChargeListener.class);
	
	public StartCheckChargeListener() {
		super();
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent e) {
		logger.info("-------------StartAutoStopListener.init-------------");
//		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(e.getServletContext());
		//CheckChargeTimer checkChargeTimer = (CheckChargeTimer) ac.getBean("CheckChargeTimer");
		//checkChargeTimer.execute();
	}
}
