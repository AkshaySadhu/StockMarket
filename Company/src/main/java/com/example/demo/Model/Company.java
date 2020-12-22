package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String companyName;

    private Long turnover;

    private String ceo;

    private String directors;

    private String stockExchanges;

    private String sector;

    private String brief;

    private Long stockExchangeCode;

    public Company() {
    }

    public Company(Long id, Long turnover, String companyName, String ceo, String directors, String stockExchanges, String sector, String brief, Long stockExchangeCode) {
        Id = id;
        this.turnover = turnover;
        this.companyName = companyName;
        this.ceo = ceo;
        this.directors = directors;
        this.stockExchanges = stockExchanges;
        this.sector = sector;
        this.brief = brief;
        this.stockExchangeCode = stockExchangeCode;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getTurnover() {
        return turnover;
    }

    public void setTurnover(Long turnover) {
        this.turnover = turnover;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getStockExchanges() {
        return stockExchanges;
    }

    public void setStockExchanges(String stockExchanges) {
        this.stockExchanges = stockExchanges;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Long getStockExchangeCode() {
        return stockExchangeCode;
    }

    public void setStockExchangeCode(Long stockExchangeCode) {
        this.stockExchangeCode = stockExchangeCode;
    }


}
