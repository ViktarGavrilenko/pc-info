package com.example.pcinfo.controller;

import com.example.pcinfo.model.CompanyType;
import com.example.pcinfo.model.EquipmentType;
import com.example.pcinfo.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/company_type")
public class CompanyTypeController {
    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @GetMapping("/add")
    public String showIndex(Model model) {
        Iterable<CompanyType> companyTypes = companyTypeRepository.findAll();
        model.addAttribute("companyTypes", companyTypes);
        model.addAttribute("companyType", new CompanyType());
        return "companyTypes";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute("companyType") @Valid CompanyType companyType, Errors errors, Model model ) {
        boolean isTypeExists = companyTypeRepository.existsByType(companyType.getType());
        if (!errors.hasErrors() && !isTypeExists) {
            companyTypeRepository.save(companyType);
        }
        Iterable<CompanyType> companyTypes = companyTypeRepository.findAll();
        model.addAttribute("companyTypes", companyTypes);
        if (isTypeExists) {
            model.addAttribute("notUnique", "Этот тип уже есть");
        }
        return "companyTypes";
    }

    @PostMapping("/delete")
    public String delCompany(@RequestParam(value = "id") Long id) {
        companyTypeRepository.deleteById(id);
        return "redirect:/company_type/add";
    }
}