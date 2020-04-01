package com.tj.ex.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.Member;
import com.tj.ex.service.Service;

public class JoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = MemberDao.getInstance();
		String id      = request.getParameter("id"     );
		String pw      = request.getParameter("pw"     );
		String name    = request.getParameter("name"   );
		Date   birth   = Date.valueOf(request.getParameter("birth"  ));
		String gender  = request.getParameter("gender" );
		String email   = request.getParameter("email"  );
		String phone   = request.getParameter("phone"  );
		String address = request.getParameter("address");
		Member dto = new Member(id, pw, name, birth, gender, email, phone, address);
		int result = 0;
		result = dao.join(dto);
		if(result==1) {
			request.setAttribute("joinResult", "회원가입이 완료되었습니다.");
		}else {
			request.setAttribute("joinResult", "회원가입이 실패되었습니다.");
		}
	}

}
