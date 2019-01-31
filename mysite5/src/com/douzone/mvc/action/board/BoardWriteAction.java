package com.douzone.mvc.action.board;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authuser");
		if(authUser ==null)
		{
			WebUtils.forward(request, response, request.getContextPath());
			return;
		}
//		String title = request.getParameter("title");
//		String contents = request.getParameter("content");

		
		//파일업로드
		//String uploads="/uploads/";
		//String uploads=request.getContextPath()+"/uploads/";
		String uploads=request.getRealPath("uploads");
		  String fileName=null;
		  String saveFileName=null;
	



		MultipartRequest multi=new MultipartRequest(request,uploads,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
		 Enumeration params=multi.getFileNames();
		 String formName=(String)params.nextElement(); // 자료가 많을 경우엔 while 문을 사용
         fileName=multi.getFilesystemName(formName); 
         String oriFile = multi.getOriginalFileName(formName);
         BoardVo boardVo = new BoardVo();
         if(fileName!=null)
         {
 	    String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());  //현재시간
//	    int i = -1;
//	          i = fileName.lastIndexOf("/uploads"); // 파일 확장자 위치
	          String realFileName = authUser.getNo()+"_"+now+"_"+ fileName;  //게시글번호+시간+파일이름

	   // System.out.println("기존:"+uploads + fileName+", 변경 후:"+uploads + realFileName);
	    
	    String uploadFileName=uploads+"/"+realFileName.replaceAll(" ","");
	    File oldFile = new File(uploads+"/"+ fileName);
	    File newFile = new File(uploadFileName);
	   // System.out.println(uploadFileName);
	    oldFile.renameTo(newFile); // 파일명 변경
	  
	    saveFileName=realFileName.replaceAll(" ","");
         }
         
         String title = multi.getParameter("title");
         String contents = multi.getParameter("content");
 		boardVo.setTitle(title);
 		boardVo.setContents(contents);
 		  boardVo.setFileName(saveFileName);
 		new BoardDao().write(boardVo, authUser.getNo());
 		WebUtils.redirect(request, response, request.getContextPath() + "/board");
	}
	

}
