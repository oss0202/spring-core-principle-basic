package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// 설정정보 annotation
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository();
    //@Bean orderService -> new MemoryMemberRepository();
    // 이 2개의 new MemoryMemberRepository()는 같은가??

    /*
    1. memberService
    AppConfig.memberService
    AppConfig.memberRepository

    2. orderService
    AppConfig.orderService
    AppConfig.memberRepository

    3. memberRepository
    AppConfig.memberRepository

    이 예상 되지만

    AppConfig.memberService
    AppConfig.memberRepository
    AppConfig.orderService
     */

    @Bean// Spring container 등록
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
