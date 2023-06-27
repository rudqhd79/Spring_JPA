package jpabook.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	
	// 아이템이 DB에 신규등록 OR 기존등록
	// Insert
	public void save(Item item) {
		// null 값으로 한 이유는 DB에 상품이 첫 등록 일 때
		if (item.getId() == null) {
			em.persist(item);
		} else {
			// 첫 등록 이후 (merge는 강제 update)
			em.merge(item);
		}
	}
	
	// ID찾기
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	// Select
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
	
}
