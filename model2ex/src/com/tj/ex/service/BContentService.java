package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.BoardDao;

public class BContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		BoardDao bDao = BoardDao.getInstance();
		request.setAttribute("content_view", bDao.bGetDto(fId));

	}

}
