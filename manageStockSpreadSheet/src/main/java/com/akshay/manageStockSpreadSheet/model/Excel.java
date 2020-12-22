package com.akshay.manageStockSpreadSheet.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Excel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String companyCode;

    private String stockExchange;

    private Double currentPrice;

    private Date date;

    private Date time;

    public Excel() {
    }

    public Excel(Long id, String companyCode, String stockExchange, Double currentPrice, Date date, Date time) {
        this.Id = id;
        this.companyCode = companyCode;
        this.stockExchange = stockExchange;
        this.currentPrice = currentPrice;
        this.date = date;
        this.time = time;
    }

    public Long getId() { return Id; }

    public void setId(Long id) { this.Id = id; }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
