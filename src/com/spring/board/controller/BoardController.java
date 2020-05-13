                         package com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVO;
import com.spring.board.common.file.FileUploadUtil;
import com.spring.board.common.page.Paging;
import com.spring.board.common.util.Util;

@Controller
@RequestMapping("/board")
public class BoardController {
	Logger logger = Logger.getLogger(BoardController.class);
	public static final String CONTEXT_PATH = "board"; 
	@Autowired
	private BoardService boardService;
	
	//�۸�� �����ϱ�
	@RequestMapping(value="/boardList", method= RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO bvo , Model model){
		logger.info("BoardController.boardList() �Լ� ����");
		System.out.println("����");
		
		//------------------------����
		//���Ŀ� ���� �⺻�� ����
		if(bvo.getOrder_by()==null) bvo.setOrder_by("h_Num");
		if(bvo.getOrder_sc()==null) bvo.setOrder_sc("DESC");
		
		//���Ŀ� ���� ������ Ȯ��
		logger.info("oredr_by = "+ bvo.getOrder_by());
		logger.info("oredr_sc = "+ bvo.getOrder_sc());
		
		//�˻��� ���� ������ Ȯ��
		logger.info("search = " + bvo.getSearch());
		logger.info("keyword = " + bvo.getKeyword());
		//-----------------------------------------------
		
		//------------------------����¡
		
		//������ ����
		Paging.setPage(bvo);
		
		//��ü ���ڵ�� ����
		int total = boardService.boardListCnt(bvo);
		logger.info("total "+total);
		
		//�۹�ȣ �缳��
		int count = total - (Util.nvl(bvo.getPage())-1) * Util.nvl(bvo.getPageSize());
		logger.info("count "+count);
		
		
		//--------------------------
		
		List<BoardVO> boardList = boardService.boardList(bvo);
		System.out.println("�Ծ��");
		logger.info("boardList �ٳ� ������");
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("data", bvo);
		model.addAttribute("total", total);
		model.addAttribute("count", count);
		logger.info("BoardController.boardList() �Լ� ��");
		return CONTEXT_PATH+"/boardList";
	}//end of boardList
	
	//�۾��� �� ����ϱ�
	
	@RequestMapping(value="/writeForm" , method=RequestMethod.GET)
	public String writeForm(){
		logger.info("BoardController.writeForm() �Լ� ����");
		
		logger.info("BoardController.writeForm() �Լ� ��");
		return CONTEXT_PATH+"/writeForm";
	}//end of writeForm
	
	//�۾��� �����ϱ� 
	@RequestMapping(value="/boardInsert",method=RequestMethod.POST)
	public String boardInsert(@ModelAttribute BoardVO bvo, HttpServletRequest request) throws Exception{
		logger.info("BoardController.boardInsert()�Լ� ����");
		
		logger.info("fileName : "+ bvo.getFile().getOriginalFilename());
		logger.info("h_title : "+ bvo.getH_Title());
		
		int result = 0;
		String url = "";
		
		System.out.println("BoardVO1" + bvo.toString());
		
		String h_File = FileUploadUtil.fileUpload(bvo.getFile(), request);		
		bvo.setH_File(h_File);
		
		System.out.println("BoardVO2" + bvo.toString());		
		
		String chaebun = boardService.boardChaebun();
		logger.info("ä�� " + chaebun);
		bvo.setH_Num(chaebun);
		
		System.out.println("BoardVO3" + bvo.toString());
		
		result = boardService.boardInsert(bvo);
		logger.info("result �ٳ�Ծ��");
		
		if(result == 1){
			url = "/board/boardList.htk";
		}
		
		logger.info("BoardController.boardInsert()�Լ� ��");
		return "redirect:"+url;
	}//end of boardInsert
	
	//�� �󼼺��� ����
	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
	public String boardDetail(@ModelAttribute BoardVO bvo , Model model){
		logger.info("BoardController.boardDetail()�Լ� ����");
		
		logger.info("h_Num = "+ bvo.getH_Num());
		
		BoardVO detail = new BoardVO();
		detail = boardService.boardDetail(bvo);
		logger.info("detail �ٳ�Ծ��");
		
		if(detail != null && (!detail.equals(""))){
			detail.setH_Content(detail.getH_Content().toString().replace("\n", "<br>"));
		}
		
		model.addAttribute("detail", detail);
		logger.info("BoardController.boardDetail()�Լ� ��");
		
		return CONTEXT_PATH+"/boardDetail";
	}//end of boardDetail
	
	/* ��й�ȣ Ȯ�� 
	 * @param h_Num 
	 * @param h_Pwd
	 * @return int
	 * ���� @RequestBody�� ���޵� �並 ���ؼ� ����ϴ� ���� �ƴ϶� HTTP Response Body�� ���� ����ϴ� ���*/
	
	@ResponseBody
	@RequestMapping(value="/pwdConfirm" , method=RequestMethod.POST)
	public String pwdConfirm(@ModelAttribute BoardVO bvo){
		logger.info("BoardController.pwdConfirm()�Լ� ����");
		
		//�Ʒ� �������� �Է� ������ ���� ���°� ���� (1 or 0)
		int result = 0;
		result = boardService.pwdConfirm(bvo);
		logger.info("result �ٳ�Ծ");
		
		logger.info("result : "+ result);
		
		logger.info("BoardController.pwdConfirm()�Լ� ��");
		return result+"";
	}//end of pwdConfirm
	
	//�ۼ��� �� ����ϱ�
	//@param : h_Num
	//@return : BoardVO
	
	@RequestMapping(value="/updateForm" , method=RequestMethod.POST)
	public String updateForm(@ModelAttribute BoardVO bvo , Model model){
		logger.info("BoardController.updateForm()�Լ� ����");
		
		logger.info("h_Num = "+bvo.getH_Num());
		
		BoardVO updateData = new BoardVO();
		
		updateData = boardService.boardDetail(bvo);
		logger.info("updateData �ٳ�Ծ��");
		
		model.addAttribute("updateData", updateData);
		
		logger.info("BoardController.updateForm()�Լ� ��");
		return CONTEXT_PATH+"/updateForm";
	}//end of updateForm
	
	//�ۼ��� �����ϱ�
	
	@RequestMapping(value="/boardUpdate" , method=RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO bvo, HttpServletRequest request) throws Exception{
		
		logger.info("BoardController.boardUpdate()�Լ� ����");
		
		int result = 0;
		String url ="";
		String h_File = "";
		
		if(!bvo.getFile().isEmpty()){
			logger.info("if�� ����");
			
			FileUploadUtil.fileDelete(bvo.getH_File(), request);
			h_File = FileUploadUtil.fileUpload(bvo.getFile(), request);
			bvo.setH_File(h_File);
		}else{
			logger.info("÷������ ����");
			bvo.setH_File("");
		}
		
		logger.info("h_File = "+ bvo.getH_File());
		result = boardService.boardUpdate(bvo);
		
		if(result == 1){
			url = "/board/boardList.htk"; //���� �� ������� �̵�
			//�Ʒ� url�� ���� �� �� ������ �̵�
			//url="/board/boardDetail.htk?h_Num="bvo.getH_Num()";
			
		}
		
		logger.info("BoardController.boardUpdate()�Լ� ��");
		return "redirect:"+url;
	}//end of boardUpdate
	
	//�ۻ��� �����ϱ�
	//@throws IOException
	
	@RequestMapping("/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo , HttpServletRequest request) throws Exception{
		logger.info("BoardController.boardDelete()�Լ� ����");
		
		//�Ʒ� �������� ���� ������ ���� ���°� ����ϴ� (1 or 0)
		int result = 0;
		String url = "";
		
		FileUploadUtil.fileDelete(bvo.getH_File(), request);
		result = boardService.boardDelete(bvo.getH_Num());
		if(result == 1){
			url = "/board/boardList.htk";
		}
		
		logger.info("BoardController.boardDelete()�Լ� ��");
		return "redirect:"+url;
	}
	
	
	

}
