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
	
	//글목록 구현
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardList()함수 시작");
		
		List<BoardVO> myList = boardDao.boardList(bvo);
		
		
		logger.info("BoardServiceImpl.boardList()함수 끝");
		return myList;
	}
	
	//글입력
	@Override
	public int boardInsert(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardInsert()함수 시작");
		
		
		int result = boardDao.boardInsert(bvo);
		
		logger.info("BoardServiceImpl.boardInsert()함수 끝");
		return result;
	}

	// 글상세
	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardDetail()함수 시작");
		
		BoardVO detail = boardDao.boardDetail(bvo);
		
		logger.info("BoardServiceImpl.boardDetail()함수 끝");
		return detail;
	}

	
	//비밀번호 확인
	@Override
	public int pwdConfirm(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.pwdConfirm()함수 시작");
		
		int result = boardDao.pwdConfirm(bvo);
		
		logger.info("BoardServiceImpl.pwdConfirm()함수 끝");
		return result;
	}

	
	//
	@Override
	public int boardUpdate(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardUpdate()함수 시작");
		
		int result = boardDao.boardUpdate(bvo);
		
		logger.info("BoardServiceImpl.boardUpdate()함수 끝");
		return result;
	}

	@Override
	public int boardDelete(String h_Num) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardDelete()함수 시작");
		
		int result = boardDao.boardDelete(h_Num);
		
		logger.info("BoardServiceImpl.boardDelete()함수 끝");
		return result;
	}

	@Override
	public String boardChaebun() {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardChaebun()함수 시작");
		
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
		logger.info("BoardServiceImpl.boardChaebun()함수 끝");
		return result;
	}

	@Override
	public int boardListCnt(BoardVO bvo) {
		// TODO Auto-generated method stub
		logger.info("BoardServiceImpl.boardListCnt()함수 시작");
		
		logger.info("BoardServiceImpl.boardListCnt()함수 끝");
		return boardDao.boardListCnt(bvo);
	}

}
