/*
 */
package com.daimler.emst2.frw.controller;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author CAL
 */
@RestController
@RequestMapping("/pub/keycloak")
public class KeyCloakConfigController {

    @Value("${keycloak.auth-server-url}")
    private String auth_server_url;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String resource;

    @GetMapping
    public UIKeyCloakConfig get() {
        return new UIKeyCloakConfig(realm, auth_server_url, resource);
    }

    private class UIKeyCloakConfig {

        @XmlElement(name = "auth-server-url")
        @JsonProperty("auth-server-url")
        public String auth_server_url;

        @SuppressWarnings("unused")
        public String realm;

        @SuppressWarnings("unused")
        public String resource;

        @SuppressWarnings("unused")
        public UIKeyCloakConfig() {
            super();
        }

        public UIKeyCloakConfig(String realm, String url, String baseResource) {
            this.realm = realm;
            this.auth_server_url = url;
            this.resource = baseResource + "-ui";
        }

    }
}
