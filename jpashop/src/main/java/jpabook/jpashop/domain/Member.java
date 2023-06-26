package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@GeneratedValue
	@Id
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	
	// 임베디드 타입(내장 할 수 있는 객체)
	// DB에는 내장 객체의 column들이 생성된다
	@Embedded
	private Address address;
	
	// 연관관계의 주인이 아니다 (이유는 order 엔티티에 있는 member_id와 orderItem 엔티티를 연결해주는 역할을 하기 때문이다)
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
	
}
