package com.wcs.report.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CBReportDTO {
    private String val;
    private CBElementDTO cbElement;
}
