package kr.ac.kopo.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring-mvc.xml"})
public class MyBatisTest {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Test
	public void myBatisT(){
		assertNotNull(sqlSessionTemplate);
	}
	
   @Test
   public void boardSelectAll() {
      List<BoardVO> boardList = sqlSessionTemplate.selectList("board.dao.boardDAO.selectAll");
      for(BoardVO board : boardList) {
         System.out.println(board);
      }
   }
   
   @Test
   public void boardInsertTest() {
	   BoardVO boardVO = new BoardVO();
	   boardVO.setTitle("title");
	   boardVO.setWriter("writer");
	   boardVO.setContent("content");
	   
	   sqlSessionTemplate.insert("board.dao.boardDAO.insert", boardVO);
   }
   @Test
   public void memberSelectTest() {
	   
	   MemberVO member = new MemberVO();
	   member.setId("user");
	   member.setPassword("user");
	   
	   MemberVO authMember = sqlSessionTemplate.selectOne("member.dao.memberDAO.selectUser", member);
	   
	   System.out.println(authMember);
   }
   @Test
   public void insertUserTest() {
	   
	   MemberVO member = new MemberVO();
	   member.setId("id");
	   member.setPassword("password");
	   member.setName("name");
	   
	   sqlSessionTemplate.insert("member.dao.memberDAO.insertUser", member);
	   
   }

}
