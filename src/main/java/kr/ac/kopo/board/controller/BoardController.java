package kr.ac.kopo.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		System.out.println("board/detail handler ??????");
		BoardVO board = boardService.getOneBoard(boardNo);
		model.addAttribute("board",board);
		return "board/detail";
	}
	
	// ?????? ??????
	// http://localhost:8080/springboard/board/write
//	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	@GetMapping("/board/write")
	public void writeGet(Model model) {
//		void : ????????? ????????? ????????? jsp??? ?????? 
		
		BoardVO boardVO1 = new BoardVO();
		model.addAttribute("boardVO1", boardVO1);
		//		return "board/write";
	}
	
//	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	@PostMapping("/board/write") // @Valid ?????? BindingResult??? ????????????
	public String writePost(@Valid @ModelAttribute("boardVO1") BoardVO board, BindingResult result){
		
		// 0. ???????????? board ??? null ?????? ??????????????? ????????? ????????????. BindingResult??? ??????
		System.out.println(result);
//		org.springframework.validation.BeanPropertyBindingResult: 1 errors
//		Field error in object 'boardVO' on field 'content': rejected value []; codes [NotEmpty.boardVO.content,NotEmpty.content,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [boardVO.content,content]; arguments []; default message [content]]; default message [?????????????????????.]
		if(result.hasErrors()) {
			// error??? ???????????? ??????. null??? ?????? ??????
			return "board/write";
		}else {
			// error??? ???????????? ?????? ?????? 
			// insert DB -> ?????? ????????? ???????????? redirect
			// 1. DB insert ??????
			// 2. ??? ?????? ????????? ??????????????? list ??????
			boardService.write(board);
			return "redirect:/board";
			
		}
		
		
	}
	
}
