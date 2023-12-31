package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //call AppConfig.memberService
    //call AppConfig.memoryRepository
    //call AppConfig.orderService
    //call AppConfig.memoryRepository
    @Bean
    public MemoryMemberRepository memberRepository() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        System.out.println("call AppConfig.memoryRepository :" + memberRepository);
        return memberRepository;
    }

    @Bean
    public MemberService memberService() {
        MemberServiceImpl memberService = new MemberServiceImpl(memberRepository());
        System.out.println("call AppConfig.memberService :" + memberService.getMemberRepository());
        return memberService;
    }

    @Bean
    public DiscountPolicy discountPolicy() {

        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

}
