package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MemberRepository {

	@PersistenceContext
	private EntityManager em;
	
	// return을 member 통째로 하지 않는 이유는?
	// 커맨드랑 쿼리를 분리하자
	// 분리함으로써 용도를 확실하게 한다
	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}
	
	public Member find (Long id) {
		return em.find(Member.class, id);
	}
	
}
