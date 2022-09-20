package com.chainsys.miniproject.commonutils;

public class HTMLHelper {
	public static String getHTMLTemplate(String title, String message) {
		String htmlOutput = "<html><head><title>" + title + "</title></head><body>";
		String body = message;
		htmlOutput  +="<div>"+body +"</div></body></html>";
		return htmlOutput;
	}
}
