package com.example.pcinfo.repository;

import com.example.pcinfo.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    public Iterable<Company>  findAllByParentIdIsNull();
    public Iterable<Company>  findAllByParentId(Long id);
}
