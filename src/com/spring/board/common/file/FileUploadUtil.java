package com.spring.board.common.file;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
public class FileUploadUtil {
	public static Logger logger = Logger.getLogger(FileUploadUtil.class);
	
	/*파일 업로드 메소드*/
	
	public static String fileUpload(MultipartFile file, HttpServletRequest request) throws Exception{
		logger.info("FileUploadUtil.fileUpload()함수 시작");
		logger.info("파일 업로드 호출 성공");
		String real_name = null;
		
		//MultipartFile  클래스의 getFile() 메서드로 클라이언트가 업로드한 파일명
		String org_name= file.getOriginalFilename();
		logger.info("org_name : " + org_name);
		
		//파일명 변경 (중복되지 않게)
		if(org_name != null && (!org_name.equals(""))){
			
			System.out.println("중복방지 if 들어옴");
			real_name="board_" + System.currentTimeMillis() + "_" + org_name;
			logger.info("real_name > : "+ real_name);
			
			String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage");
			logger.info("docRoot > : "+ docRoot);
			
			File fileDir = new File(docRoot);
			
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			
			File fileAdd = new File(docRoot+"/"+real_name);
			logger.info(fileAdd);
			
			file.transferTo(fileAdd);

		}
		
		
		logger.info("FileUploadUtil.fileUpload()함수 끝");
		return real_name;
	}//end of fileUpload
	
	// 파일 삭제 메서드
	public static void fileDelete(String fileName, HttpServletRequest request) throws Exception{
		logger.info("FileUploadUtil.fileDelete()함수 시작");
		
		logger.info("fileDelete 호출 성공");
		
		boolean result = false;
		String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage");
		
		File fileDelete = new File(docRoot+"/"+fileName);
		logger.info(fileDelete);
		
		if(fileDelete.exists() && fileDelete.isFile()){
			logger.info("삭제 if문 진입");
			result = fileDelete.delete();
		}
		
		logger.info(result);
		logger.info("FileUploadUtil.fileDelete()함수 끝");
	}//end of fileDelete

}
