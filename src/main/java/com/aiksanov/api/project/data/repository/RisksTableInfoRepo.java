package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.RisksTableInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RisksTableInfoRepo extends CrudRepository<RisksTableInfo, Integer> {
}
