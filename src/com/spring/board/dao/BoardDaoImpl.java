package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.vo.BoardVO;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;

	
	
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		// TODO Auto-generated method stub
		return session.selectList("boardList");
	}

	@Override
	public int boardInsert(BoardVO bvo) {
		// TODO Auto-generated method stub
		return session.insert("boardInsert");
	}

	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		// TODO Auto-generated method stub
		return (BoardVO)session.selectOne("boardDetail");
	}

	@Override
	public int pwdConfirm(BoardVO bvo) {
		// TODO Auto-generated method stub
		return (Integer)session.selectOne("pwdConfirm");
	}

	@Override
	public int boardUpdate(BoardVO bvo) {
		// TODO Auto-generated method stub
		return session.update("boardUpdate");
	}

	@Override
	public int boardDelete(String h_Num) {
		// TODO Auto-generated method stub
		return session.update(h_Num);
	}

	@Override
	public String boardChaebun() {
		// TODO Auto-generated method stub
		return session.selectOne("boardChaebun");
	}

	@Override
	public int boardListCnt(BoardVO bvo) {
		// TODO Auto-generated method stub
		return (Integer)session.selectOne("boardListCnt");
	}

}
