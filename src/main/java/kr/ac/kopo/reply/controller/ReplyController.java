package kr.ac.kopo.reply.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.reply.service.ReplyService;
import kr.ac.kopo.reply.vo.ReplyVO;

//@Controller
@RestController // 해당 클래스의 메소드는 다 @ResponseBody 로 동작
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	// @ResponseBody : 이 어노테이션이 붙은 파라미터에는 http요청의 본문(body)이 그대로 전달된다.
	// xml이나 json기반의 메시지를 사용하는 요청의 경우에 이 방법이 매우 유용
	// 바로 본문을 고객한테 넘겨줌
//	@ResponseBody
	@PostMapping("/reply")
	public void insert(ReplyVO reply) {
		replyService.insert(reply); // insert와 update 가 일어남
	}
	
	// /reply/{boardNo}
//	@ResponseBody
	@GetMapping("/reply/{boardNo}") // 리퀘스트 x -> @PathVariable
	public List<ReplyVO> getAllReply(@PathVariable("boardNo") int boardNo) {
		
		List<ReplyVO> replyList = replyService.selectAll(boardNo);
		
		return replyList;
	}
	
//	@ResponseBody
	@DeleteMapping("/reply/{boardNo}/{replyNo}")
	public void deleteReply(@PathVariable("boardNo") int boardNo, @PathVariable("replyNo") int replyNo) {
		System.out.println("boardNo: "+boardNo+",replyNo: "+replyNo);
		replyService.deleteReply(boardNo,replyNo);
			
	}
}
