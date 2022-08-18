package kr.ac.kopo.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.reply.dao.ReplyDAO;
import kr.ac.kopo.reply.vo.ReplyVO;

@Service
public class ReplyService {
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Transactional // 트랜잭션
	public void insert(ReplyVO reply) {
		boardDAO.increaseReplyCnt(reply.getBoardNo()); // 댓글수 증가
		replyDAO.insert(reply); // 댓글 입력
	}
	
	public List<ReplyVO> selectAll(int boardVo){
		List<ReplyVO> replyList = replyDAO.selectAll(boardVo);
		return replyList;
	}
	
	@Transactional // 트랜잭션으로 묶어서 한번에 처리
	public void deleteReply(int boardNo,int replyNo) {
		boardDAO.decreaseReplyCnt(boardNo); // 댓글수 감소
		replyDAO.deleteReply(replyNo);	// 댓글 삭제
	}
	
}
