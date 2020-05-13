package com.spring.board.common.file;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
public class FileUploadUtil {
	public static Logger logger = Logger.getLogger(FileUploadUtil.class);
	
	/*���� ���ε� �޼ҵ�*/
	
	public static String fileUpload(MultipartFile file, HttpServletRequest request) throws Exception{
		logger.info("FileUploadUtil.fileUpload()�Լ� ����");
		logger.info("���� ���ε� ȣ�� ����");
		String real_name = null;
		
		//MultipartFile  Ŭ������ getFile() �޼���� Ŭ���̾�Ʈ�� ���ε��� ���ϸ�
		String org_name= file.getOriginalFilename();
		logger.info("org_name : " + org_name);
		
		//���ϸ� ���� (�ߺ����� �ʰ�)
		if(org_name != null && (!org_name.equals(""))){
			
			System.out.println("�ߺ����� if ����");
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
		
		
		logger.info("FileUploadUtil.fileUpload()�Լ� ��");
		return real_name;
	}//end of fileUpload
	
	// ���� ���� �޼���
	public static void fileDelete(String fileName, HttpServletRequest request) throws Exception{
		logger.info("FileUploadUtil.fileDelete()�Լ� ����");
		
		logger.info("fileDelete ȣ�� ����");
		
		boolean result = false;
		String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage");
		
		File fileDelete = new File(docRoot+"/"+fileName);
		logger.info(fileDelete);
		
		if(fileDelete.exists() && fileDelete.isFile()){
			logger.info("���� if�� ����");
			result = fileDelete.delete();
		}
		
		logger.info(result);
		logger.info("FileUploadUtil.fileDelete()�Լ� ��");
	}//end of fileDelete

}
