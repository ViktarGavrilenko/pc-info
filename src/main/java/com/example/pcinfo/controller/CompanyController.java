package com.example.pcinfo.controller;

import com.example.pcinfo.model.Company;
import com.example.pcinfo.model.CompanyTypes;
import com.example.pcinfo.repository.CompanyRepository;
import com.example.pcinfo.repository.CompanyTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyTypesRepository companyTypesRepository;

    @GetMapping("/add")
    public String addCompany(Model model) {
        Iterable<CompanyTypes> companyTypes = companyTypesRepository.findAll();
        model.addAttribute("companyTypes", companyTypes);
        model.addAttribute("company", new Company());
        return "addCompany";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute Company company, Model model) {
        companyRepository.save(company);
        Iterable<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "index";
    }

    @GetMapping("/{id}")
    public String showCompany(Model model, @PathVariable Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isEmpty()){
            return "error";
        } else {
            model.addAttribute("company", company.get());
            return "company";
        }
    }

    @DeleteMapping("/delete")
    public String delCompany(@RequestParam(value = "id") Long id) {
        companyRepository.deleteById(id);
        return "redirect:/";
    }
}