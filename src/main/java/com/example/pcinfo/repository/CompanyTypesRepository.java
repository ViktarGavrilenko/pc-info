package com.example.pcinfo.repository;

import com.example.pcinfo.model.CompanyTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypesRepository extends CrudRepository<CompanyTypes, Long> {
}
