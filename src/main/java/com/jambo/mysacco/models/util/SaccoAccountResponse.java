package com.jambo.mysacco.models.util;

import java.util.HashMap;

public class SaccoAccountResponse {
    private Long saccoId;
    String saccoName;
    HashMap<String, Float> balances;

    public SaccoAccountResponse(Long saccoId, String saccoName, HashMap<String, Float> balances) {
        this.saccoId = saccoId;
        this.saccoName = saccoName;
        this.balances = balances;
    }

    public Long getSaccoId() {
        return saccoId;
    }

    public void setSaccoId(Long saccoId) {
        this.saccoId = saccoId;
    }

    public String getSaccoName() {
        return saccoName;
    }

    public void setSaccoName(String saccoName) {
        this.saccoName = saccoName;
    }

    public HashMap<String, Float> getBalances() {
        return balances;
    }

    public void setBalances(HashMap<String, Float> balances) {
        this.balances = balances;
    }
}
