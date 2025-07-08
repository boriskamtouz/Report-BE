package com.wcs.report.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
