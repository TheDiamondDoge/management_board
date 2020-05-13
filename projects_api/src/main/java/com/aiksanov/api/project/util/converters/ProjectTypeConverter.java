package com.aiksanov.api.project.util.converters;

import com.aiksanov.api.project.util.enums.ProjectTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProjectTypeConverter implements AttributeConverter<ProjectTypes, String> {

    @Override
    public String convertToDatabaseColumn(ProjectTypes projectTypes) {
        return Objects.nonNull(projectTypes) ? projectTypes.getValue() : null;
    }

    @Override
    public ProjectTypes convertToEntityAttribute(String s) {
        if (Objects.isNull(s)) return null;

        return Stream.of(ProjectTypes.values())
                .filter(type -> type.getValue().equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(s));
    }
}
