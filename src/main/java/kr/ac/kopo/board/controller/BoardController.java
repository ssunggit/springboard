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
		System.out.println("board/detail handler 동작");
		BoardVO board = boardService.getOneBoard(boardNo);
		model.addAttribute("board",board);
		return "board/detail";
	}
	
	// 새글 쓰기
	// http://localhost:8080/springboard/board/write
//	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	@GetMapping("/board/write")
	public void writeGet(Model model) {
//		void : 요청한 동일한 이름의 jsp로 이동 
		
		BoardVO boardVO1 = new BoardVO();
		model.addAttribute("boardVO1", boardVO1);
		//		return "board/write";
	}
	
//	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	@PostMapping("/board/write") // @Valid 뒤에 BindingResult가 와야한다
	public String writePost(@Valid @ModelAttribute("boardVO1") BoardVO board, BindingResult result){
		
		// 0. 입력받은 board 가 null 값이 포함안되어 있는지 확인한다. BindingResult를 활용
		System.out.println(result);
//		org.springframework.validation.BeanPropertyBindingResult: 1 errors
//		Field error in object 'boardVO' on field 'content': rejected value []; codes [NotEmpty.boardVO.content,NotEmpty.content,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [boardVO.content,content]; arguments []; default message [content]]; default message [필수사항입니다.]
		if(result.hasErrors()) {
			// error가 존재하는 경우. null이 있는 경우
			return "board/write";
		}else {
			// error가 존재하지 않는 경우 
			// insert DB -> 전체 게시글 리스트로 redirect
			// 1. DB insert 작업
			// 2. 다 완료 후에는 전체게시글 list 조회
			boardService.write(board);
			return "redirect:/board";
			
		}
		
		
	}
	
}
