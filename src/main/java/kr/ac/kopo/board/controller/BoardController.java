package kr.ac.kopo.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.board.service.BoardService;
import kr.ac.kopo.board.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// http://localhost:8080/springboard/board
	@RequestMapping("/board")
	public String allboard(HttpServletRequest request) {
		List<BoardVO> boardlist = new ArrayList<>();
		boardlist=boardService.getAllBaord();
		request.setAttribute("boardlist", boardlist);
		// WEB-INF/jsp/board/list.jsp
		return "board/list";
	}
	
	//  /board/detail?boardNo=3
//	@RequestMapping("/board/detail")
//	public String detail(HttpServletRequest request, @RequestParam("boardNo") int boardNo, Model model) {
//		//String boardNo = request.getParameter("boardNo");
//		//boardService.getOneBoard(Integer.parseInt(boardNo));
//		
//		BoardVO board = boardService.getOneBoard(boardNo);
//		model.addAttribute("board",board);
//		return "board/detail";
//	}
	/*
	@RequestMapping("/board/detail/{boardNo}")
	public String detail(HttpServletRequest request, @PathVariable("boardNo") int boardNo, Model model) {
		//String boardNo = request.getParameter("boardNo");
		//boardService.getOneBoard(Integer.parseInt(boardNo));
		
		BoardVO board = boardService.getOneBoard(boardNo);
		model.addAttribute("board",board);
		return "board/detail";
	}
	*/
	@RequestMapping("/board/detail/{boardNo}")
	public String detail2(@PathVariable("boardNo") int boardNo, Model model) {
		//String boardNo = request.getParameter("boardNo");
		//boardService.getOneBoard(Integer.parseInt(boardNo));
		
		BoardVO board = boardService.getOneBoard(boardNo);
		model.addAttribute("board",board);
		return "board/detail";
	}
}
