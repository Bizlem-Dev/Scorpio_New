package com.anagha;


import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest; 
import org.apache.sling.api.SlingHttpServletResponse;


@SlingServlet(paths = "/process/hello")
public class TestNotReading extends SlingAllMethodsServlet {
private static final long serialVersionUID = 1;

@Override
public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
response.getWriter().write("Hello Abhishek here");


}

}