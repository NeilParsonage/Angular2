package com.daimler.emst2.fhi.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class UserInDbContextPointcut {

    @Pointcut("@annotation(UserInDbContextAnnotation)")
    protected void annotationPointcut() {
    }
}