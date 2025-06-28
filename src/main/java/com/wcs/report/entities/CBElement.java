package com.wcs.report.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cbelements")
@Table(name = "cbelements")
public class CBElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String val;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "goal_id")
    @JsonIgnore
    private Goal goal;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "report_id")
    private Report report;
}
