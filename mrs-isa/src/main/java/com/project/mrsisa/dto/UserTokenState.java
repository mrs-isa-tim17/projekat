package com.project.mrsisa.dto;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {
	
    private String accessToken;
    private Long expiresIn;
    private long roleID;

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String accessToken, long expiresIn, long roleId) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.roleID = roleId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}