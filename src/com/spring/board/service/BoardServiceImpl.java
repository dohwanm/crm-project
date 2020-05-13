package com.spring.board.service;

import java.util.List;

import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVO;



@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	Logger logger = Logger.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDao boardDao;
	
	//�۸�� ����
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardList()�Լ� ����");
		
		List<BoardVO> myList = boardDao.boardList(bvo);
		
		
		logger.info("BoardServiceImpl.boardList()�Լ� ��");
		return myList;
	}
	
	//���Է�
	@Override
	public int boardInsert(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardInsert()�Լ� ����");
		
		
		int result = boardDao.boardInsert(bvo);
		
		logger.info("BoardServiceImpl.boardInsert()�Լ� ��");
		return result;
	}

	// �ۻ�
	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardDetail()�Լ� ����");
		
		BoardVO detail = boardDao.boardDetail(bvo);
		
		logger.info("BoardServiceImpl.boardDetail()�Լ� ��");
		return detail;
	}

	
	//��й�ȣ Ȯ��
	@Override
	public int pwdConfirm(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.pwdConfirm()�Լ� ����");
		
		int result = boardDao.pwdConfirm(bvo);
		
		logger.info("BoardServiceImpl.pwdConfirm()�Լ� ��");
		return result;
	}

	
	//
	@Override
	public int boardUpdate(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardUpdate()�Լ� ����");
		
		int result = boardDao.boardUpdate(bvo);
		
		logger.info("BoardServiceImpl.boardUpdate()�Լ� ��");
		return result;
	}

	@Override
	public int boardDelete(String h_Num) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardDelete()�Լ� ����");
		
		int result = boardDao.boardDelete(h_Num);
		
		logger.info("BoardServiceImpl.boardDelete()�Լ� ��");
		return result;
	}

	@Override
	public String boardChaebun() {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardChaebun()�Լ� ����");
		
		String result = boardDao.boardChaebun();
		if(result.length() == 1){
			result = "000" + result;
		}
		if(result.length() == 2){
			result = "00" + result;
		}
		if(result.length() == 3){
			result = "0" + result;
		}
		result = "H" + result;
		logger.info("BoardServiceImpl.boardChaebun()�Լ� ��");
		return result;
	}

	@Override
	public int boardListCnt(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardListCnt()�Լ� ����");
		
		logger.info("BoardServiceImpl.boardListCnt()�Լ� ��");
		return boardDao.boardListCnt(bvo);
	}

}
