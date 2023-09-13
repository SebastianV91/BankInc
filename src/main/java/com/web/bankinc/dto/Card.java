package com.web.bankinc.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "card")
public class Card {

    private static final long serialVersionUID = 3514047894891029401L;

    @Id
    private String cardId;

    private String titular;

    private Date expireDate;

    private Integer balance;

    private String status;

    private Integer typeProduct;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(Integer typeProduct) {
        this.typeProduct = typeProduct;
    }
}
