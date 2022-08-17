package kr.ac.kopo.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.reply.dao.ReplyDAO;
import kr.ac.kopo.reply.vo.ReplyVO;

@Service
public class ReplyService {
	@Autowired
	private ReplyDAO replyDAO;
	
	public void insert(ReplyVO reply) {
		replyDAO.insert(reply);
	}
}
