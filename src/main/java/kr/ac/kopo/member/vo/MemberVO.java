package kr.ac.kopo.member.vo;

import javax.validation.constraints.NotEmpty;

public class MemberVO {
	@NotEmpty(message = "필수항목입니다.")
	private String id;
	
	@NotEmpty(message = "필수항목입니다.")
	private String password;

	// @NotEmpty : 로그인시 오류가 난다 -> 처리방법 : 1. vo 분리 
	private String name;
	
	public MemberVO() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + "]";
	}
	
}
