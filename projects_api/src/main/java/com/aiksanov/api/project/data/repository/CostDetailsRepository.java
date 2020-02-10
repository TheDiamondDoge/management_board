package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.CostDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostDetailsRepository extends CrudRepository<CostDetails, Integer> {
}
