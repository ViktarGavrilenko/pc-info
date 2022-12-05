package com.example.pcinfo.repository;

import com.example.pcinfo.model.EquipmentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentTypeRepository extends CrudRepository<EquipmentType, Long> {
    Optional<EquipmentType> findByType(String type);
    boolean existsByType(String type);
}
