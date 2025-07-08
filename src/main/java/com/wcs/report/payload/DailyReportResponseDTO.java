package com.wcs.report.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportResponseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<CBReportDTO> reports;
}
