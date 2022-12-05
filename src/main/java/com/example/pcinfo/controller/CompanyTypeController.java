package com.example.pcinfo.controller;

import com.example.pcinfo.model.CompanyType;
import com.example.pcinfo.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company_type")
public class CompanyTypeController {
    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @GetMapping
    public String showIndex(Model model) {
        Iterable<CompanyType> companyTypes = companyTypeRepository.findAll();
        model.addAttribute("companyTypes", companyTypes);
        return "companytypes";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute CompanyType companyType) {
        companyTypeRepository.save(companyType);
        return "redirect:/company_type";
    }

    @PostMapping("/delete")
    public String delCompany(@RequestParam(value = "id") Long id) {
        companyTypeRepository.deleteById(id);
        return "redirect:/company_type";
    }
}