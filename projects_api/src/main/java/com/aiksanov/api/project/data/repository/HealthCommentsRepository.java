package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.HealthIndicatorsComments;
import com.aiksanov.api.project.data.entity.HealthIndicatorsCommentsPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HealthCommentsRepository extends CrudRepository<HealthIndicatorsComments, HealthIndicatorsCommentsPK> {
    HealthIndicatorsComments findByPk(HealthIndicatorsCommentsPK pk);
}
