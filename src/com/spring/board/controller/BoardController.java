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
	
	//글목록 구현하기
	@RequestMapping(value="/boardList", method= RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO bvo , Model model){
		logger.info("BoardController.boardList() 함수 시작");
		System.out.println("ㅎㅇ");
		
		//------------------------정렬
		//정렬에 대한 기본값 설정
		if(bvo.getOrder_by()==null) bvo.setOrder_by("h_Num");
		if(bvo.getOrder_sc()==null) bvo.setOrder_sc("DESC");
		
		//정렬에 대한 데이터 확인
		logger.info("oredr_by = "+ bvo.getOrder_by());
		logger.info("oredr_sc = "+ bvo.getOrder_sc());
		
		//검색에 대한 데이터 확인
		logger.info("search = " + bvo.getSearch());
		logger.info("keyword = " + bvo.getKeyword());
		//-----------------------------------------------
		
		//------------------------페이징
		
		//페이지 세팅
		Paging.setPage(bvo);
		
		//전체 레코드수 구현
		int total = boardService.boardListCnt(bvo);
		logger.info("total "+total);
		
		//글번호 재설정
		int count = total - (Util.nvl(bvo.getPage())-1) * Util.nvl(bvo.getPageSize());
		logger.info("count "+count);
		
		
		//--------------------------
		
		List<BoardVO> boardList = boardService.boardList(bvo);
		System.out.println("왔어욤");
		logger.info("boardList 다녀 왔져욤");
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("data", bvo);
		model.addAttribute("total", total);
		model.addAttribute("count", count);
		logger.info("BoardController.boardList() 함수 끝");
		return CONTEXT_PATH+"/boardList";
	}//end of boardList
	
	//글쓰기 폼 출력하기
	
	@RequestMapping(value="/writeForm" , method=RequestMethod.GET)
	public String writeForm(){
		logger.info("BoardController.writeForm() 함수 시작");
		
		logger.info("BoardController.writeForm() 함수 끝");
		return CONTEXT_PATH+"/writeForm";
	}//end of writeForm
	
	//글쓰기 구현하기 
	@RequestMapping(value="/boardInsert",method=RequestMethod.POST)
	public String boardInsert(@ModelAttribute BoardVO bvo, HttpServletRequest request) throws Exception{
		logger.info("BoardController.boardInsert()함수 시작");
		
		logger.info("fileName : "+ bvo.getFile().getOriginalFilename());
		logger.info("h_title : "+ bvo.getH_Title());
		
		int result = 0;
		String url = "";
		
		System.out.println("BoardVO1" + bvo.toString());
		
		String h_File = FileUploadUtil.fileUpload(bvo.getFile(), request);		
		bvo.setH_File(h_File);
		
		System.out.println("BoardVO2" + bvo.toString());		
		
		String chaebun = boardService.boardChaebun();
		logger.info("채번 " + chaebun);
		bvo.setH_Num(chaebun);
		
		System.out.println("BoardVO3" + bvo.toString());
		
		result = boardService.boardInsert(bvo);
		logger.info("result 다녀왔어욤");
		
		if(result == 1){
			url = "/board/boardList.htk";
		}
		
		logger.info("BoardController.boardInsert()함수 끝");
		return "redirect:"+url;
	}//end of boardInsert
	
	//글 상세보기 구현
	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
	public String boardDetail(@ModelAttribute BoardVO bvo , Model model){
		logger.info("BoardController.boardDetail()함수 시작");
		
		logger.info("h_Num = "+ bvo.getH_Num());
		
		BoardVO detail = new BoardVO();
		detail = boardService.boardDetail(bvo);
		logger.info("detail 다녀왔어욤");
		
		if(detail != null && (!detail.equals(""))){
			detail.setH_Content(detail.getH_Content().toString().replace("\n", "<br>"));
		}
		
		model.addAttribute("detail", detail);
		logger.info("BoardController.boardDetail()함수 끝");
		
		return CONTEXT_PATH+"/boardDetail";
	}//end of boardDetail
	
	/* 비밀번호 확인 
	 * @param h_Num 
	 * @param h_Pwd
	 * @return int
	 * 참고 @RequestBody는 전달된 뷰를 통해서 출력하는 것이 아니라 HTTP Response Body에 직접 출력하는 방식*/
	
	@ResponseBody
	@RequestMapping(value="/pwdConfirm" , method=RequestMethod.POST)
	public String pwdConfirm(@ModelAttribute BoardVO bvo){
		logger.info("BoardController.pwdConfirm()함수 시작");
		
		//아래 변수에는 입력 성공에 대한 상태값 저장 (1 or 0)
		int result = 0;
		result = boardService.pwdConfirm(bvo);
		logger.info("result 다녀왔어염");
		
		logger.info("result : "+ result);
		
		logger.info("BoardController.pwdConfirm()함수 끝");
		return result+"";
	}//end of pwdConfirm
	
	//글수정 폼 출력하기
	//@param : h_Num
	//@return : BoardVO
	
	@RequestMapping(value="/updateForm" , method=RequestMethod.POST)
	public String updateForm(@ModelAttribute BoardVO bvo , Model model){
		logger.info("BoardController.updateForm()함수 시작");
		
		logger.info("h_Num = "+bvo.getH_Num());
		
		BoardVO updateData = new BoardVO();
		
		updateData = boardService.boardDetail(bvo);
		logger.info("updateData 다녀왔어욤");
		
		model.addAttribute("updateData", updateData);
		
		logger.info("BoardController.updateForm()함수 끝");
		return CONTEXT_PATH+"/updateForm";
	}//end of updateForm
	
	//글수정 구현하기
	
	@RequestMapping(value="/boardUpdate" , method=RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO bvo, HttpServletRequest request) throws Exception{
		
		logger.info("BoardController.boardUpdate()함수 시작");
		
		int result = 0;
		String url ="";
		String h_File = "";
		
		if(!bvo.getFile().isEmpty()){
			logger.info("if문 진입");
			
			FileUploadUtil.fileDelete(bvo.getH_File(), request);
			h_File = FileUploadUtil.fileUpload(bvo.getFile(), request);
			bvo.setH_File(h_File);
		}else{
			logger.info("첨부파일 없음");
			bvo.setH_File("");
		}
		
		logger.info("h_File = "+ bvo.getH_File());
		result = boardService.boardUpdate(bvo);
		
		if(result == 1){
			url = "/board/boardList.htk"; //수정 후 목록으로 이동
			//아래 url은 수정 후 상세 페이지 이동
			//url="/board/boardDetail.htk?h_Num="bvo.getH_Num()";
			
		}
		
		logger.info("BoardController.boardUpdate()함수 끝");
		return "redirect:"+url;
	}//end of boardUpdate
	
	//글삭제 구현하기
	//@throws IOException
	
	@RequestMapping("/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo , HttpServletRequest request) throws Exception{
		logger.info("BoardController.boardDelete()함수 시작");
		
		//아래 변수에는 삭제 성공에 대한 상태값 담습니다 (1 or 0)
		int result = 0;
		String url = "";
		
		FileUploadUtil.fileDelete(bvo.getH_File(), request);
		result = boardService.boardDelete(bvo.getH_Num());
		if(result == 1){
			url = "/board/boardList.htk";
		}
		
		logger.info("BoardController.boardDelete()함수 끝");
		return "redirect:"+url;
	}
	
	
	

}
