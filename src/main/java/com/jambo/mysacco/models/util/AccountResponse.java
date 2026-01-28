package com.jambo.mysacco.models.util;

import java.util.HashMap;
import java.util.List;

public class AccountResponse {
    private Long userId;
    Long saccoId;
    HashMap<String, Float> balances;

    public AccountResponse(Long userId, Long saccoId, HashMap<String, Float> balances) {
        this.userId = userId;
        this.saccoId = saccoId;
        this.balances = balances;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSaccoId() {
        return saccoId;
    }

    public void setSaccoId(Long saccoId) {
        this.saccoId = saccoId;
    }

    public HashMap<String, Float> getBalances() {
        return balances;
    }

    public void setBalances(HashMap<String, Float> balances) {
        this.balances = balances;
    }
}
