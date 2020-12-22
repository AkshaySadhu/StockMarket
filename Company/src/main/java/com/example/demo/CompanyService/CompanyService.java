package com.example.demo.CompanyService;

import com.example.demo.Model.Company;
import com.example.demo.Model.IPO;
import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.IPORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private final IPORepository ipoRepository;

    @Autowired
    private final CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository, IPORepository ipoRepository) {
        this.companyRepository = companyRepository;
        this.ipoRepository = ipoRepository;
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public Iterable<Company> showAllCompany() {
        return companyRepository.findAll();
    }

    public Iterable<Company> deleteCompanyByname(String companyname) {
        companyRepository.deleteByCompanyName(companyname);
        return companyRepository.findAll();
    }


    public Company findByCompanyname(String username) {
        return companyRepository.findByCompanyName(username);
    }

    public void saveipo(IPO ipo) {
        ipoRepository.save(ipo);
    }

    public IPO findipoByCompanyname(String username) {
        return ipoRepository.findByCompanyName(username);
    }

    public Iterable<IPO> deleteCompanyipoByname(String companyname) {
        ipoRepository.deleteByCompanyName(companyname);
        return ipoRepository.findAll();

    }

    public String updateipo(Long id, IPO ipo) {
        if (ipoRepository.findById(id).isPresent()) {
            IPO existingipo = ipoRepository.findById(id).get();

            existingipo.setCompanyName(ipo.getCompanyName());
            existingipo.setCostPerShare(ipo.getCostPerShare());
            existingipo.setDate(ipo.getDate());
            existingipo.setCompanyName(ipo.getCompanyName());
            existingipo.setRemarks(ipo.getRemarks());
            existingipo.setTotalShares(ipo.getTotalShares());
            existingipo.setTime(ipo.getTime());
            existingipo.setStockExchange(ipo.getStockExchange());
            IPO updatedipo = ipoRepository.save(existingipo);

            return "Updated Successfully!!! ";
        } else {
            return "NOT UPDATED";
        }

    }

}
