package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    //생성자 대신 세터를 사용한 이유
    //생성자를 사용하면 자신이 생성 되지도 않았는데 자신을 참조해야하는 문제가 생긴다
    //세터를 사용할 경우 스프링빈생성(이 단계에서 생성자가 사용된다) -> 의존관계주입(이 단계에서 세터가 사용된다)
//    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); //프록시의 internal() 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
