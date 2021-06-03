package com.study.spring_AOP.config;

import com.study.spring_AOP.domain.CommonDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class BindingAdvice {

    private static final Logger logger = LoggerFactory.getLogger(BindingAdvice.class);

    @Before("execution(* com.study.spring_AOP.domain..*Controller.*(..))")
    public void testCheck(){
        System.out.println(" 전처리 로그를 남겼습니다.");

    }

    @After("execution(* com.study.spring_AOP.domain..*Controller.*(..))")
    public void testCheck2(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println("주소"+request.getRequestURI());

        System.out.println(" 후처리 로그를 남겼습니다.");

    }

    @Around("execution(* com.study.spring_AOP.domain..*Controller.*(..))")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();
        System.out.println("method = " + method);
        System.out.println("type = " + type);

        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        logger.warn(type+"."+method+"() = > 필드:"+error.getField()+", 메시지:"+error.getDefaultMessage());
                    }
                    //로그 레벨 debug < info < warn < error 순

                    return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 함수의 스택을 이어서 실행
    }
}
