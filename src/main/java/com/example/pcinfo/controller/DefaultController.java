package com.example.pcinfo.controller;

import com.example.pcinfo.model.Company;
import com.example.pcinfo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @Autowired
    CompanyRepository company;

    @GetMapping("/")
    public String showIndex(Model model) {
        Iterable<Company> companies = company.findAllByParentIdIsNull();
        model.addAttribute("companies", companies);
        return "index";
    }
}