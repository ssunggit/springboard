package kr.ac.kopo.reply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.reply.vo.ReplyVO;

@Repository
public class ReplyDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(ReplyVO reply) {
		sqlSessionTemplate.insert("reply.dao.replyDAO.insert",reply);
	}
	
	
	public List<ReplyVO> selectAll(int boardNo) {
		List<ReplyVO> replyList = sqlSessionTemplate.selectList("reply.dao.replyDAO.selectAll", boardNo);
		return replyList;
	}


	public void deleteReply(int replyNo) {
		sqlSessionTemplate.delete("reply.dao.replyDAO.delete", replyNo);
	}
	
}
