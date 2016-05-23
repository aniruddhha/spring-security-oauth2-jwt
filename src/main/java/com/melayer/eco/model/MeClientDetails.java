/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 *
 * @author aniruddha
 */
public class MeClientDetails implements ClientDetails {

    @Id
    private final String clientId;
    private final String clientSecret;
    private Set<String> scope = Collections.emptySet();
    private Set<String> resourceIds = Collections.emptySet();
    private Set<String> authorizedGrantTypes = Collections.emptySet();
    private final Set<String> registeredRedirectUris;
    private List<GrantedAuthority> authorities = Collections.emptyList();
    private final Integer accessTokenValiditySeconds;
    private final Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation = new LinkedHashMap<>();
    private Set<String> autoApproveScopes = Collections.emptySet();

    @PersistenceConstructor
    public MeClientDetails(final String clientId,
            final String clientSecret,
            final Set<String> scope,
            final Set<String> resourceIds,
            final Set<String> authorizedGrantTypes,
            final Set<String> registeredRedirectUris,
            final List<GrantedAuthority> authorities,
            final Integer accessTokenValiditySeconds,
            final Integer refreshTokenValiditySeconds,
            final Map<String, Object> additionalInformation,
            final Set<String> autoApproveScopes) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.resourceIds = resourceIds;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
        this.autoApproveScopes = autoApproveScopes;

    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAutoApproveScopes(final Set<String> autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    public Set<String> getAutoApproveScopes() {
        return autoApproveScopes;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUris;
    }

    @Override
    public boolean isAutoApprove(final String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        return autoApproveScopes.stream().anyMatch((auto) -> (auto.equals("true") || scope.matches(auto)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientSecret, scope, resourceIds, authorizedGrantTypes, registeredRedirectUris, authorities, accessTokenValiditySeconds, refreshTokenValiditySeconds, additionalInformation, autoApproveScopes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MeClientDetails other = (MeClientDetails) obj;
        return Objects.equals(this.clientId, other.clientId)
                && Objects.equals(this.clientSecret, other.clientSecret)
                && Objects.equals(this.scope, other.scope)
                && Objects.equals(this.resourceIds, other.resourceIds)
                && Objects.equals(this.authorizedGrantTypes, other.authorizedGrantTypes)
                && Objects.equals(this.registeredRedirectUris, other.registeredRedirectUris)
                && Objects.equals(this.authorities, other.authorities)
                && Objects.equals(this.accessTokenValiditySeconds, other.accessTokenValiditySeconds)
                && Objects.equals(this.refreshTokenValiditySeconds, other.refreshTokenValiditySeconds)
                && Objects.equals(this.additionalInformation, other.additionalInformation)
                && Objects.equals(this.autoApproveScopes, other.autoApproveScopes);
    }

    @Override
    public String toString() {
        return "MongoClientDetails{"
                + "clientId='" + clientId + '\''
                + ", clientSecret='" + clientSecret + '\''
                + ", scope=" + scope
                + ", resourceIds=" + resourceIds
                + ", authorizedGrantTypes=" + authorizedGrantTypes
                + ", registeredRedirectUris=" + registeredRedirectUris
                + ", authorities=" + authorities
                + ", accessTokenValiditySeconds=" + accessTokenValiditySeconds
                + ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds
                + ", additionalInformation=" + additionalInformation
                + ", autoApproveScopes=" + autoApproveScopes
                + '}';
    }
}
