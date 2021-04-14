package com.daimler.emst2.frw.aspects;


import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.daimler.emst2.frw.context.IAuthenticationContext;

@Aspect
@Component
public class LoggingRestEndpointAspect {

    
    @Autowired
    IAuthenticationContext authContext;

    private final Logger log = Logger.getLogger(this.getClass().getName());

    public LoggingRestEndpointAspect() {
        log.fine("creating " + this.getClass().getName());
    }

    @Around("execution(* com.daimler.emst2..controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        if (log.isLoggable(Level.FINE)) {
            String username = getUsernameForLogging();
            log.fine("user: '"
                    + username
                    + "' before "
                    + joinPoint
                    + " / ROLES:" + getRolesForLogging());
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

    public String getUsernameForLogging() {
        Authentication authentication = authContext.getAuthentication();
        if (authentication == null) {
            return "no-user-found";
        }

        return authentication.getName();
    }

    public Set<String> getRolesForLogging() {
        Authentication authentication = authContext.getAuthentication();
        if (authentication == null) {
            return Collections.emptySet();
        }
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        return roles;
    }
}
