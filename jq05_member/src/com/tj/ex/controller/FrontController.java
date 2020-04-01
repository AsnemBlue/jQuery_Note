package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.JoinService;
import com.tj.ex.service.Service;
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FrontController() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		
		if(com.equals("/idChk.do")) {
			service = new IdChkService();
			service.execute(request,response);
			viewPage="member/idChk.jsp";
		}
		if(com.equals("/emailChk.do")) {
			service = new EmailChkService();
			service.execute(request,response);
			viewPage="member/emailChk.jsp";
		}
		if(com.equals("/join.do")) {
			service = new JoinService();
			service.execute(request,response);
			viewPage="member/login.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
