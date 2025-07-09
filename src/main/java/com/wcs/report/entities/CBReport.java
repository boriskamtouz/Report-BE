package com.wcs.report.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cb_reports")
public class CBReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String val = "0";

    @ManyToOne(fetch = FetchType.EAGER)
    private CBElement cbElement;

    @ManyToOne
    @JoinColumn(name = "daily_report_id")
    private DailyReport dailyReport;
}
