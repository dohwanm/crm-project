package com.spring.reply.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.reply.dao.ReplyDao;
import com.spring.reply.vo.ReplyVO;


@Service
@Transactional

public class ReplyServiceImpl implements ReplyService {

	Logger logger = Logger.getLogger(ReplyServiceImpl.class);
	
	@Autowired
	private ReplyDao replyDao;
	
	//글목록
	@Override
	public List<ReplyVO> replyList(String h_Num) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyList()함수 시작");
		
		List<ReplyVO> myList = replyDao.replyList(h_Num);
		
		System.out.println("ReplyServiceImpl.replyList()함수 끝");
		return myList;
	}
	
	
	//글입력 구현
	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyInsert()함수 시작");
		
		int result = replyDao.replyInsert(rvo);
		System.out.println("ReplyServiceImpl.replyInsert()함수 시작");
		return result;
	}

	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyUpdate()함수 시작");
		
		int result = replyDao.replyUpdate(rvo);
		
		System.out.println("ReplyServiceImpl.replyUpdate()함수 시작");
		return result;
	}

	@Override
	public int replyDelete(String r_num) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyDelete()함수 시작");
		
		int result = replyDao.replyDelete(r_num);
		
		System.out.println("ReplyServiceImpl.replyDelete()함수 시작");
		return result;
	}


	@Override
	public String replyChaebun() {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyChaebun()함수 시작");
		
		String chaebun = replyDao.replyChaebun();
		
		if(chaebun.length() == 1){
			chaebun = "000" + chaebun;
		}
		if(chaebun.length() == 2){
			chaebun = "00" + chaebun;
		}
		if(chaebun.length() == 3){
			chaebun = "0" + chaebun;
		}
		chaebun = "R" + chaebun;
		System.out.println("ReplyServiceImpl.replyChaebun()함수 시작");
		return chaebun;
	}

}
