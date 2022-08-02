package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    //final 장점 : 생성자에서만 값을 넣어줄수있음 , 나머지에서는 값을 넣어줄 수 없음
    // 값을 생성자에서 안넣어주고 final 붙였을때 자바 컴파일 단계서 오류를 잡을 수있음
    //생성자 주입 방식에만 'final' 키워드를 사용할 수 있음
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자 주입...?
    //생성시점에 딱 한번만 호출보장
    //블변,필수 의존관계에 사용
    //생성자 한개일때 기본으로 자동 autowired 붙여줌
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
