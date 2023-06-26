package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// 상속 관계다 라는 것을 어노테이션으로 선언
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 상속 관계에서 부모 관계라는 것을 어노테이션으로 선언
@DiscriminatorColumn(name = "dtype")
public class Category {

	@Id
	@Column(name = "category_id")
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@ManyToMany
	// jointable이 필요한 이유는 다대다 관계에서 조인 할 테이블이 있어야 가능
	// 다대다 관계로 진행하고 싶으면 JoinTable을 이용하여 사용해보자
	// 실무에서는 추가로 넣을 작성일 등 dateTime을 extends하여 작성자, 수정일 등등
	// 하지만 jointable로 만들어버리면 중간 Entity에 추가 할 것들을 못하기 때문에
	// 보통 다대다 관계는 이루어 지지 않게끔 중간역할 Entity가 필요하다
	@JoinTable(name = "category_item",
		joinColumns = @JoinColumn(name="category_id"),
		inverseJoinColumns = @JoinColumn(name = "item_id"))
	// inverseJoinColumns는 반대편의 FK
	private List<Item> items = new ArrayList<>();
	
	// parent와 child는 부모 1개에 자식 여러개 가능
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();
	
	// 연관관계 메소드
	public void addChildCategory (Category child) {
		this.child.add(child);
		child.setParent(this);
	}
	
}
