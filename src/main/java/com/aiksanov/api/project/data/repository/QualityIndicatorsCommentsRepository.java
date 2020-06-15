package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.QualityIndicatorsComments;
import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsCommentsPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityIndicatorsCommentsRepository extends CrudRepository<QualityIndicatorsComments, QualityIndicatorsCommentsPK> {
    void deleteAllByProjectID(int projectID);
}
