package edu.fit.cse5670;

import java.util.Date;

public class HCPolicy {
    private Integer policyID;
    private String provider;
    private Date expiryDate;

    public HCPolicy(Integer policyID, String provider, Date expiryDate) {
        this.policyID = policyID;
        this.provider = provider;
        this.expiryDate = expiryDate;
    }

    public Integer getPolicyID() {
        return policyID;
    }

    public void setPolicyID(Integer policyID) {
        this.policyID = policyID;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
