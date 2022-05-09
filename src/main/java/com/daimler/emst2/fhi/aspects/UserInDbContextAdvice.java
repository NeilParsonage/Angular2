package com.daimler.emst2.fhi.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.daimler.emst2.fhi.aspects.dao.BenutzerverwaltungDao;

@Aspect
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserInDbContextAdvice {

    private static String getUsernameForLogging() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "no-user-found";
        }
        return authentication.getName();
    }

    private final ThreadLocal<Integer> callCount = new ThreadLocal<>();

    @Autowired
    BenutzerverwaltungDao benutzerverwaltungDao;

    @Around("UserInDbContextPointcut.annotationPointcut()")
    public Object storeUserInDbContext(ProceedingJoinPoint pjp) {
        return doStoreUserInDbContext(pjp);
    }

    private Object doStoreUserInDbContext(ProceedingJoinPoint pjp) {
        setUserInDbContextIfUnset();

        try {
            Object result = pjp.proceed();
            return result;
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            unsetUserInDbContextIfLast();
        }
    }

    private void unsetUserInDbContextIfLast() {
        int count = decCount();
        if (count <= 0) {
            benutzerverwaltungDao.clearUsernameInContext();
        }
    }

    private void setUserInDbContextIfUnset() {
        String userId = getUsernameForLogging();
        if (getCount() <= 0) {
            benutzerverwaltungDao.setUsernameInContext(userId);
        }
        incCount();
    }

    private int incCount() {
        int count = getCount();
        count++;
        setCount(count);
        return count;
    }

    private int decCount() {
        int count = getCount();
        count--;
        setCount(count);
        return count;
    }

    private int getCount() {
        Integer count = callCount.get();
        if (count == null) {
            return 0;
        }
        return count.intValue();
    }

    private void setCount(int count) {
        if (count <= 0) {
            callCount.set(null);
        }
        else {
            callCount.set(Integer.valueOf(count));
        }
    }
}