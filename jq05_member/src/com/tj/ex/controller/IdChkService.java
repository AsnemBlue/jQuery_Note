package com.tj.ex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.service.Service;

public class IdChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.idChk(id);
		if(result==0) {
			request.setAttribute("idChkMsg", "사용가능한 ID입니다.");
		}else {
			request.setAttribute("idChkMsg", "이미 존재하는 ID입니다.");
		}
	}

}
