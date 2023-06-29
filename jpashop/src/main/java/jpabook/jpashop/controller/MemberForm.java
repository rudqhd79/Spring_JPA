package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberForm {

	// 어노테이션 적용이 안된다 버전 문제로 dependencies에 추가해도 안됨
	/* @NotEmpty(message = "회원정보 입력은 필수 입니다.") */
	private String name;
	
	private String city;
	
	private String street;
	
	private String zipcode;
	
}
