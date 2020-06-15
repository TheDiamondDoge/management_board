package com.aiksanov.api.project.web.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class ReportSnapshot implements Comparable<ReportSnapshot> {
    private int reportId;
    private Date timestamp;

    @Override
    public int compareTo(ReportSnapshot o) {
        if (Objects.isNull(this.getTimestamp())) return -1;
        if (Objects.isNull(o.getTimestamp())) return 1;
        return o.getTimestamp().compareTo(this.getTimestamp());
    }
}
