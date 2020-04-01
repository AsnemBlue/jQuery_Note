package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class ProductService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pname = request.getParameter("pname");
		ProductDao dao = ProductDao.getInstance();
		ProductDto product = dao.getProduct(pname);
		if(product==null){
			request.setAttribute("stockResult", "없는 물건입니다.");
		}else{
			request.setAttribute("stockResult", product.getPstock()+"개 재고");
		}
	}

}
