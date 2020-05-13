package com.spring.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.reply.vo.ReplyVO;


@Repository
public class ReplyDaoImpl implements ReplyDao {
	
	@Autowired
	private SqlSession session;

	//글목록
	@Override
	public List<ReplyVO> replyList(String h_Num) {
		// TODO Auto-generated method stub
		return session.selectList("replyList", h_Num);
	}

	
	//글입력 구현
	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return session.insert("replyInsert");
	}

	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return session.update("replyUpdate");
	}

	@Override
	public int replyDelete(String r_num) {
		// TODO Auto-generated method stub
		return session.update("replyDelete");
	}


	@Override
	public String replyChaebun() {
		// TODO Auto-generated method stub
		return session.selectOne("replyChaebun");
	}

}
