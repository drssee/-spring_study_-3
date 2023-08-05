package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)//빈으로 임포트
@SpringBootTest//스프링 컨테이너를 테스트에서 사용한다
@Slf4j
public class CallServiceV1Test {

    @Autowired
    CallServiceV1 callServiceV1;

    @Test
    void external() {
        log.info("callServiceV1={}", callServiceV1.getClass());
        callServiceV1.external();
    }

    @Test
    void internal() {
        log.info("callServiceV0={}", callServiceV1.getClass());
        callServiceV1.internal();
    }
}
