package com.example.demo.Model;

import javax.persistence.*;

@Entity
public class IPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String companyName;
    private String stockExchange;
    private String totalShares;
    private String remarks;
    private String date;
    private String time;
    private Long costPerShare;

    public IPO() {
    }

    public IPO(Long id, String companyName, String stockExchange, String totalShares, String remarks, String date, String time, Long costPerShare) {
        Id = id;
        this.companyName = companyName;
        this.stockExchange = stockExchange;
        this.totalShares = totalShares;
        this.remarks = remarks;
        this.date = date;
        this.time = time;
        this.costPerShare = costPerShare;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(String totalShares) {
        this.totalShares = totalShares;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getCostPerShare() {
        return costPerShare;
    }

    public void setCostPerShare(Long costPerShare) {
        this.costPerShare = costPerShare;
    }
}
