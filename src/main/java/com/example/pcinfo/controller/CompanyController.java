package com.example.pcinfo.controller;

import com.example.pcinfo.model.Company;
import com.example.pcinfo.model.CompanyTypes;
import com.example.pcinfo.repository.CompanyRepository;
import com.example.pcinfo.repository.CompanyTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyTypesRepository companyTypesRepository;

    @GetMapping("/add")
    public String addCompany(@RequestParam(value = "parent", defaultValue = "0") Long id, Model model) {
        Iterable<CompanyTypes> companyTypes = companyTypesRepository.findAll();
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            model.addAttribute("companyParent", null);
        } else {
            model.addAttribute("companyParent", company.get().getId());
        }
        model.addAttribute("company", new Company());
        model.addAttribute("companyTypes", companyTypes);
        return "addCompany";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute("company") @Valid Company company, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Iterable<CompanyTypes> companyTypes = companyTypesRepository.findAll();
            model.addAttribute("companyParent", company.getParentId());
            model.addAttribute("company", company);
            model.addAttribute("companyTypes", companyTypes);
            return "addCompany";
        } else {
            companyRepository.save(company);
            if (company.getParentId() == null) {
                return "redirect:/";
            } else {
                Optional<Company> parentCompany = companyRepository.findById(company.getParentId());
                model.addAttribute("company", parentCompany.get());
                Iterable<Company> childrenCompanies = companyRepository.findAllByParentId(parentCompany.get().getId());
                model.addAttribute("childrenCompanies", childrenCompanies);
                return "company";
            }
        }
    }

    @GetMapping({"/{id}", "/"})
    public String showCompany(Model model, @PathVariable(required = false) Long id) {
        if (id == null) {
            return "redirect:/";
        } else {
            Optional<Company> company = companyRepository.findById(id);
            if (company.isEmpty()) {
                return "redirect:/";
            } else {
                model.addAttribute("company", company.get());
                Iterable<Company> childrenCompanies = companyRepository.findAllByParentId(company.get().getId());
                model.addAttribute("childrenCompanies", childrenCompanies);
                return "company";
            }
        }
    }

    @PostMapping("/delete")
    public String delCompany(@RequestParam(value = "id") Long id, Model model) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.get().getParentId() == null) {
            companyRepository.deleteById(id);
            return "redirect:/";
        } else {
            Optional<Company> parentCompany = companyRepository.findById(company.get().getParentId());
            model.addAttribute("company", parentCompany.get());
            companyRepository.deleteById(id);
            Iterable<Company> childrenCompanies = companyRepository.findAllByParentId(parentCompany.get().getId());
            model.addAttribute("childrenCompanies", childrenCompanies);
            return "company";
        }
    }

    @GetMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, Model model) {
        Iterable<CompanyTypes> companyTypes = companyTypesRepository.findAll();
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            model.addAttribute("companies", companyRepository.findAllByParentIdIsNull());
            return "index";
        } else {
            model.addAttribute("company", company.get());
            model.addAttribute("companyTypes", companyTypes);
            return "updateCompany";
        }
    }

    @PostMapping("/update")
    public String updateCompany(@ModelAttribute Company company, Model model) {
        companyRepository.save(company);
        Optional<Company> updateCompany = companyRepository.findById(company.getId());
        model.addAttribute("company", updateCompany.get());
        Iterable<Company> childrenCompanies = companyRepository.findAllByParentId(updateCompany.get().getId());
        model.addAttribute("childrenCompanies", childrenCompanies);
        return "company";
    }
}