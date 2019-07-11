package com.fis.drzewiecki.wojciech.lbd.httpsessionlistener;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// logs info about session creation and termination
@WebListener
public class HttpSessionLogger implements HttpSessionListener {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
		logger.info("Session creation date: " + LocalDateTime.now());
		logger.info("Session id: " + se.getSession().getId());
		logger.info("Accepted language: " + se.getSession().getAttribute("Accept-Language"));
		
		HttpSessionListener.super.sessionCreated(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		logger.info("Session deleted.");
		logger.info("Session deletion date: " + LocalDateTime.now());
		logger.info("Session id: " + se.getSession().getId());
		logger.info("Accepted language: " + se.getSession().getAttribute("Accept-Language"));
		
		HttpSessionListener.super.sessionDestroyed(se);
	}

}
