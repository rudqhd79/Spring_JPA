/*
 * package jpabook.jpashop;
 * 
 * import org.assertj.core.api.Assertions; import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.annotation.Rollback; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import jpabook.jpashop.domain.Member; import
 * jpabook.jpashop.repository.MemberRepository;
 * 
 * 
 * @SpringBootTest public class MemberRepositoryTest {
 * 
 * @Autowired MemberRepository memberRepository;
 * 
 * // 트랜잭션이 없으면 오류가난다 // rollback은 test DB에 값이 들어가지 않는 것을 되돌리는 것 (값이 들어감으로써 제대로
 * 돌아가는지 확인가능)
 * 
 * @SuppressWarnings({ "deprecation", "unlikely-arg-type" })
 * 
 * @Test
 * 
 * @Transactional
 * 
 * @Rollback(false) public void testMember() throws Exception { // given Member
 * member = new Member(); member.setName("memberA");
 * 
 * // when Long saveId = memberRepository.save(member); Member findMebmer =
 * memberRepository.findOne(saveId);
 * 
 * // then Assertions.assertThat(findMebmer.getId().equals(member.getId()));
 * Assertions.assertThat(findMebmer.getName().equals(member.getName()));
 * Assertions.assertThat(findMebmer).equals(member); }
 * 
 * }
 */