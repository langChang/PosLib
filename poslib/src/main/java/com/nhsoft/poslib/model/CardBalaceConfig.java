package com.nhsoft.poslib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Iverson on 2020/9/28 2:26 PM
 * 此类用于：
 */

public class CardBalaceConfig implements Serializable {


    @SerializedName("消费卡参数")
    private CardBalaceConfigDetail cardBalanceParams;

    public CardBalaceConfigDetail getCardBalanceParams() {
        return cardBalanceParams;
    }

    public void setCardBalanceParams(CardBalaceConfigDetail cardBalanceParams) {
        this.cardBalanceParams = cardBalanceParams;
    }

    public class CardBalaceConfigDetail implements Serializable{

        @SerializedName("未结算存款消费额度")
        private String unSettlementCardTotal;
        @SerializedName("未结算存款额度")
        private String unSettlementDeposit;

        public String getUnSettlementCardTotal() {
            return unSettlementCardTotal;
        }

        public void setUnSettlementCardTotal(String unSettlementCardTotal) {
            this.unSettlementCardTotal = unSettlementCardTotal;
        }

        public String getUnSettlementDeposit() {
            return unSettlementDeposit;
        }

        public void setUnSettlementDeposit(String unSettlementDeposit) {
            this.unSettlementDeposit = unSettlementDeposit;
        }
    }

}
