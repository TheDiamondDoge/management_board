package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.BlcDashboardComments;
import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlcDashboardCommentsRepo extends CrudRepository<BlcDashboardComments, BlcDashboardPK> {

}
