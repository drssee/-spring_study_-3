package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    //hello.aop.order패키지 하위의 모든 메서드들
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){} //pointcut 시그니처

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
