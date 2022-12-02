package com.example.pcinfo.controller;

import com.example.pcinfo.model.CompanyTypes;
import com.example.pcinfo.repository.CompanyTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company_type")
public class CompanyTypesController {
    @Autowired
    CompanyTypesRepository companyTypesRepository;

    @GetMapping
    public String showIndex(Model model) {
        Iterable<CompanyTypes> companyTypes = companyTypesRepository.findAll();
        model.addAttribute("companyTypes", companyTypes);
        return "companytypes";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute CompanyTypes companyTypes) {
        companyTypesRepository.save(companyTypes);
        return "redirect:/company_type";
    }

    @PostMapping("/delete")
    public String delCompany(@RequestParam(value = "id") Long id) {
        companyTypesRepository.deleteById(id);
        return "redirect:/company_type";
    }
}