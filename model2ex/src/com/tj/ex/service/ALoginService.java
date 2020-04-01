package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.AdminDao;
import com.tj.ex.dto.AdminDto;


public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.aLogin(aId, aPw);
		if(result==AdminDao.SUCCESS) {
			HttpSession session = request.getSession();
			AdminDto dto = aDao.aGet(aId);
			session.setAttribute("admin", dto);
		}else {
			System.out.println(aId+"/비번"+aPw);
		}

	}

}
