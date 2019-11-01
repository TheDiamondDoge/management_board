package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.HealthIndicatorsComments;
import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsCommentsPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthCommentsRepository extends CrudRepository<HealthIndicatorsComments, HealthIndicatorsCommentsPK> {
    Optional<HealthIndicatorsComments> findByPk(HealthIndicatorsCommentsPK pk);
}
