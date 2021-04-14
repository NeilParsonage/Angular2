package com.daimler.emst2.frw.context;

import org.springframework.security.core.Authentication;

public interface IAuthenticationContext {
    Authentication getAuthentication();
}
