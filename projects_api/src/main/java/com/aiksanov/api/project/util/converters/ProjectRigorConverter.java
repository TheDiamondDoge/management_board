package com.aiksanov.api.project.util.converters;

import com.aiksanov.api.project.util.enums.ProjectRigors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProjectRigorConverter implements AttributeConverter<ProjectRigors, String> {

    @Override
    public String convertToDatabaseColumn(ProjectRigors projectRigors) {
        return Objects.nonNull(projectRigors) ? projectRigors.getLabel() : null;
    }

    @Override
    public ProjectRigors convertToEntityAttribute(String s) {
        if (Objects.isNull(s)) return null;

        return Stream.of(ProjectRigors.values())
                .filter(type -> type.getLabel().equalsIgnoreCase(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(s));
    }
}
