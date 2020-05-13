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
	
	//�۸��
	@Override
	public List<ReplyVO> replyList(String h_Num) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyList()�Լ� ����");
		
		List<ReplyVO> myList = replyDao.replyList(h_Num);
		
		System.out.println("ReplyServiceImpl.replyList()�Լ� ��");
		return myList;
	}
	
	
	//���Է� ����
	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyInsert()�Լ� ����");
		
		int result = replyDao.replyInsert(rvo);
		System.out.println("ReplyServiceImpl.replyInsert()�Լ� ����");
		return result;
	}

	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyUpdate()�Լ� ����");
		
		int result = replyDao.replyUpdate(rvo);
		
		System.out.println("ReplyServiceImpl.replyUpdate()�Լ� ����");
		return result;
	}

	@Override
	public int replyDelete(String r_num) {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyDelete()�Լ� ����");
		
		int result = replyDao.replyDelete(r_num);
		
		System.out.println("ReplyServiceImpl.replyDelete()�Լ� ����");
		return result;
	}


	@Override
	public String replyChaebun() {
		// TODO Auto-generated method stub
		System.out.println("ReplyServiceImpl.replyChaebun()�Լ� ����");
		
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
		System.out.println("ReplyServiceImpl.replyChaebun()�Լ� ����");
		return chaebun;
	}

}
