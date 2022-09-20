package com.chainsys.webapp.second;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SeesionDemo
 */
public class SessionDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionDemo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Service");
		/*
		 * String methodName = request.getMethod(); System.out.println(methodName); if
		 * (methodName.equals("POST")) { doPost(request, response); } else {
		 * doGet(request, response); }
		 */

		HttpSession currentSession = request.getSession(true);
		int defaultTimeOut = currentSession.getMaxInactiveInterval(); // default time out period in seconds
		System.out.println(" Default time out  period in seconds : " + defaultTimeOut +" secs");
	/*	int defaultTimeOutmin = defaultTimeOut/60; // default time out period in minutes
		System.out.println(" Default time out  period in minutes : " + defaultTimeOutmin +" mins");
		currentSession.setMaxInactiveInterval(0);// no timeout period if value is zero or less than zero. it is infinite
		currentSession.setMaxInactiveInterval(5*60);// no timeout period if value is zero or less than zero. it is infinite
		defaultTimeOut = currentSession.getMaxInactiveInterval();
		System.out.println(" time out period: " + defaultTimeOut +" secs"); // this is per user ie per session id 
		*/
		
	}
}
