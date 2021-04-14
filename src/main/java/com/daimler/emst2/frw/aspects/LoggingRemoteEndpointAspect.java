package com.daimler.emst2.frw.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.daimler.emst2.frw.context.IAuthenticationContext;

import io.micrometer.core.instrument.util.StringUtils;

@Aspect
@Component
public class LoggingRemoteEndpointAspect {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    IAuthenticationContext authContext;

    public LoggingRemoteEndpointAspect() {
        log.fine("creating " + this.getClass().getName());
    }

    @Around("execution(* com.daimler.emst2..remote.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        if (log.isLoggable(Level.FINE)) {
            log.fine("user: '"
                    + getUsernameForLogging()
                    + "' before "
                    + joinPoint
                    + "");
        }
        Object result = joinPoint.proceed();
        if (log.isLoggable(Level.FINE)) {
            long timeTaken = System.currentTimeMillis() - startTime;
            log.fine("user: '"
                    + getUsernameForLogging()
                    + "' after  "
                    + joinPoint
                    + " is "
                    + timeTaken
                    + " ms");
        }
        return result;
    }

    private String getUsernameForLogging() {
        Authentication authentication = authContext.getAuthentication();
        if (authentication == null || StringUtils.isBlank(authentication.getName())) {
            return "no-user-found";
        }
        return authentication.getName();
    }
}
