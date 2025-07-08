package com.wcs.report.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cb_reports")
public class CBReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String val = "0";

    @OneToOne(fetch = FetchType.EAGER)
    private CBElement cbElement;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private DailyReport report;
}
