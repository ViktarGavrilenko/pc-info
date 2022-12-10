package com.example.pcinfo.controller;

import com.example.pcinfo.model.Company;
import com.example.pcinfo.model.CompanyType;
import com.example.pcinfo.repository.CompanyRepository;
import com.example.pcinfo.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyTypeRepository companyTypeRepository;

    private static final String COMPANY_PARENT = "companyParent";
    private static final String COMPANY = "company";
    private static final String COMPANIES = "companies";
    private static final String COMPANY_TYPES = "companyTypes";
    private static final String CHILDREN_COMPANIES = "childrenCompanies";
    private static final String TREE_COMPANY = "treeCompany";
    private static final String UPDATE_COMPANY = "updateCompany";
    private static final String REDIRECT_MAIN = "redirect:/";
    private static final String ADD_COMPANY = "addCompany";

    @GetMapping("/add")
    public String addCompany(@RequestParam(value = "parent", defaultValue = "0") Long id, Model model) {
        Iterable<CompanyType> companyTypes = companyTypeRepository.findAllByOrderByTypeAsc();
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            model.addAttribute(COMPANY_PARENT, null);
        } else {
            model.addAttribute(COMPANY_PARENT, company.get().getId());
        }
        model.addAttribute(COMPANY, new Company());
        model.addAttribute(COMPANY_TYPES, companyTypes);
        return ADD_COMPANY;
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute(COMPANY) @Valid Company company, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Iterable<CompanyType> companyTypes = companyTypeRepository.findAllByOrderByTypeAsc();
            model.addAttribute(COMPANY_PARENT, company.getParentId());
            model.addAttribute(COMPANY, company);
            model.addAttribute(COMPANY_TYPES, companyTypes);
            return ADD_COMPANY;
        } else {
            companyRepository.save(company);
            if (company.getParentId() == null) {
                return REDIRECT_MAIN;
            } else {
                Optional<Company> parentCompany = companyRepository.findById(company.getParentId());
                model.addAttribute(COMPANY, parentCompany.get());
                Iterable<Company> childrenCompanies = companyRepository.findAllByParentIdOrderByShortNameAsc(parentCompany.get().getId());
                model.addAttribute(CHILDREN_COMPANIES, childrenCompanies);
                model.addAttribute(TREE_COMPANY, getListParentCompanies(company));
                return COMPANY;
            }
        }
    }

    @GetMapping({"/{id}", "/"})
    public String showCompany(Model model, @PathVariable(required = false) Long id) {
        if (id == null) {
            return REDIRECT_MAIN;
        } else {
            Optional<Company> company = companyRepository.findById(id);
            if (company.isEmpty()) {
                return REDIRECT_MAIN;
            } else {
                model.addAttribute(COMPANY, company.get());
                Iterable<Company> childrenCompanies = companyRepository.findAllByParentIdOrderByShortNameAsc(company.get().getId());
                model.addAttribute(CHILDREN_COMPANIES, childrenCompanies);
                model.addAttribute(TREE_COMPANY, getListParentCompanies(company.get()));
                return COMPANY;
            }
        }
    }

    @PostMapping("/delete")
    public String delCompany(@RequestParam(value = "id") Long id, Model model) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.get().getParentId() == null) {
            companyRepository.deleteById(id);
            return REDIRECT_MAIN;
        } else {
            Optional<Company> parentCompany = companyRepository.findById(company.get().getParentId());
            model.addAttribute(COMPANY, parentCompany.get());
            companyRepository.deleteById(id);
            Iterable<Company> childrenCompanies = companyRepository.findAllByParentIdOrderByShortNameAsc(parentCompany.get().getId());
            model.addAttribute(CHILDREN_COMPANIES, childrenCompanies);
            model.addAttribute(TREE_COMPANY, getListParentCompanies(company.get()));
            return COMPANY;
        }
    }

    @GetMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, Model model) {
        Iterable<CompanyType> companyTypes = companyTypeRepository.findAllByOrderByTypeAsc();
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            model.addAttribute(COMPANIES, companyRepository.findAllByParentIdIsNullOrderByShortNameAsc());
            return "index";
        } else {
            model.addAttribute(COMPANY, company.get());
            model.addAttribute(COMPANY_TYPES, companyTypes);
            return UPDATE_COMPANY;
        }
    }

    @PostMapping("/update")
    public String updateCompany(@ModelAttribute(COMPANY) @Valid Company company, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Iterable<CompanyType> companyTypes = companyTypeRepository.findAllByOrderByTypeAsc();
            model.addAttribute(COMPANY, company);
            model.addAttribute(COMPANY_TYPES, companyTypes);
            return UPDATE_COMPANY;
        }
        companyRepository.save(company);
        Optional<Company> updateCompany = companyRepository.findById(company.getId());
        model.addAttribute(COMPANY, updateCompany.get());
        Iterable<Company> childrenCompanies = companyRepository.findAllByParentIdOrderByShortNameAsc(updateCompany.get().getId());
        model.addAttribute(CHILDREN_COMPANIES, childrenCompanies);
        model.addAttribute(TREE_COMPANY, getListParentCompanies(company));
        return COMPANY;
    }

    public List<Company> getListParentCompanies(Company company) {
        List<Company> treeCompany = new ArrayList<>();
        if (company.getParentId() != null) {
            Optional<Company> startCompany = companyRepository.findById(company.getParentId());
            while (startCompany.isPresent()) {
                treeCompany.add(startCompany.get());
                if (startCompany.get().getParentId() == null)
                    break;
                startCompany = companyRepository.findById(startCompany.get().getParentId());
            }
        }
        Collections.reverse(treeCompany);
        return treeCompany;
    }
}