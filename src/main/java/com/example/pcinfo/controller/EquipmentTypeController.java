package com.example.pcinfo.controller;

import com.example.pcinfo.model.Company;
import com.example.pcinfo.model.Equipment;
import com.example.pcinfo.model.EquipmentTypes;
import com.example.pcinfo.repository.EquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/equip_type")
public class EquipmentTypeController {
    @Autowired
    EquipmentTypeRepository equipmentTypeRepository;

    @GetMapping("/add")
    public String showIndex(Model model) {
        Iterable<EquipmentTypes> equipmentTypes = equipmentTypeRepository.findAll();
        model.addAttribute("equipmentTypes", equipmentTypes);
        model.addAttribute("equipmentType", new EquipmentTypes());
        return "equipmenttypes";
    }

    @PostMapping("/add")
    public String addEquipment(@ModelAttribute("equipmentType") @Valid EquipmentTypes equipmentType, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            equipmentTypeRepository.save(equipmentType);
            model.addAttribute("equipmentType", new EquipmentTypes());
        }
        Iterable<EquipmentTypes> equipmentTypes = equipmentTypeRepository.findAll();
        model.addAttribute("equipmentTypes", equipmentTypes);
        return "equipmenttypes";
    }

    @PostMapping("/delete")
    public String delEquipment(@RequestParam(value = "id") Long id) {
        equipmentTypeRepository.deleteById(id);
        return "redirect:/equip_type/add";
    }
}