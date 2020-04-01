package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.mLogin(mId, mPw);
		if(result==MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			MemberDto dto = mDao.mGet(mId);
			session.setAttribute("member", dto);
		}else {
			request.setAttribute("errorMsg", "아이디와 비번을 확인하세요");
		}

	}

}
