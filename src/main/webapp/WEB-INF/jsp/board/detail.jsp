<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		
		$(document).on('click', '.delBtn', function() {
			let replyNo = $(this).attr('id')
			$.ajax({
				url	 	: '${ pageContext.request.contextPath }/reply/${board.no}/' + replyNo,
				type	: 'delete',
				success : function() {
					alert('성공')
					getAllReply() 						
				}, 
				error 	: function () {
					alert('실패')						
				}			
			})
		
		})	
	})	
		
 	

	function getAllReply() {
		$.ajax({
			url	 	: '${ pageContext.request.contextPath }/reply/${board.no}',
			type	: 'get',
			success : function(data) {
				$('#replyList').empty();
				
				console.log(typeof data)
				console.log(data)
				let list = JSON.parse(data);
				console.log(list)
				
				$(list).each(function () {
					let str = '';
					str += '<hr>';
					str += '<div>';					
					str += this.content + '		';
					str += this.writer + '		';
					str += this.regDate + '		';
					str += '<button class="delBtn" id='+this.no+'>삭제</button>';
					
					str += '</div>';
					
					$('#replyList').append(str)
				})
				
			}, 
			error 	: function () {
				alert('실패')
						
			}
			
		})	
	}
	
	
	$(document).ready(function() {
		getAllReply()
		$('#addReplyBtn').click(function () {
			let r_content = document.rform.content.value;
			let r_writer = document.rform.writer.value;
			
			$.ajax({
				url : '${ pageContext.request.contextPath }/reply',
				type : 'post',
				data: {
					boardNo : ${board.no},
					content : r_content,
					writer : r_writer
				},
				success: function () {
					alert('성공')
					// 댓글 리스트 요청
					getAllReply()
					
				},
				error : function () {
					alert('실패')					
				},
				complete : function () {
					document.rform.content.value = null
		            document.rform.writer.value = null
				}
				
			})
		})
	})
	

</script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/include/topMenu.jsp"></jsp:include>
	</header>

	<h1 style="text-align: center;">상세게시글 입니다</h1>
	
	<table class="table table-striped table-hover" style="width: 80%; margin: auto; text-align: center;">
		<tr>
			<th>제목</th>
			<td>${board.title}</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>${board.writer}</td>
		</tr>
		
		<tr>
			<th>등록일</th>
			<td>${board.regDate}</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>${board.content}</td>
		</tr>	
		<tr>
			<th>댓글수</th>
			<td>${board.replyCnt}</td>
		</tr>	
	</table>
	
	<form name="rform">
		댓글 : <input type="text" size="30" name="content">
		작성자 : <input type="text" size="20" name="writer">
		<input type="button" value="댓글작성" id="addReplyBtn">
	</form>
	
	<div id="replyList">
	
	</div>
	
	
</body>
</html>