package com.tj.ex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.service.Service;

public class EmailChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.emailChk(email);
		if(result==0) {
			request.setAttribute("emailChkMsg", "사용가능한 email입니다.");
		}else {
			request.setAttribute("emailChkMsg", "이미 존재하는 email입니다.");
		}

	}

}
