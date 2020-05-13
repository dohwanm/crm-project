package com.spring.reply.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.reply.service.ReplyService;
import com.spring.reply.vo.ReplyVO;

/*
 * 참고 @RestController (@Controller + @ResponseBody)
 * 기존의 특정한 JSP와 같은 뷰를 만들어 내는 것이 목적이 아닌 REST 방식의 데이터 처리를
 * 위해서 사용하는 (데이터 자체를 반환) 어노테이션이다
 * */

@Controller
@RequestMapping("/replies")
public class ReplyController {
	Logger logger = Logger.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService replyService;
	
	/*
	 * 댓글 글목록 구현하기
	 * @return List<ReplyVO>
	 * 참고 : @PathVariable는 URl의 경로에서 원하는 데이터를 추출하는 용도로 사용
	 * ResponseEntity 타입은 개발자가 직접 결과 데이터와  HTTP의 상태 코드를
	 * 직접 제어할 수 있는 클래스이다 404sk 500같은 상태코드를 전송하고싶은 데이터와 함꼐
	 * 전송할 수 있기 떄문에 좀더 세밀한 제어가 가능
	 * */
	
	@ResponseBody
	@RequestMapping(value= "/all/{h_Num}.htk", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("h_Num") String h_Num){
		
		System.out.println("ReplyController.list()함수 시작");
		System.out.println("h_Num >> : "+ h_Num);
		ResponseEntity<List<ReplyVO>> entity = null;
		try{
			entity = new ResponseEntity<>(replyService.replyList(h_Num),HttpStatus.OK);
		}catch(Exception e){
			System.out.println("에러가 >> :"+ e.getMessage());
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("entity >> : "+ entity);
		System.out.println("ReplyController.list()함수 끝");
		
		return entity;
	}//end of list
	
	//댓글 글쓰기 구현
	@ResponseBody
	@RequestMapping(value="/replyInsert")
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo){
		System.out.println("ReplyController.replyInsert()함수 시작");
		System.out.println("값 >> : "+ rvo.getR_content() +":" +rvo.getR_name()+":"+rvo.getR_pwd());
		ResponseEntity<String> entity = null;
		int result = 0;
		
		String chaebun = replyService.replyChaebun();
		System.out.println("채번 값 >> : "+ chaebun);
		
		try{
			rvo.setR_num(chaebun);
			result = replyService.replyInsert(rvo);
			System.out.println("result >>: "+ result);
			if(result ==1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		}catch (Exception e){
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("ReplyController.replyInsert()함수 끝");
		return entity;
		
	}
	//댓글 수정 구현하기
	/*
	 * 참고:REST 방식에서 UPDATE 작업은 PUT,PATCH 방식을 이용해서 처리
	 * 전체 데이터를 수정하는 경우에는 PUT을 이용하고
	 * 일부의 데이터를 수정하는 경우에는 PATCH를 이용
	 * */
	
	@ResponseBody
	@RequestMapping(value="/{r_num}.htk",method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("r_num") String r_num,@RequestBody ReplyVO rvo){
		System.out.println("ReplyController.replyUpdate()함수 시작");
		
		ResponseEntity<String> entity = null;
		System.out.println("r_num >> : "+ r_num);	
		System.out.println("rvo 값 확인"+rvo.getR_num());
		try{
			rvo.setR_num(r_num);
			replyService.replyUpdate(rvo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e){
			System.out.println("에러가 >: " + e.getMessage());
			
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("ReplyController.replyUpdate()함수 끝");
		
		return entity;
	}
	
	
	//댓글 삭제 구현하기 
	
	@ResponseBody
	@RequestMapping(value="/{r_num}.htk",method= {RequestMethod.DELETE, RequestMethod.PATCH})
	public ResponseEntity<String> replyDelete(@PathVariable("r_num") String r_num){
		System.out.println("ReplyController.replyDelete()함수 시작");
		
		ResponseEntity<String> entity = null;
		System.out.println("r_num >> : "+ r_num);	
		try{

			replyService.replyDelete(r_num);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e){
			System.out.println("에러가 >: " + e.getMessage());
			
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("ReplyController.replyDelete()함수 끝");
		
		return entity;
	}
	
}
