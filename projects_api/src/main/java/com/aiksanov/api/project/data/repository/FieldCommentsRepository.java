package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.FieldComments;
import com.aiksanov.api.project.data.entity.pk.FieldCommentsPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldCommentsRepository extends CrudRepository<FieldComments, FieldCommentsPK> {
    void deleteAllByPk_ProjectID(int projectID);
    List<FieldComments> findAllByPk_ProjectID(int projectID);
}
