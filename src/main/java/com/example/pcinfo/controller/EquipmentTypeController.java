package com.example.pcinfo.controller;

import com.example.pcinfo.model.CompanyType;
import com.example.pcinfo.model.EquipmentType;
import com.example.pcinfo.repository.EquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/equip_type")
public class EquipmentTypeController {
    @Autowired
    EquipmentTypeRepository equipmentTypeRepository;

    @GetMapping("/add")
    public String showIndex(Model model) {
        Iterable<EquipmentType> equipmentTypes = equipmentTypeRepository.findAllByOrderByTypeAsc();
        model.addAttribute("equipmentTypes", equipmentTypes);
        model.addAttribute("equipmentType", new EquipmentType());
        return "equipmenttypes";
    }

    @PostMapping("/add")
    public String addEquipment(@ModelAttribute("equipmentType") @Valid EquipmentType equipmentType, Errors errors, Model model) {
        boolean isTypeExists = equipmentTypeRepository.existsByType(equipmentType.getType());
        if (!errors.hasErrors() && !isTypeExists) {
            equipmentTypeRepository.save(equipmentType);
            model.addAttribute("equipmentType", new EquipmentType());
        }
        Iterable<EquipmentType> equipmentTypes = equipmentTypeRepository.findAllByOrderByTypeAsc();
        model.addAttribute("equipmentTypes", equipmentTypes);
        if (isTypeExists) {
            model.addAttribute("notUnique", "Этот тип уже есть");
        }
        return "equipmenttypes";
    }

    @PostMapping("/delete")
    public String delEquipment(@RequestParam(value = "id") Long id) {
        equipmentTypeRepository.deleteById(id);
        return "redirect:/equip_type/add";
    }

    @GetMapping("/update/{id}")
    public String updateType(Model model, @PathVariable(required = false) Long id) {
        if (id == null) {
            return "redirect:/equip_type/add";
        } else {
            Optional<EquipmentType> equipmentType = equipmentTypeRepository.findById(id);
            if (equipmentType.isEmpty()) {
                return "redirect:/equip_type/add";
            } else {
                model.addAttribute("equipmentType", equipmentType.get());
                return "updateEquipmentType";
            }
        }
    }

    @PostMapping("/update")
    public String updateEquipmentType(@ModelAttribute("equipmentType") @Valid EquipmentType equipmentType, Errors errors) {
        if (errors.hasErrors()) {
            return "updateEquipmentType";
        }
        equipmentTypeRepository.save(equipmentType);
        return "redirect:/equip_type/add";
    }
}