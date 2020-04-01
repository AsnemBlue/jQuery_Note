package com.tj.ex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.BoardDao;

public class BModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("board_file");
		int maxSize = 1024*1024*5;
		String fFileName = "";
		try {
			request.setCharacterEncoding("utf-8");
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fFileName = mRequest.getFilesystemName(param);
			fFileName = fFileName==null?"" :fFileName;
			BoardDao bDao = BoardDao.getInstance();
			
			String fTitle 	= mRequest.getParameter("fTitle");
			String fContent = mRequest.getParameter("fContent");
			String fIp		= request.getRemoteAddr();
			int	   fId 		= Integer.parseInt(mRequest.getParameter("fId"));
			
			request.setAttribute("modifyResult", bDao.bUpdate(fId, fTitle, fContent, fFileName, fIp));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//업로드된 파일을 소스폴더로 복사
		File serverFile = new File(path+"/"+fFileName);
		if(!fFileName.equals("") && serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/7_jQuery/model2ex/WebContent/board_file/"+fFileName);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int readbyteCnt = is.read(bs);
					if(readbyteCnt == -1) break;
					os.write(bs,0,readbyteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (IOException e) {}
			}
		}
	}

}
