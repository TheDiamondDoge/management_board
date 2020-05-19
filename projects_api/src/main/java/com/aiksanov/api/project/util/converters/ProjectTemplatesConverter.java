package com.aiksanov.api.project.util.converters;

import com.aiksanov.api.project.util.enums.ProjectTemplates;
import com.aiksanov.api.project.util.enums.ProjectTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProjectTemplatesConverter implements AttributeConverter<ProjectTemplates, String> {
    @Override
    public String convertToDatabaseColumn(ProjectTemplates projectTemplates) {
        return Objects.nonNull(projectTemplates) ? projectTemplates.getLabel() : null;
    }

    @Override
    public ProjectTemplates convertToEntityAttribute(String s) {
        if (Objects.isNull(s)) return null;

        return Stream.of(ProjectTemplates.values())
                .filter(type -> type.getLabel().equalsIgnoreCase(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(s));
    }
}
