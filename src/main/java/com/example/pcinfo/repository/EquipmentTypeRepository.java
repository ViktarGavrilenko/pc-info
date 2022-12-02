package com.example.pcinfo.repository;

import com.example.pcinfo.model.EquipmentTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepository extends CrudRepository<EquipmentTypes, Long> {
}
