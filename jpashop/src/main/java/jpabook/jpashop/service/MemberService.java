package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 필드로 생성자를 만들어준다
public class MemberService {

	private final MemberRepository memberRepository;
	
	// setter Injection (setter를 통해 주입 방식)
	// 장점 : 가짜 memberRepository를 통해 테스트 가능
	// 단점 : 런타임에서 누군가가 setter를 통해 정보를 바꿔버릴 수 있다
	/*
	 * @Autowired public void setMemberRepository (MemberRepository
	 * memberRepository) { this.memberRepository = memberRepository; }
	 */
	
	// 생성자 방식
	/*
	 * @Autowired public MemberService (MemberRepository memberRepository) {
	 * this.memberRepository = memberRepository; }
	 */
	
	// 회원가입
	@Transactional
	public Long join (Member member) {
		// 중복된 회원 인지?
		validateDuplicateMember(member);
		// repository를 통해 회원정보 insert
		memberRepository.save(member);
		// return을 통해 id를 확인하여 로그기록 남김
		return member.getId();
	}
	
	// 중복 확인
	private void validateDuplicateMember (Member member) {
		List<Member> findMembers =  memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체 조회
	// Transactional (readOnly = true)를 사용 할 수 있지만 지워도 상관없다
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
