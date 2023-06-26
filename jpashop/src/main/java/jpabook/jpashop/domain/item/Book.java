package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// 상속 관계에서 자식 관계에 쓰인다
@DiscriminatorValue("B")
public class Book extends Item {

	private String author;
	
	private String isbn;
	
}
