package com.example.pcinfo.repository;

import com.example.pcinfo.model.CompanyType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends CrudRepository<CompanyType, Long> {
}
