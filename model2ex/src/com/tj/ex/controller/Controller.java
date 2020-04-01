package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.ALoginService;
import com.tj.ex.service.BContentService;
import com.tj.ex.service.BDeleteService;
import com.tj.ex.service.BListService;
import com.tj.ex.service.BModifyService;
import com.tj.ex.service.BReplyService;
import com.tj.ex.service.BWriteService;
import com.tj.ex.service.MAllViewService;
import com.tj.ex.service.MJoinService;
import com.tj.ex.service.MLoginService;
import com.tj.ex.service.MLogoutService;
import com.tj.ex.service.MModifyService;
import com.tj.ex.service.Service;
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Controller() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri 		= request.getRequestURI();	
		String conPath 	= request.getContextPath();	
		String command	= uri.substring(conPath.length());	
		String viewPage = null;
		Service service = null;
		
		if(command.equals("/main.do")) {
			viewPage = "main/main.jsp";
		}
		if(command.equals("/joinView.do")) {
			viewPage = "member/join.jsp";
		}
		if(command.equals("/join.do")) {
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "member/login.jsp";
		}
		if(command.equals("/loginView.do")) {
			viewPage = "member/login.jsp";
		}
		if(command.equals("/login.do")) {
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		if(command.equals("/modifyView.do")) {
			viewPage = "member/modify.jsp";
		}
		if(command.equals("/modify.do")) {
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		if(command.equals("/logout.do")) {
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		if(command.equals("/adminloginView.do")) {
			viewPage = "admin/aLogin.jsp";
		}
		if(command.equals("/alogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "mAllView.do";
		}
		if(command.equals("/mAllView.do")) {
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}
		if(command.equals("/list.do")) {
			service = new BListService();
			service.execute(request, response);
			viewPage = "board/list.jsp";
		}
		if(command.equals("/content_view.do")) {
			service = new BContentService();
			service.execute(request, response);
			viewPage = "board/content_view.jsp";
		}
		if(command.equals("/bWrite_view.do")) {
			viewPage = "board/write_view.jsp";
		}
		if(command.equals("/bWrite.do")) {
			service = new BWriteService();
			service.execute(request, response);
			viewPage = "list.do";
		}
		if(command.equals("/modify_view.do")) {
			viewPage = "board/modify_view.jsp";
		}
		if(command.equals("/bModify.do")) {
			service = new BModifyService();
			service.execute(request, response);
			viewPage = "list.do";
		}
		if(command.equals("/bdelete.do")) {
			service = new BDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
		}
		if(command.equals("/reply_view.do")) {
			viewPage = "board/reply_view.jsp";
		}
		if(command.equals("/bReply.do")) {
			service = new BReplyService();
			service.execute(request, response);
			viewPage = "list.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
