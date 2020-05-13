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
 * ���� @RestController (@Controller + @ResponseBody)
 * ������ Ư���� JSP�� ���� �並 ����� ���� ���� ������ �ƴ� REST ����� ������ ó����
 * ���ؼ� ����ϴ� (������ ��ü�� ��ȯ) ������̼��̴�
 * */

@Controller
@RequestMapping("/replies")
public class ReplyController {
	Logger logger = Logger.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService replyService;
	
	/*
	 * ��� �۸�� �����ϱ�
	 * @return List<ReplyVO>
	 * ���� : @PathVariable�� URl�� ��ο��� ���ϴ� �����͸� �����ϴ� �뵵�� ���
	 * ResponseEntity Ÿ���� �����ڰ� ���� ��� �����Ϳ�  HTTP�� ���� �ڵ带
	 * ���� ������ �� �ִ� Ŭ�����̴� 404sk 500���� �����ڵ带 �����ϰ���� �����Ϳ� �Բ�
	 * ������ �� �ֱ� ������ ���� ������ ��� ����
	 * */
	
	@ResponseBody
	@RequestMapping(value= "/all/{h_Num}.htk", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("h_Num") String h_Num){
		
		System.out.println("ReplyController.list()�Լ� ����");
		System.out.println("h_Num >> : "+ h_Num);
		ResponseEntity<List<ReplyVO>> entity = null;
		try{
			entity = new ResponseEntity<>(replyService.replyList(h_Num),HttpStatus.OK);
		}catch(Exception e){
			System.out.println("������ >> :"+ e.getMessage());
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("entity >> : "+ entity);
		System.out.println("ReplyController.list()�Լ� ��");
		
		return entity;
	}//end of list
	
	//��� �۾��� ����
	@ResponseBody
	@RequestMapping(value="/replyInsert")
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo){
		System.out.println("ReplyController.replyInsert()�Լ� ����");
		System.out.println("�� >> : "+ rvo.getR_content() +":" +rvo.getR_name()+":"+rvo.getR_pwd());
		ResponseEntity<String> entity = null;
		int result = 0;
		
		String chaebun = replyService.replyChaebun();
		System.out.println("ä�� �� >> : "+ chaebun);
		
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
		
		System.out.println("ReplyController.replyInsert()�Լ� ��");
		return entity;
		
	}
	//��� ���� �����ϱ�
	/*
	 * ����:REST ��Ŀ��� UPDATE �۾��� PUT,PATCH ����� �̿��ؼ� ó��
	 * ��ü �����͸� �����ϴ� ��쿡�� PUT�� �̿��ϰ�
	 * �Ϻ��� �����͸� �����ϴ� ��쿡�� PATCH�� �̿�
	 * */
	
	@ResponseBody
	@RequestMapping(value="/{r_num}.htk",method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("r_num") String r_num,@RequestBody ReplyVO rvo){
		System.out.println("ReplyController.replyUpdate()�Լ� ����");
		
		ResponseEntity<String> entity = null;
		System.out.println("r_num >> : "+ r_num);	
		System.out.println("rvo �� Ȯ��"+rvo.getR_num());
		try{
			rvo.setR_num(r_num);
			replyService.replyUpdate(rvo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e){
			System.out.println("������ >: " + e.getMessage());
			
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("ReplyController.replyUpdate()�Լ� ��");
		
		return entity;
	}
	
	
	//��� ���� �����ϱ� 
	
	@ResponseBody
	@RequestMapping(value="/{r_num}.htk",method= {RequestMethod.DELETE, RequestMethod.PATCH})
	public ResponseEntity<String> replyDelete(@PathVariable("r_num") String r_num){
		System.out.println("ReplyController.replyDelete()�Լ� ����");
		
		ResponseEntity<String> entity = null;
		System.out.println("r_num >> : "+ r_num);	
		try{

			replyService.replyDelete(r_num);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e){
			System.out.println("������ >: " + e.getMessage());
			
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("ReplyController.replyDelete()�Լ� ��");
		
		return entity;
	}
	
}
