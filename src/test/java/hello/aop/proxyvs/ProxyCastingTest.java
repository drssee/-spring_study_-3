package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();//인터페이스o, 구체클래스o
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);//JDK 동적 프록시 사용하도록 적용

        //프록시를 인터페이스로 캐스팅 성공
        Object proxy = proxyFactory.getProxy();
        MemberService memberService = (MemberService) proxy; //성공

        //인터페이스를 기반으로 만들어지는 JDK동적프록시는 구체 클래스인 MemberServiceImpl을 알지 못한다
        Assertions.assertThatThrownBy(() -> {
            MemberServiceImpl memberService1 = (MemberServiceImpl) proxy; //실패
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();//인터페이스o, 구체클래스o
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);//cglib 사용하도록 적용

        //프록시를 인터페이스로 캐스팅 성공
        Object proxy = proxyFactory.getProxy();
        MemberService memberService = (MemberService) proxy; //성공
        MemberServiceImpl memberService1 = (MemberServiceImpl) proxy; //성공 
    }
}
