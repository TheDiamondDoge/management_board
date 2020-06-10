package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_milestones")
public class Milestone implements Comparable<Milestone> {
    @EmbeddedId
    private MilestonePK milestonePK;

    @Column(name = "baseline_date")
    private Date baselineDate;

    @Column(name = "actual_date")
    private Date actualDate;

    @Column(name = "completion")
    private int completion;

    @Column(name = "url")
    private String meetingMinutes;

    @Column(name = "show_in_timeline")
    private boolean shown;

    @Override
    public int compareTo(Milestone o) {
        Date d1 = Objects.isNull(this.getActualDate()) ? this.getBaselineDate() : this.getActualDate();
        Date d2 = Objects.isNull(o.getActualDate()) ? o.getBaselineDate() : o.getActualDate();
        if (Objects.isNull(d1) || Objects.isNull(d2)) {
            return 0;
        }
        return d1.compareTo(d2);
    }
}

