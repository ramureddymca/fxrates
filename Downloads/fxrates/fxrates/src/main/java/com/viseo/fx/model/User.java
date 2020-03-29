package com.viseo.fx.model;

public class User {

    private String userId;
    private String pricingTier;
    private String email;

    public User(String userId, String pricingTier, String email) {
        super();
        this.userId = userId;
        this.pricingTier = pricingTier;
        this.email = email;
    }

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPricingTier() {
        return pricingTier;
    }

    public void setPricingTier(String pricingTier) {
        this.pricingTier = pricingTier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", pricingTier=" + pricingTier + ", email=" + email + ", getUserId()="
                + getUserId() + ", getPricingTier()=" + getPricingTier() + ", getEmail()=" + getEmail()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }
}
