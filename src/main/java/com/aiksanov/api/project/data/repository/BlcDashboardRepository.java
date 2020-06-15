package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.BlcDashboardRow;
import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlcDashboardRepository extends CrudRepository<BlcDashboardRow, BlcDashboardPK> {

}
